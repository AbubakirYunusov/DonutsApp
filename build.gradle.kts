// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
        maven {
            setUrl("https://jitpack.io")
        }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.1") // Updated Android Gradle Plugin dependency
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31") // Updated Kotlin plugin version
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}