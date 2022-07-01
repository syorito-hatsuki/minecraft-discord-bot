import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val pkg = "dev.syoritohatsuki.mdb"

plugins {
    kotlin("jvm") version "1.7.0"
    application
}

group = pkg
version = "2022.7.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jessecorbett:diskord-bot:2.1.4")
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