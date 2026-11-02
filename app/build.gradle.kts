plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// Build khusus guru: gradle assembleDebug -PteacherAi=true -PaiApiUrl=https://xxx.workers.dev
val teacherAi = (findProperty("teacherAi") ?: "false").toString().toBoolean()
val aiApiUrl = (findProperty("aiApiUrl") ?: "").toString()

android {
    namespace = "id.web.assessment.man19jkt"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.web.assessment.man19jkt"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // Domain induk; kode sekolah dipakai sebagai subdomain: https://<kode>.assessment.web.id/login
        buildConfigField("String", "BASE_DOMAIN", "\"assessment.web.id\"")
        buildConfigField("boolean", "TEACHER_AI", teacherAi.toString())
        buildConfigField("String", "AI_API_URL", "\"$aiApiUrl\"")
        if (teacherAi) applicationIdSuffix = ".guru" // terpasang terpisah dari aplikasi biasa
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}
