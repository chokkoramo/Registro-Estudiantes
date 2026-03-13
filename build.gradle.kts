plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "7.1.0.6387"
    id("info.solidsoft.pitest") version "1.19.0-rc.3"
}

group = "juanca"
version = "0.0.1-SNAPSHOT"
description = "RegistroEstudiantes"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    pitest("org.pitest:pitest-junit5-plugin:1.2.1")
}

pitest {

    pitestVersion.set("1.15.0")
    junit5PluginVersion.set("1.2.1")

    targetClasses.set(listOf("juanca.registroestudiantes.*"))
    targetTests.set(listOf("juanca.registroestudiantes.*"))

    threads.set(4)
    outputFormats.set(listOf("HTML"))
    timestampedReports.set(false)
}

dependencies {

    implementation("org.springframework.boot:spring-boot-h2console")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sonar {
    properties {
        property("sonar.projectKey", "Registro-Estudiantes")
        property("sonar.projectName", "Registro Estudiantes")
        property("sonar.host.url", "http://localhost:9000" )
        property("sonar.login", project.findProperty("sonarToken") ?: "")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}