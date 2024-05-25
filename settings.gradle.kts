//plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
//}

pluginManagement {
    // Include 'plugins build' to define convention plugins.
    includeBuild("build-logic")
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "e-commerce-msa-kotlin"
include("product-service")
include("order-service")
include("notification-service")
include("api-gateway")
