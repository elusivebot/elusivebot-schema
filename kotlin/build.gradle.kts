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

repositories {
    mavenCentral()
}

group = "com.sirnuke.elusivebot"

apply<JSONSchemaCodegenPlugin>()

plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.serialization") version "2.0.10"
    `java-library`
    `maven-publish`
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
}

java {
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

if (project.hasProperty("internalMavenUrl")) {
    val internalMavenUsername: String by project
    val internalMavenPassword: String by project
    val internalMavenUrl: String by project

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
        repositories {
            maven {
                credentials {
                    username = internalMavenUsername
                    password = internalMavenPassword
                }
                val releasesRepoUrl = "$internalMavenUrl/releases/"
                val snapshotsRepoUrl = "$internalMavenUrl/snapshots/"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                name = "Internal-Maven-Publish"
            }
        }
    }

    repositories {
        maven {
            credentials {
                username = internalMavenUsername
                password = internalMavenPassword
            }
            url = uri("$internalMavenUrl/releases")
            name = "Internal-Maven-Releases"
        }
    }

    repositories {
        maven {
            credentials {
                username = internalMavenUsername
                password = internalMavenPassword
            }
            url = uri("$internalMavenUrl/snapshots")
            name = "Internal-Maven-Snapshots"
        }
    }
} else {
    repositories {
        mavenLocal()
    }
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
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
