plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.marti_cv.minevera"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marti_cv.minevera"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.android.dagger.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    testOptions {
//        unitTests {
//            includeAndroidResources = true
//        }
//    }
}

dependencies {

    implementation("androidx.test:runner:1.5.2")
    testImplementation("junit:junit:4.13.2")
    val hilt_version="2.47"
    val room_version = "2.6.1"
    val retrofit_version="2.9.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //Dagger Hilt para inyeccion de dependencias
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:$hilt_version")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:$hilt_version")



    //LiveData para corrutinas
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

    //Room para persistencia de datos
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    //Mockito
    testImplementation ("org.mockito:mockito-core:5.8.0")
    testImplementation ("org.mockito:mockito-inline:5.2.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.2.1")

    //Robolecrtic
    testImplementation("org.robolectric:robolectric:4.11.1")
    //Hamcrest
    testImplementation("org.hamcrest:hamcrest:2.2")
    // Kotlin extensions for androidx.test.core
    testImplementation("androidx.test:core-ktx:1.5.0")
    // Kotlin extensions for androidx.test.ext.junit
    testImplementation("androidx.test.ext:junit-ktx:1.1.5")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}