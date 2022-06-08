plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

apply {
    from("../defaultconfig.gradle")
}

android {
    defaultConfig {
        applicationId = "ru.susu.scsusu"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        named("debug") {
            isDebuggable = true
        }
    }
    testOptions {
        animationsDisabled = true
    }
}

dependencies {

    api(Dependencies.kotlin_stdlib)
    api(Dependencies.kotlinx_coroutines_core)
    api(Dependencies.appcompat)
    api(Dependencies.androidx_core)
    api(Dependencies.fragment)

    api(Dependencies.navigation_fragment)
    api(Dependencies.navigation_ui)
    api(Dependencies.material)
    api(Dependencies.constraintLayout)
    implementation(Dependencies.viewPager2)
    implementation(Dependencies.glide)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Dependencies.glide_compiler)

    api(Dependencies.lifecycle_viewmodel)
    api(Dependencies.lifecycle_livedata)

    api(Dependencies.retrofit)
    api(Dependencies.converter_gson)
    implementation(Dependencies.logging_interceptor)

    implementation(Dependencies.hilt)
    api(Dependencies.hilt_navigation_fragment)
    kapt(Dependencies.hilt_compiler)

    implementation(Dependencies.zxing_core)

    api(Dependencies.rmr_input_mask)
    api(Dependencies.rmr_view_binding)
    api(Dependencies.rmr_lifecycle_livedata)

    api(Dependencies.coil)
    api(Dependencies.coil_svg)

    implementation(Dependencies.epoxy)
    kapt(Dependencies.epoxy_processor)

    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room)
    kapt(Dependencies.room_compiler)

    implementation(Dependencies.activity)

    implementation(Dependencies.junit)
}
