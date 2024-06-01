package apigw

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration

@SpringBootApplication(exclude = [GsonAutoConfiguration::class, SpringDataWebAutoConfiguration::class])
open class ApiGatewayApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiGatewayApplication::class.java, *args)
}