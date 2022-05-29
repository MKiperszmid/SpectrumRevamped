plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.applicationId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        kotlinCompilerExtensionVersion = Compose.version
    }
    packagingOptions {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
            excludes.add("**/attach_hotspot_windows.dll")
            excludes.add("META-INF/licenses/ASM")
        }
    }
}

dependencies {

    implementation(Hilt.hiltAndroid)
    "kapt" (Hilt.hiltCompiler)

    implementation(project(Modules.core))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.Home.domain))
    implementation(project(Modules.Home.data))
    implementation(project(Modules.Home.presentation))
    implementation(project(Modules.Search.domain))
    implementation(project(Modules.Search.data))
    implementation(project(Modules.Search.presentation))


    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.tooling)
    implementation(Compose.toolingPreview)
    implementation(Compose.runtime)
    implementation(Compose.compiler)
    implementation(Compose.icons)
    implementation(Compose.activityCompose)
    implementation(Compose.viewModelCompose)
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigationCompose)

    implementation(AndroidX.core)
    implementation(AndroidX.lifecycleRuntime)
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.extJunit)
    androidTestImplementation(Testing.espresso)
    androidTestImplementation(Testing.junitUi)
}