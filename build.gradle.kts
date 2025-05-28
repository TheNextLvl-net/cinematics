import io.papermc.hangarpublishplugin.model.Platforms
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta15"
    id("io.papermc.hangar-publish-plugin") version "0.1.3"
    id("de.eldoria.plugin-yml.paper") version "0.7.1"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.compileJava {
    options.release.set(21)
}

group = "net.thenextlvl.cinematics"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.thenextlvl.net/releases")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")

    implementation("net.thenextlvl.core:files:3.0.0")
    implementation("net.thenextlvl.core:i18n:3.2.0")
}

tasks.shadowJar {
    archiveBaseName.set("cinematics")
    relocate("org.bstats", "net.thenextlvl.cinematics.bstats")
}

paper {
    name = "Cinematics"
    main = "net.thenextlvl.cinematics.CinematicsPlugin"
    apiVersion = "1.21"
    description = "Create awesome looking cinematic animations and transitions"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    website = "https://thenextlvl.net"
    authors = listOf("NonSwag")
    foliaSupported = true
}

val versionString: String = project.version as String
val isRelease: Boolean = !versionString.contains("-pre")

val versions: List<String> = (property("gameVersions") as String)
    .split(",")
    .map { it.trim() }

hangarPublish { // docs - https://docs.papermc.io/misc/hangar-publishing
    publications.register("plugin") {
        id.set("Cinematics")
        version.set(versionString)
        channel.set(if (isRelease) "Release" else "Snapshot")
        apiKey.set(System.getenv("HANGAR_API_TOKEN"))
        platforms.register(Platforms.PAPER) {
            jar.set(tasks.shadowJar.flatMap { it.archiveFile })
            platformVersions.set(versions)
        }
    }
}