plugins {
    java
    application
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("net.ltgt.errorprone") version "3.1.0"
}

group = "com.github.bgalek"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    errorprone("com.google.errorprone:error_prone_core:2.26.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.session:spring-session-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.sentry:sentry-spring-boot-starter-jakarta:7.8.0")
    implementation("com.azure:azure-ai-openai:1.0.0-beta.8")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus:1.12.5")
    runtimeOnly("org.postgresql:postgresql")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.processResources {
    dependsOn(":frontend:build")
}

tasks.jar {
    enabled = false
}

tasks.distZip {
    enabled = false
}

tasks.distTar {
    enabled = false
}
