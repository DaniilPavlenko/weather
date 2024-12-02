import com.android.build.api.dsl.ApplicationDefaultConfig
import java.util.Properties

plugins {
    id("dpav.android.application")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "ru.dpav.weather"

    defaultConfig {
        applicationId = "ru.dpav.weather"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        configureOpenWeatherApi()
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            // Just for simplicity. In real projects I read 'keystore.properties'.
            signingConfig = signingConfigs.named("debug").get()

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.play.services.location)

    // Retrofit
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.retrofit)

    // OpenStreetMap
    implementation(libs.osm.map)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

private fun ApplicationDefaultConfig.configureOpenWeatherApi() {
    val propertiesFileName = "local.properties"
    val properties = Properties().apply {
        load(project.rootProject.file(propertiesFileName).inputStream())
    }

    val apiKeyPropertyName = "openweather.apikey"
    val openWeatherApiKey: String? = properties.getProperty(apiKeyPropertyName)

    check(openWeatherApiKey?.isNotBlank() == true) {
        "OpenWeather API configuration is missed. " +
            "Provide the API key in the '$apiKeyPropertyName' property " +
            "in the root '$propertiesFileName' file. " +
            "Don't have a key? Get it here - https://home.openweathermap.org/api_keys"
    }

    buildConfigField("String", "OPEN_WEATHER_API_KEY", "\"$openWeatherApiKey\"")
}
