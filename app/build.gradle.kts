import java.util.Properties
import org.gradle.api.GradleException
import java.security.KeyStore
import java.security.cert.X509Certificate

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mbbshandbook.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mbbshandbook.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 8
        versionName = "1.5.0"
    }

    signingConfigs {
        // Release artifacts must use a private upload key, never the debug key.
        // Local builds use ignored keystore.properties; CI materializes that
        // file from repository secrets. Missing credentials fail the release
        // task explicitly instead of producing a misleading release artifact.
        val keystorePropsFile = rootProject.file("keystore.properties")
        if (keystorePropsFile.exists()) {
            val props = Properties()
            keystorePropsFile.inputStream().use { props.load(it) }
            val required = listOf("storeFile", "storePassword", "keyAlias", "keyPassword")
            val missing = required.filter { props.getProperty(it).isNullOrBlank() }
            if (missing.isNotEmpty()) {
                throw GradleException(
                    "keystore.properties is missing ${missing.joinToString()}. " +
                        "See README → Release signing; never commit signing credentials."
                )
            }
            val uploadStore = rootProject.file(props.getProperty("storeFile"))
            if (!uploadStore.isFile) {
                throw GradleException("Release keystore not found: ${uploadStore.path}")
            }
            create("release") {
                storeFile = uploadStore
                storePassword = props.getProperty("storePassword")
                keyAlias = props.getProperty("keyAlias")
                keyPassword = props.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.findByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        // Debug-only ad diagnostics must never appear in release artifacts.
        buildConfig = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

// Guard both assembleRelease and bundleRelease, including direct invocations.
// AGP also validates the alias/password; this check catches the old debug-key
// fallback before an APK/AAB can be collected or uploaded.
val verifyReleaseSigning = tasks.register("verifyReleaseSigning") {
    group = "verification"
    description = "Require a non-debug upload key before building release artifacts."
    doLast {
        val releaseSigning = android.signingConfigs.findByName("release")
            ?: throw GradleException(
                "Release signing is not configured. Add keystore.properties locally " +
                    "or KEYSTORE_BASE64, KEYSTORE_PASSWORD, KEY_ALIAS and KEY_PASSWORD " +
                    "in GitHub Actions secrets. See README → Release signing."
            )
        val uploadStore = releaseSigning.storeFile
        if (uploadStore == null || !uploadStore.isFile) {
            throw GradleException("Release keystore is missing.")
        }
        if (releaseSigning.keyAlias.isNullOrBlank() ||
            releaseSigning.storePassword.isNullOrBlank() ||
            releaseSigning.keyPassword.isNullOrBlank()
        ) {
            throw GradleException("Release alias/password configuration is incomplete.")
        }
        val keyStore = KeyStore.getInstance(
            releaseSigning.storeType ?: KeyStore.getDefaultType()
        )
        uploadStore.inputStream().use { input ->
            keyStore.load(input, releaseSigning.storePassword!!.toCharArray())
        }
        val alias = releaseSigning.keyAlias!!
        if (!keyStore.containsAlias(alias) || !keyStore.isKeyEntry(alias)) {
            throw GradleException("Release keystore has no private-key entry for the configured alias.")
        }
        val certificate = keyStore.getCertificate(alias) as? X509Certificate
            ?: throw GradleException("Release signing certificate is not X.509.")
        certificate.checkValidity()
        if (certificate.subjectX500Principal.name.contains("Android Debug", ignoreCase = true)) {
            throw GradleException("The Android debug certificate cannot sign Play release artifacts.")
        }
        logger.lifecycle("Release upload certificate verified (not a debug certificate).")
    }
}

tasks.matching { it.name == "preReleaseBuild" }.configureEach {
    dependsOn(verifyReleaseSigning)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.ads)
    implementation(libs.user.messaging.platform)
    testImplementation(libs.junit)
    debugImplementation(libs.androidx.ui.tooling)
}
