// AGP 9's built-in Kotlin support means org.jetbrains.kotlin.android is no longer applied;
// this classpath override bumps the Kotlin compiler AGP uses internally to match the
// version the Compose compiler plugin / KSP below are pinned to.
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.20")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}
