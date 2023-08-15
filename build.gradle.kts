import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    id("com.diffplug.spotless") version "6.20.0"
    application
}

group = "com.leg3nd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

application {
    mainClass.set("MainKt")
}

spotless {
    // optional: limit format enforcement to just the files changed by this feature branch
//    ratchetFrom("origin/main")

    format("misc") {
        // define the files to apply `misc` to
        target("*.gradle", "*.md", ".gitignore")

        // define the steps to apply to those files
        trimTrailingWhitespace()
        indentWithTabs() // or spaces. Takes an integer argument if you don't like 4
        endWithNewline()
    }

    kotlin {
        // version, userData and editorConfigOverride are all optional
        ktlint("0.50.0")
            .setEditorConfigPath("$projectDir/.editorconfig")
    }
}
