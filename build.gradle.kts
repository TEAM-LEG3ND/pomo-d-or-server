import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val koinVersion by properties
val koinKspVersion by properties

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jmailen.kotlinter") version "3.16.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

group = "com.leg3nd"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("com.google.devtools.ksp")
        plugin("org.jmailen.kotlinter")
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

        implementation("ch.qos.logback:logback-classic:1.4.11")

        implementation("io.insert-koin:koin-core:$koinVersion")
        implementation("io.insert-koin:koin-annotations:$koinKspVersion")
        ksp("io.insert-koin:koin-ksp-compiler:$koinKspVersion")
    }

    tasks.lintKotlinMain {
        dependsOn("kspKotlin") // without this, it will fail to build with Gradle 8
// I have to convert the type `FileCollection` to `FileTree` here.
        source = (source - fileTree("$buildDir/generated")).asFileTree
    }

    tasks.formatKotlinMain {
        dependsOn("kspKotlin") // without this, it will fail to build with Gradle 8
// I have to convert the type `FileCollection` to `FileTree` here.
        source = (source - fileTree("$buildDir/generated")).asFileTree
    }

    tasks.whenTaskAdded {
//    println("adding task: $name")
        if (name == "lintKotlinGeneratedByKspKotlin" ||
            name == "lintKotlinGeneratedByKspTestKotlin" ||
            name == "formatKotlinGeneratedByKspKotlin" ||
            name == "formatKotlinGeneratedByKspTestKotlin"
        ) {
            enabled = false
        }
    }
}
