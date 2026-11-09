import java.util.Base64
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// ---------------------------------------------------------------------------
// Release signing
//
// Signing material is resolved from, in order:
//   1. environment variables exported by CI (no properties file is written):
//        KEYSTORE_BASE64 or KEYSTORE_FILE, KEYSTORE_PASSWORD, KEY_ALIAS,
//        KEY_PASSWORD, optional KEYSTORE_TYPE (JKS by default)
//   2. keystore.properties at the repository root (gitignored, local builds)
//
// Without either, release artifacts fall back to debug signing so CI still
// produces installable test builds — those are never valid for Play.
// `./gradlew verifyReleaseSigning` fails loudly in that case, and
// -PrequireReleaseSigning=true (or REQUIRE_RELEASE_SIGNING=true) makes any
// build fail without a real upload key.
// ---------------------------------------------------------------------------
android {
    namespace = "com.mbbshandbook.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mbbshandbook.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 9
        versionName = "1.6.0"
    }

    signingConfigs {
        val keystorePropsFile = rootProject.file("keystore.properties")
        val keystoreProps = Properties()
        if (keystorePropsFile.exists()) keystorePropsFile.inputStream().use { keystoreProps.load(it) }

        val base64Keystore = System.getenv("KEYSTORE_BASE64")
        val storePasswordValue =
            System.getenv("KEYSTORE_PASSWORD") ?: keystoreProps.getProperty("storePassword")
        val keyAliasValue = System.getenv("KEY_ALIAS") ?: keystoreProps.getProperty("keyAlias")
        val keyPasswordValue =
            System.getenv("KEY_PASSWORD") ?: keystoreProps.getProperty("keyPassword")

        val keystoreFileEnv = System.getenv("KEYSTORE_FILE")
        val keystoreFile: File? = when {
            !base64Keystore.isNullOrBlank() -> {
                val decoded = layout.buildDirectory.file("signing/release-keystore.jks").get().asFile
                decoded.parentFile?.mkdirs()
                decoded.writeBytes(Base64.getMimeDecoder().decode(base64Keystore))
                decoded
            }
            !keystoreFileEnv.isNullOrBlank() -> rootProject.file(keystoreFileEnv)
            else -> keystoreProps.getProperty("storeFile")?.let { rootProject.file(it) }
        }

        if (keystoreFile != null && keystoreFile.isFile &&
            !storePasswordValue.isNullOrBlank() &&
            !keyAliasValue.isNullOrBlank() &&
            !keyPasswordValue.isNullOrBlank()
        ) {
            create("release") {
                storeFile = keystoreFile
                storePassword = storePasswordValue
                keyAlias = keyAliasValue
                keyPassword = keyPasswordValue
                storeType = System.getenv("KEYSTORE_TYPE")
                    ?: keystoreProps.getProperty("storeType")
                    ?: "JKS"
                enableV1Signing = true
                enableV2Signing = true
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
            signingConfig = if (signingConfigs.findByName("release") != null) {
                signingConfigs.getByName("release")
            } else {
                // Installable test artifacts only — never valid for a Play upload.
                signingConfigs.getByName("debug")
            }
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

val releaseSigningConfigured: Boolean = android.signingConfigs.findByName("release") != null

/** Fails when release artifacts would be signed with the debug key. */
tasks.register("verifyReleaseSigning") {
    group = "verification"
    description = "Verifies that release builds are signed with the upload key, not the debug key."
    doLast {
        if (!releaseSigningConfigured) {
            throw GradleException(
                "Release signing is NOT configured. The APK/AAB would be debug-signed and Google " +
                    "Play would reject it. Add KEYSTORE_BASE64, KEYSTORE_PASSWORD, KEY_ALIAS and " +
                    "KEY_PASSWORD secrets (see docs/RELEASE.md) or create keystore.properties locally."
            )
        }
        logger.lifecycle("Release signing OK — alias and keystore are configured.")
    }
}

/** Prints what the next release build will use (handy for CI logs). */
tasks.register("printSigningStatus") {
    group = "help"
    description = "Prints whether release signing material is available."
    doLast {
        if (releaseSigningConfigured) {
            println("Release signing: configured")
        } else {
            println("Release signing: NOT configured — release artifacts will be debug-signed (test only).")
        }
    }
}

if ((project.findProperty("requireReleaseSigning") as String?)?.toBoolean() == true ||
    System.getenv("REQUIRE_RELEASE_SIGNING")?.toBoolean() == true
) {
    if (!releaseSigningConfigured) {
        throw GradleException(
            "Release signing is required (requireReleaseSigning=true) but no signing material was " +
                "found. Provide KEYSTORE_BASE64/KEYSTORE_FILE, KEYSTORE_PASSWORD, KEY_ALIAS and " +
                "KEY_PASSWORD, or keystore.properties at the repo root. See docs/RELEASE.md."
        )
    }
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
