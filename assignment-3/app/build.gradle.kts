plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}



android {
    namespace ="com.assignment3"
    compileSdk = 34


    viewBinding{
        enable = true
    }

    defaultConfig {
        applicationId = "com.jutak.assignment3"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = "1.5"
    }

    kapt{
        generateStubs = true
    }



}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    //implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //implementation ("androidx.activity:activity-ktx:1.3.1")
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    implementation ("com.google.dagger:hilt-android:2.42")
   // implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //implementation ("com.google.dagger:hilt-android:2.x.x")
    //kapt ("com.google.dagger:hilt-compiler:2.x.x")

    //implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-compiler:2.42")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.activity:activity-ktx:1.3.1")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
