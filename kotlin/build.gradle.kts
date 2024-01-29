import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("net.pwall.json:json-kotlin-gradle:0.96.1")
    }
}

repositories {
    mavenCentral()
}

group = "com.sirnuke.elusivebot"

apply<JSONSchemaCodegenPlugin>()

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    `java-library`
    `maven-publish`
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

configure<JSONSchemaCodegen> {
    configFile.set(file("json-schema-codegen.config.json"))
    outputDir.set(file("build/generated-sources/kotlin"))
    inputs {
        inputFile(file("../schema"))
    }
}

sourceSets.main {
    java.srcDirs("build/generated-sources/kotlin")
}
