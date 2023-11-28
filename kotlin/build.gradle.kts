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

group = "com.degrendel.elusivebot"

apply<JSONSchemaCodegenPlugin>()

plugins {
  id("org.jetbrains.kotlin.jvm")
  `java-library`
}

java {
    withSourcesJar()
}

configure<JSONSchemaCodegen> {
  packageName.set("com.degrendel.elusivebot.schema")
  outputDir.set(file("build/generated-sources/kotlin"))
  inputs {
    inputFile(file("../schema"))
  }
}

sourceSets.main {
    java.srcDirs("build/generated-sources/kotlin")
}
