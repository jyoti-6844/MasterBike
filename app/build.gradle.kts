plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "ardents.in.masterbike"
    compileSdk = 34

    defaultConfig {
        applicationId = "ardents.in.masterbike"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures{
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.22")
    implementation ("com.github.bumptech.glide:glide:4.9.0")
    //LifeCycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.google.code.gson:gson:2.8.5")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.0")
    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")
}