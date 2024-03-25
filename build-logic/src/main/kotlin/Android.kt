import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
internal fun configureAndroidApplication(appModuleExtension : BaseAppModuleExtension,project : Project) {
    appModuleExtension.apply {
        compileSdk = ProjectConfigs.compileSdk
        defaultConfig {
            minSdk = ProjectConfigs.minSdk
            targetSdk = ProjectConfigs.targetSdk

            versionCode = ProjectConfigs.versionCode
            versionName = ProjectConfigs.versionName

            vectorDrawables {
                useSupportLibrary = true
            }
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        signingConfigs {
            create("release") {
                storeFile = project.file(SigningConfigs.storeFile)
                storePassword = SigningConfigs.storePassword
                keyAlias = SigningConfigs.keyAlias
                keyPassword = SigningConfigs.keyPassword
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
                signingConfig = signingConfigs.getByName("release")
            }

            getByName("debug") {
                isDebuggable = true
                isMinifyEnabled = true
                signingConfig = signingConfigs.getByName("release")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            viewBinding = true
            dataBinding = true
            buildConfig = true
        }
    }
}

@Suppress("UnstableApiUsage")
internal fun configureAndroidLibrary(libraryExtension : LibraryExtension) {
    libraryExtension.apply {
        compileSdk = ProjectConfigs.compileSdk

        defaultConfig {
            minSdk = ProjectConfigs.minSdk
//            targetSdk = ProjectConfigs.targetSdk
            testInstrumentationRunner = ProjectConfigs.testInstrumentationRunner
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            viewBinding = true
            dataBinding = true
            buildConfig = true
        }
    }
}