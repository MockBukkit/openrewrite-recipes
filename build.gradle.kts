import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("org.openrewrite.build.recipe-library-base") version "latest.release"

    id("com.vanniktech.maven.publish") version "0.29.0"

    // Configures artifact repositories used for dependency resolution to include maven central and nexus snapshots.
    // If you are operating in an environment where public repositories are not accessible, we recommend using a
    // virtual repository which mirrors both maven central and nexus snapshots.
    id("org.openrewrite.build.recipe-repositories") version "latest.release"

    // Only needed when you want to apply the OpenRewriteBestPractices recipe to your recipes through
    // ./gradlew rewriteRun -Drewrite.activeRecipe=org.openrewrite.recipes.OpenRewriteBestPractices
    id("org.openrewrite.rewrite") version "latest.release"
}

group = "org.mockbukkit.rewrite"
version = rewriteVersion()
description = "Rewrite recipes for Mockbukkit 4.0"

dependencies {
    // The bom version can also be set to a specific version
    // https://github.com/openrewrite/rewrite-recipe-bom/releases
    implementation(platform("org.openrewrite.recipe:rewrite-recipe-bom:latest.release"))

    implementation("org.openrewrite:rewrite-java")
    implementation("org.openrewrite.recipe:rewrite-java-dependencies")
    implementation("org.openrewrite:rewrite-yaml")
    implementation("org.openrewrite.meta:rewrite-analysis")
    implementation("org.assertj:assertj-core:3.24.2")
    runtimeOnly("org.openrewrite:rewrite-java-21")


    // Need to have a slf4j binding to see any output enabled from the parser.
    runtimeOnly("ch.qos.logback:logback-classic:1.5.12")

    // Contains the OpenRewriteBestPractices recipe, which you can apply to your recipes
    rewrite("org.openrewrite.recipe:rewrite-recommendations:latest.release")
}

mavenPublishing {
    coordinates(project.group.toString(), "openrewrite-recipes", project.version.toString())

    pom {
        description.set("A set of OpenRewrite recipes designed to help developers refactor projects that use MockBukkit.")
        name.set("openrewrite-recipes")
        url.set("https://github.com/MockBukkit/openrewrite-recipes")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://raw.githubusercontent.com/MockBukkit/openrewrite-recipes/refs/heads/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("thelooter")
                name.set("Eve Kolb")
                email.set("me@thelooter.de")
            }
            developer {
                id.set("thorinwasher")
                name.set("Hjalmar Gunnarsson")
                email.set("officialhjalmar.gunnarsson@outlook.com")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/MockBukkit/openrewrite-recipes.git")
            developerConnection.set("scm:git:ssh://github.com:MockBukkit/openrewrite-recipes.git")
            url.set("https://github.com/MockBukkit/openrewrite-recipes")
        }
    }
    if (!project.gradle.startParameter.taskNames.any { it.contains("publishToMavenLocal") }) {
        signAllPublications()
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
}


val backupVersion = "1.0.0"

fun rewriteVersion(): String = System.getenv("REWRITE_VERSION")
    ?.let { it + (System.getenv("CI")?.let { "" } ?: "-dev") }
    ?: (backupVersion + (System.getenv("CI")?.let { "" } ?: "-dev"))
