plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "7.2.3.7755"
    id("info.solidsoft.pitest") version "1.19.0-rc.3"
    id("maven-publish")
    id("jacoco")
}

group = "juanca"
version = "0.0.1-SNAPSHOT"
description = "RegistroEstudiantes"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencyLocking {
    lockAllConfigurations()
}

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {


    implementation("org.springframework.boot:spring-boot-h2console")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation(libs.playwright)
    testImplementation(libs.cucumber.java)
    testImplementation(libs.cucumber.junit)
    testImplementation(libs.junit.plataform)
    testImplementation(libs.mockito.core)

    testRuntimeOnly(libs.bytebuddy)
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

jacoco {
    toolVersion = "0.8.11"
}

tasks.withType<Test> {
    useJUnitPlatform()

    jvmArgs(
        "-javaagent:${configurations.testRuntimeClasspath.get()
            .find { it.name.contains("byte-buddy-agent") }?.absolutePath}"
    )

    systemProperty("file.encoding", "UTF-8")
}

tasks.named<Test>("test") {
    exclude("**/runners/**")
}

tasks.register<Test>("acceptanceTest") {
    useJUnitPlatform()
    description = "Runs Cucumber acceptance tests."
    group = "verification"

    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath

    include("**/runners/RunCucumberTest.class")

    systemProperty("cucumber.plugin", "pretty")

    extensions.configure<JacocoTaskExtension> {
        isEnabled = true
    }
}

tasks.register<JavaExec>("installPlaywrightBrowsers") {
    description = "Installs the Chromium browser required by Playwright acceptance tests."
    group = "verification"

    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("com.microsoft.playwright.CLI")
    args("install", "chromium")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    executionData.setFrom(
        layout.buildDirectory.asFileTree.matching {
            include("jacoco/test.exec")
        }
    )

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "chokkoramo_Registro-Estudiantes")
        property("sonar.organization", "chokkoramo")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token", System.getenv("SONAR_TOKEN"))

        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/test/jacocoTestReport.xml"
        )

        property("sonar.pitest.reportsDirectory", "build/reports/pitest")
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
            name = "ArtifactFeed"
            url = uri("https://pkgs.dev.azure.com/Chokkoramo/d02376a2-5ba5-4351-9537-67bc863f5059/_packaging/ArtifactFeed/maven/v1")
            credentials {
                username = "ArtifactFeed"
                password = System.getenv("TOKEN_ARTIFACT_FEED")
            }
        }
    }
}

tasks.withType<GenerateModuleMetadata> {
    suppressedValidationErrors.add("dependencies-without-versions")
}
