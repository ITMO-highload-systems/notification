plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("kapt") version "1.9.10"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"
val testContainersVersion = "1.20.1"
val mapstructVersion = "1.6.0"
val flyWayVersion = "10.10.0"
val postgresqlVersion = "42.7.3"
val jacksonVersion = "2.17.2"
val kotlinJetBrainsVersion = "2.0.20"
val cloudVersion = "4.1.3"
val wiremockVersion = "3.9.2"
val jettyServer = "11.0.24"
val netflixVersion = "2.2.10.RELEASE"
val springDataJdbc = "3.3.5"
val jjwtApiVersion = "0.11.2"
val jjwtImplVersion = "0.11.5"
val jjwtJacksonVersion = "0.11.1"
val springSecurityTestVersion = "6.3.4"
val cloudFeignVersion = "1.4.7.RELEASE"
val cloudConfigVersion = "4.1.3"
val circuitBreakerVersion = "3.1.2"
val webmvcUiVersion = "2.6.0"
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.cloud:spring-cloud-starter-config:$cloudConfigVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$webmvcUiVersion")
    implementation("io.jsonwebtoken:jjwt-api:$jjwtApiVersion")



    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtImplVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtJacksonVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlinJetBrainsVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
