plugins {
    `kotlin-dsl`
}

group = "com.zss.sample.modularization.buildlogic"

dependencies {
    // gradle
    implementation("com.android.tools.build:gradle:8.0.0")
    //kotlin-gradle-plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
}

gradlePlugin {
    plugins {
        register("sampleAndroidApplication") {
            id = "sample.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("sampleLibraryApplication") {
            id = "sample.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
