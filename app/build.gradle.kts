plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.echo.kotlinlearning"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.echo.kotlinlearning"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview) // 可选
    // 1. 引入 OkHttp BOM（通过 Version Catalog 引用，统一版本）
    implementation(platform(libs.okhttp.bom))

    // 2. 引入 OkHttp 核心库 + 日志拦截器（无需写版本，由 BOM 统一管理）
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // 引入Simple XML（libs方式）
    implementation(libs.simple.xml)
    // 引入配套的stax-api（解决Android 9+解析异常）
//    implementation(libs.stax.api)
    // 若配置了 bundles，也可以一键引入：implementation(libs.bundles.okhttp)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}