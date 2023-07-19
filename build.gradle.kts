import org.jetbrains.compose.desktop.application.dsl.TargetFormat


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "de.jxdev.espdmx"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("de.jxdev.espdmx:lib:0.0.2")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

                implementation("org.jmdns:jmdns:3.5.1")
                implementation("com.google.code.gson:gson:2.9.0")

                implementation("com.github.weliem.blessed-bluez:blessed:0.61")

                implementation("io.ktor:ktor-server-core:2.3.1")
                implementation("io.ktor:ktor-server-netty:2.3.1")
                implementation("io.ktor:ktor-server-websockets:2.3.1")
                implementation("ch.qos.logback:logback-classic:1.4.8")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ESPDMX-Server-Desktop"
            packageVersion = "1.0.0"
        }
    }
}
