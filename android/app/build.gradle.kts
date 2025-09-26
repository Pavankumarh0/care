plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.user"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.user"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // Use a proper release signing config if provided via gradle.properties or environment.
            // Falls back to debug only if no release config is available.
            val hasReleaseKeystore = project.hasProperty("RELEASE_STORE_FILE") || System.getenv("RELEASE_STORE_FILE") != null
            if (hasReleaseKeystore) {
                signingConfig = signingConfigs.create("release").apply {
                    val storeFilePath = (project.findProperty("RELEASE_STORE_FILE") as String?) ?: System.getenv("RELEASE_STORE_FILE")
                    val storePasswordVal = (project.findProperty("RELEASE_STORE_PASSWORD") as String?) ?: System.getenv("RELEASE_STORE_PASSWORD")
                    val keyAliasVal = (project.findProperty("RELEASE_KEY_ALIAS") as String?) ?: System.getenv("RELEASE_KEY_ALIAS")
                    val keyPasswordVal = (project.findProperty("RELEASE_KEY_PASSWORD") as String?) ?: System.getenv("RELEASE_KEY_PASSWORD")

                    if (storeFilePath != null) {
                        storeFile = file(storeFilePath)
                    }
                    if (storePasswordVal != null) {
                        storePassword = storePasswordVal
                    }
                    if (keyAliasVal != null) {
                        keyAlias = keyAliasVal
                    }
                    if (keyPasswordVal != null) {
                        keyPassword = keyPasswordVal
                    }
                }
            } else {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
    }
}

flutter {
    source = "../.."
}
