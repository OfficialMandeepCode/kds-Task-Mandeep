plugins {
    id("com.android.application")
    kotlin("android")
//    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MIN_SDK_VERSION
        targetSdk = AppConfig.TARGET_SDK_VERSION
        versionCode = AppConfig.VERSION_ID
        versionName = AppConfig.VERSION_NAME
        testInstrumentationRunner = AppConfig.ANDROID_TEST_INSTRUMENTATION
        javaCompileOptions.annotationProcessorOptions.arguments["dagger.hilt.disableModulesHaveInstallInCheck"] =
            "true"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
        buildConfigField("String","BASE_URL", "\"https://api.openweathermap.org\"")
    }

    buildTypes {
        getByName("release") {
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
//        jvmTarget = 11.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Core.materialDesign)
    implementation(Dependencies.Core.timber)
    implementation(Dependencies.Core.coroutine)
    implementation(Dependencies.Core.navigationUi)
    implementation(Dependencies.Core.navigationFragment)
    implementation(Dependencies.Core.ktxViewModel)
    implementation(Dependencies.Core.ktxLiveData)
    implementation(Dependencies.Core.ktxLifeCycleScope)
    implementation("androidx.fragment:fragment-ktx:1.5.3")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.annotation:annotation-experimental:1.3.0")

    kapt(Dependencies.Kotlin.kotlinxMetaData)
    implementation(Dependencies.Hilt.hilt)

    implementation("androidx.room:room-runtime:2.4.3")
    annotationProcessor("androidx.room:room-compiler:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt ("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    kapt(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Core.leakCanary)
}
kapt {
    correctErrorTypes = true
}