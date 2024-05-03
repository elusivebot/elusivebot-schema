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

configure<JSONSchemaCodegen> {
    configFile.set(file("json-schema-codegen.config.json"))
    outputDir.set(file("pkg/src"))
    inputs {
        inputFile(file("../schema"))
    }
}

tasks.register<DefaultTask>("build") {
  group = "build"
  dependsOn("generate")
}
