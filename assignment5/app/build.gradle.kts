plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.wafflestudio.assignment5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wafflestudio.assignment5"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.5.4")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.material:material-ripple:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    val fragment_version = "1.6.2"

    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    // Kotlin Fragment
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
    implementation ("com.github.skydoves:landscapist-glide:1.4.7")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.compose.material:material-icons-extended:1.5.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}