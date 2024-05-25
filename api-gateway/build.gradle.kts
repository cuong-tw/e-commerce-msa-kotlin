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
    api(libs.org.springframework.boot.spring.boot.starter.oauth2.resource.server)
    api(libs.org.springframework.boot.spring.boot.starter.security)
    api(libs.org.springframework.boot.spring.boot.starter.webflux)
    api(libs.org.springframework.cloud.spring.cloud.starter.sleuth)
    api(libs.org.springframework.cloud.spring.cloud.sleuth.zipkin)
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}