plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.serialization)
    id("org.sonarqube") version "7.0.1.6134"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
}

android {
    namespace = "com.jmarser.vehiclemanager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jmarser.vehiclemanager"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.2.21"
    }

    packaging {
        resources {
            excludes +=
                setOf(
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1",
                    "META-INF/licenses/**",
                    "META-INF/DEPENDENCIES",
                    "META-INF/NOTICE",
                    "META-INF/NOTICE.txt",
                    "META-INF/LICENSE",
                    "META-INF/LICENSE.txt",
                    "META-INF/ASL2.0",
                    "META-INF/NOTICE.md",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md",
                )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.foundation)
    implementation(libs.material3)
    implementation(libs.androidx.junit.ktx)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.animation)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.google)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Coil
    implementation(libs.coil.compose)

    // --- Unit tests (JVM) ---
    testImplementation(libs.junit) // junit 4.13.2 (ya tienes)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(kotlin("test"))

    // --- Android instrumented tests (androidTest) ---
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.mockk.android)

    // Hilt testing (annotation processor + testing artifact)
    kaptAndroidTest(libs.hilt.android.compiler) // si usas kapt (o ksp), para generación de Hilt en tests
}

kapt {
    correctErrorTypes = true
}
