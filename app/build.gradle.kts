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
	
	testOptions {
		unitTests.isReturnDefaultValues = true
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
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
	
	implementation("io.coil-kt:coil:2.4.0")
	
	implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
	implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
	implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
	
	implementation("androidx.fragment:fragment-ktx:1.7.1")
	
	// 테스트를 위한 의존성 추가
	testImplementation("junit:junit:4.13.2")
	testImplementation("org.mockito:mockito-core:3.12.4")
	testImplementation("org.mockito:mockito-inline:3.12.4")
	testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
	testImplementation("net.bytebuddy:byte-buddy:1.10.22")
	testImplementation("net.bytebuddy:byte-buddy-agent:1.10.22")
	
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
	androidTestImplementation("org.mockito:mockito-android:3.12.4")
	androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
	
}