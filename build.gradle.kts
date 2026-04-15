plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "7.2.3.7755"
    id("info.solidsoft.pitest") version "1.19.0-rc.3"
    id("maven-publish")
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

    implementation("org.springframework.boot:spring-boot-h2console")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("io.cucumber:cucumber-java:7.18.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.0")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

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

sonar {
    properties {
        property("sonar.projectKey", "chokkoramo_Registro-Estudiantes")
        property("sonar.organization", "chokkoramo")
        property("sonar.token", System.getenv("SONAR_TOKEN"))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://pkgs.dev.azure.com/Chokkoramo/d02376a2-5ba5-4351-9537-67bc863f5059/_packaging/ArtifactFeed/maven/v1")
            name = "ArtifactFeed"
            credentials {
                username = "ArtifactFeed"
                password = project.findProperty("TOKEN_ARTIFACT_FEED")?.toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

tasks.withType<GenerateModuleMetadata> {
    suppressedValidationErrors.add("dependencies-without-versions")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("file.encoding", "UTF-8")
}

tasks.register<Test>("acceptanceTest") {
    useJUnitPlatform()
    description = "Runs Cucumber acceptance tests."
    group = "verification"

    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath

    systemProperty("cucumber.plugin", "pretty")
}