
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.*

val appTitle = "Minecraft Discord Bot"
val app = "mdb"
val domain = "dev.syoritohatsuki"
val pkg = "$domain.$app"

plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.0"
    application
}

group = pkg
version = SimpleDateFormat("yyyy.M.d").format(Date())

repositories {
    maven {
        name = "OSS"
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    mavenCentral()
}

dependencies {
    implementation("com.jessecorbett:diskord-bot:3.0.0-SNAPSHOT")

    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-cio:2.0.3")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")
}

val createBuildClassFile = task("createBuildClassFile") {
    doFirst {
        val packagePath = pkg.replace(".", File.separator)
        val path = "src${File.separator}" +
                "main${File.separator}" +
                "kotlin${File.separator}" +
                packagePath + File.separator

        File(path, "Build.kt").writeText(
            "package $pkg\n\n" +
                    "object Build {\n" +
                    "    const val APP_NAME = \"$app\"\n" +
                    "    const val APP_TITLE = \"$appTitle\"\n" +
                    "    const val BOT_VERSION = \"$version\"\n" +
                    "}"
        )
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_18.majorVersion
    dependsOn(createBuildClassFile)

}

application {
    mainClass.set("${group}.MainKt")
}