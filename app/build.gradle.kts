plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.ramsoft.dogsearchapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ramsoft.dogsearchapp"
        minSdk = 31
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    dataBinding{
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    //Room
    implementation(libs.room)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    //OkHTTP
    implementation(libs.okHttp)
    implementation(libs.logging.interceptor)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt)
    ksp(libs.hilt.compiler)

    //Coroutines
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    //LifeCycle , Livedata , ViewModel , Navigation
    implementation(libs.bundles.feature.ui)

    //Coil
    implementation(libs.coil)

    //DataBinding
    implementation(libs.androidx.databinding)
    kapt(libs.databinding.compiler)
    //Fragment
    implementation(libs.androidx.fragment)



    //Testing.....

    // coroutines-test
    testImplementation(libs.coroutines.test)
    // mockito-test
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inlinee)
    testImplementation(libs.mockito.kotlin)
    // Junit-test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //hilt-android-testing
    implementation(libs.hilt.android.testing)
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.compiler)

    //androidx-arch-core
    testImplementation(libs.androidx.arch.core)

}