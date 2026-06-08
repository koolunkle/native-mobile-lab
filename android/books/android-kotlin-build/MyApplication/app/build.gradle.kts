plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 35
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

    // 코틀린 기반의 안드로이드 젯팩 컴포넌트
    implementation(libs.androidx.core.ktx)
    // 하위 호환을 위한 지원 라이브러리와 젯팩 컴포넌트
    implementation(libs.androidx.appcompat)
    // 앱에 테마 및 스타일링을 적용하기 위한 머티리얼 디자인 구성 요소
    implementation(libs.material)
    // 안드로이드 메인 코드와 별도로 업데이트되는 액티비티
    implementation(libs.androidx.activity)
    // 안드로이드 메인 코드와 별도로 업데이트되는 ConstraintLayout ViewGroup
    implementation(libs.androidx.constraintlayout)
    // 단위 테스트용 라이브러리
    testImplementation(libs.junit)
    // UI 테스트
    androidTestImplementation(libs.androidx.junit)
    // 안드로이드 테스트를 만들기 위한 에스프레소 라이브러리
    androidTestImplementation(libs.androidx.espresso.core)
}