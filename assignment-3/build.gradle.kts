
plugins {
    kotlin("android") version "1.9.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0-beta01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")

    }
}
