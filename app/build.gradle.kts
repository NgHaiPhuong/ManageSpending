plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.example.quan_ly_chi_tieu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quan_ly_chi_tieu"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding {
        enable = true
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.ui.desktop)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //MPChart
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-auth")// xác nhân danh tính người dùng
    implementation("com.google.firebase:firebase-storage") // lưu ảnh

    implementation("com.airbnb.android:epoxy:5.1.4")
    // Add the annotation processor if you are using Epoxy's annotations (recommended)
    kapt("com.airbnb.android:epoxy-processor:5.1.4")
    // Layout error alignment library for horizontal epoxy model
    implementation("com.github.rubensousa:gravitysnaphelper:2.2.2")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // scalable dp, sp library
    implementation("com.intuit.sdp:sdp-android:1.1.1")

    // lib for lottie animation
    implementation("com.airbnb.android:lottie:6.4.0")

    implementation ("com.android.support:appcompat-v7:27.1.0")
    implementation ("com.android.support:design:27.1.0")

    //CardView
    implementation("androidx.cardview:cardview:1.0.0")
    
    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation ("com.android.support:design:26.1.0")

    // Meow Bottom Nav
    implementation ("com.etebarian:meow-bottom-navigation:1.3.1")

    //Circle Image
    implementation ("de.hdodenhof:circleimageview:3.1.0")
}