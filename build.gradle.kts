// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.sonarqube)
}

sonarqube {
    properties {
        property("sonar.projectKey", "app-astro-daily-clean")
        property("sonar.projectName", "AppAstroDailyClean")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.login", project.findProperty("sonar.login") ?: "")
    }
}
