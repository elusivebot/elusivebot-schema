import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegen
import net.pwall.json.kotlin.codegen.gradle.JSONSchemaCodegenPlugin

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("net.pwall.json:json-kotlin-gradle:0.107")
    }
}

apply<JSONSchemaCodegenPlugin>()

// TODO: Make this work better with KeyValue.ts
configure<JSONSchemaCodegen> {
    configFile.set(file("json-schema-codegen.config.json"))
    outputDir.set(file("pkg/src/msgs"))
    inputs {
        inputFile(file("../schema"))
    }
}

tasks.register<DefaultTask>("build") {
  group = "build"
  dependsOn("generate")
}

tasks.register<Delete>("clean") {
    delete("pkg/src/msgs")
}
