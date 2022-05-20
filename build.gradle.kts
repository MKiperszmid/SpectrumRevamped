// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Build.androidBuildToolsVersion apply false
    id("com.android.library") version Build.androidBuildToolsVersion apply false
    id("org.jetbrains.kotlin.android") version Kotlin.version apply false
    id("org.jetbrains.kotlin.jvm") version Kotlin.version apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}