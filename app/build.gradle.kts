plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}


val composeVersion = "1.0.1"

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.holderzone.library.composescaffold"
        minSdk = 21
        targetSdk = 30
        versionCode = 7
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release").apply {
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

}

kapt {
    mapDiagnosticLocations = true
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    //hlit
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    implementation("com.google.dagger:hilt-android:2.38.1")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    //compose
    implementation("androidx.compose.compiler:compiler:$composeVersion")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    implementation("androidx.compose.compiler:compiler:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.ui:ui-util:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("com.google.accompanist:accompanist-pager:0.15.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.15.0")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation("androidx.compose.animation:animation:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08")
    implementation("io.coil-kt:coil-compose:1.3.0")

    //Glide
    implementation("com.google.accompanist:accompanist-glide:0.12.0")

    // network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:3.14.9")
    implementation("com.squareup.okhttp3:logging-interceptor:3.11.0")

    //引入MVVM架构
    //协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //Livedata + viewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    kapt("androidx.lifecycle:lifecycle-compiler:2.2.0")

    //room
    implementation("androidx.room:room-runtime:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")
    implementation("androidx.room:room-ktx:2.2.6")
    implementation("androidx.room:room-testing:2.2.6")

    // routing
    implementation("com.alibaba:arouter-api:1.5.1") {
        exclude(group = "com.android.support", module = "support-v4")
    }
    kapt("com.alibaba:arouter-compiler:1.5.1")

    //bugly
    implementation("com.tencent.bugly:crashreport_upgrade:1.5.23")

    //json
    implementation("com.squareup.moshi:moshi:1.12.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.8.0")

    //permissionx
    implementation("com.permissionx.guolindev:permission-support:1.4.0")

    //glide
    implementation("com.google.accompanist:accompanist-glide:0.12.0")

    //三方工具库
    api("io.github.jeremyliao:live-event-bus-x:1.8.0")
    implementation("com.blankj:utilcode:1.30.0")
    implementation("com.orhanobut:logger:2.2.0")

    //KotlinPoet
    implementation("com.squareup:kotlinpoet:1.10.2")
    //lottie
    implementation("com.airbnb.android:lottie-compose:4.2.0")

    //CameraX依赖模块
    val camerax_version = "1.0.0-rc05"
    implementation("androidx.camera:camera-core:$camerax_version")
    implementation("androidx.camera:camera-camera2:$camerax_version")
    implementation("androidx.camera:camera-lifecycle:$camerax_version")
    implementation("androidx.camera:camera-view:1.0.0-alpha24")
    implementation("androidx.camera:camera-view:1.0.0-alpha24")

    //work manager
    implementation("androidx.work:work-runtime:2.2.0")

}