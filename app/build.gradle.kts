import kotlin.jvm.Throws

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.gradletest"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.gradletest"
        minSdk = 28
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

tasks.register<HelloWorldTask>("hello") {
    greeting = "Hello, Gradle!"
}

tasks.register<FileClassesTask>("fileClasses") {
    input = "src/main/java/com/example/gradletest/MainActivity.kt"
    outputFile = layout.buildDirectory.file("files.txt")
}


tasks.register<ProducerTask>("producer") {
    // during the Configuration phase: Gradle attempts to read that file immediately.
    // Because the task that creates that file hasn't run yet (it only runs in the Execution phase)
    outputFile = layout.buildDirectory.file("result.txt").get()
}

tasks.register<ConsumerTask>("consumer") {
    dependsOn("producer")

    // will fail since the result is not available at configuration time, but we can read it at execution time
    // so we need to defer the reading of the result to execution time
    val resultFile: RegularFileProperty = tasks.named<ProducerTask>("producer").get().outputFile

    val foo = resultFile.map { it.asFile.readText() }

    result = foo
}
