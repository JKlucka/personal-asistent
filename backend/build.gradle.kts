plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
}

group = "com.assistant"
version = "0.0.1"

application {
    mainClass.set("com.assistant.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor server
    implementation("io.ktor:ktor-server-core-jvm:2.3.10")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.10")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.10")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.10")
    implementation("io.ktor:ktor-server-config-yaml:2.3.10")

    // Exposed + PostgreSQL
    implementation("org.jetbrains.exposed:exposed-core:0.50.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.50.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.50.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.50.1")
    implementation("org.postgresql:postgresql:42.7.3")

    // Connection pool
    implementation("com.zaxxer:HikariCP:5.1.0")

    // Flyway
    implementation("org.flywaydb:flyway-core:10.10.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.10.0")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.3")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.23")
}

kotlin {
    jvmToolchain(17)
}
