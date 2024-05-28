plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("kotlin-kapt")
}

android {
	namespace = "com.keunyoung.rxproject"
	compileSdk = 34
	
	defaultConfig {
		applicationId = "com.keunyoung.rxproject"
		minSdk = 28
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
		dataBinding = true
	}
}

//noinspection UseTomlInstead
dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	
	
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.squareup.retrofit2:retrofit:2.11.0")
	implementation("com.squareup.retrofit2:converter-gson:2.11.0")
	implementation("io.coil-kt:coil:2.4.0")
	implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
	implementation("io.reactivex.rvjava3:rxandroid:3.1.8")
	implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}