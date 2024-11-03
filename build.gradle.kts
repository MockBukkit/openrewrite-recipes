plugins {
    id("org.openrewrite.build.recipe-library-base") version "latest.release"

    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("signing")

    // Configures artifact repositories used for dependency resolution to include maven central and nexus snapshots.
    // If you are operating in an environment where public repositories are not accessible, we recommend using a
    // virtual repository which mirrors both maven central and nexus snapshots.
    id("org.openrewrite.build.recipe-repositories") version "latest.release"

    // Only needed when you want to apply the OpenRewriteBestPractices recipe to your recipes through
    // ./gradlew rewriteRun -Drewrite.activeRecipe=org.openrewrite.recipes.OpenRewriteBestPractices
    id("org.openrewrite.rewrite") version "latest.release"
}

group = "org.mockbukkit.rewrite"
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
    runtimeOnly("ch.qos.logback:logback-classic:1.2.+")

    // Contains the OpenRewriteBestPractices recipe, which you can apply to your recipes
    rewrite("org.openrewrite.recipe:rewrite-recommendations:latest.release")
}

signing {
    sign(publishing.publications)
}

nexusPublishing {
    this.repositories {
        sonatype {
            username.set(findProperty("OSSRH_USERNAME") as String?)
            password.set(findProperty("OSSRH_PASSWORD") as String?)
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "openrewrite-recipes"
            from(components.getByName("java"))
            pom {
                description.set("A set of OpenRewrite recipes designed to help developers refactor projects that use MockBukkit.")
                url.set("https://github.com/MockBukkit/openrewrite-recipes")
                scm {
                    connection.set("scm:git:git://github.com/MockBukkit/openrewrite-recipes.git")
                    developerConnection.set("scm:git:ssh://github.com:MockBukkit/openrewrite-recipes.git")
                    url.set("https://github.com/MockBukkit/openrewrite-recipes")
                }
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
                    }
                    developer {
                        id.set("thorinwasher")
                        name.set("Hjalmar Gunnarsson")
                        email.set("officialhjalmar.gunnarsson@outlook.com")
                    }
                }
            }
        }
    }
}
