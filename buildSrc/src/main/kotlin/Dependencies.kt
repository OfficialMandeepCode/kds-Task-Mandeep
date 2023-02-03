/**
 * App Name: KDS Mandeep
 * Package:
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/
object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinxMetaData =
            "org.jetbrains.kotlinx:kotlinx-metadata-jvm:${Versions.kotlinxMetaData}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Core {
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val ktxViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTXLifecycle}"
        const val ktxLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.KTXLifecycle}"
        const val ktxLifeCycleScope =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.KTXLifecycle}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }


    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }


}