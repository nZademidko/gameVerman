// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.com_android_tools_build_gradle)
        classpath(Dependencies.google_services)
        classpath(Dependencies.firebase_crashlytics_gradle)
        classpath(Dependencies.kotlin_gradle_plugin)
        classpath(Dependencies.navigation_safe_args_gradle_plugin)
        classpath(Dependencies.hilt_gradle_plugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}