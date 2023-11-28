rootProject.name = "elusivebot-schema"

pluginManagement {
  plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
  }
}

include("kotlin")
project(":kotlin").name = "elusivebot-schema"
