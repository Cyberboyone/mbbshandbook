# CI workflow updates pending push permission

`workflows.patch` contains the GitHub Actions changes that could not be pushed
from the tooling session that prepared this branch: the GitHub App connection
lacks the **Workflows** permission, so any change under `.github/workflows/` is
rejected with

```
refusing to allow a GitHub App to create or update workflow
`.github/workflows/android-build.yml` without `workflows` permission
```

Everything else in this change set is already on the branch. The patch adds:

| File | Change |
|---|---|
| `.github/workflows/android-build.yml` | signing status report, signing env for Gradle, artifact signature verification, `SHA256SUMS.txt`, tag builds require an upload key, plus a `quality` job (lint + Play listing validation) |
| `.github/workflows/play-release.yml` | **new** manual workflow that builds a signed AAB and uploads it to a Play track |

## Apply it

```bash
git checkout arena/3b5cf4cc-mbbshandbook   # or main, once merged
git apply docs/ci/workflows.patch
git add .github/workflows
git commit -m "CI: signing verification, checksums, lint and Play upload"
git push
```

If the push is rejected with the same message, the token needs the **Workflows**
permission: reconnect GitHub (or use `gh auth login` with a token that has
`repo` + `workflow` scopes), or paste the two files into the GitHub web editor —
the web UI never has this restriction.

## What still works without the patch

The signing guardrails live in Gradle, not in the workflow:

- `./gradlew verifyReleaseSigning` fails when signing material is missing.
- `REQUIRE_RELEASE_SIGNING=true` makes any build fail without a real key.
- `scripts/verify-release-signing.sh` compares the built APK/AAB certificate with
  the configured upload key and fails on a mismatch.
- `./gradlew verifyReleaseSigning` (and env-based signing config) works without
  any workflow change, so a bare `./gradlew bundleRelease` in the current workflow
  already signs with the upload key when the secrets exist.

Once the patch is applied, the run also publishes checksums and runs the lint and
listing checks; `play-release.yml` additionally automates Play uploads.
