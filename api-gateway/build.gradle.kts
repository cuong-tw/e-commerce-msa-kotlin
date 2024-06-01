plugins {
    kotlin("jvm")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.org.springframework.cloud.spring.cloud.starter.gateway)
    api(libs.org.springframework.cloud.spring.cloud.starter.netflix.eureka.client)
    api(libs.org.springframework.cloud.spring.cloud.starter.sleuth)
    api(libs.org.springframework.cloud.spring.cloud.sleuth.zipkin)
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}