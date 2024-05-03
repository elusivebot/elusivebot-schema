rootProject.name = "elusivebot-schema"

pluginManagement {
  plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
  }
}

include("kotlin", "typescript")
project(":kotlin").name = "elusivebot-schema"
project(":typescript").name = "elusivebot-schema-typescript"
