import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.researchgate.release.ReleaseExtension

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("net.pwall.json:json-kotlin-gradle:0.96.1")
    }
}

apply<JSONSchemaCodegenPlugin>()

plugins {
    id("net.researchgate.release") version "3.0.2"
}

configure<ReleaseExtension> {
  tagTemplate.set("v\${version}")
}
