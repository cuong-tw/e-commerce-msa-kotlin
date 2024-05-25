plugins {
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.23"
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.org.springframework.cloud.spring.cloud.starter.netflix.eureka.server)
    api(libs.org.springframework.cloud.spring.cloud.starter.sleuth)
    api(libs.org.springframework.cloud.spring.cloud.sleuth.zipkin)
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}