pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroup ("com.github.chrisbanes")
            }
        }

        val localSdk = File("${providers.gradleProperty("LinphoneSdkBuildDir").get()}/maven_repository/org/linphone/linphone-sdk-android/maven-metadata.xml")
        if (localSdk.exists()) {
            val localSdkPath = providers.gradleProperty("LinphoneSdkBuildDir").get()
            println("Using locally built SDK from maven repository at ${localSdkPath}/maven_repository/")
            maven {
                name = "local linphone-sdk maven repository"
                url = uri(
                    "file://${localSdkPath}/maven_repository/"
                )
                content {
                    includeGroup("org.linphone")
                }
            }
        } else {
            throw GradleException("Only local SDK with my hacks SDK allowed from: https://github.com/dibalavs/linphone-sdk ")
        }
    }
}

rootProject.name = "Linphone"
include(":app")
