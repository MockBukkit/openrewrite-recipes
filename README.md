# OpenRewrite Recipes for MockBukkit Refactoring

This repository contains a set of OpenRewrite recipes designed to help developers refactor projects that use [MockBukkit](https://github.com/Mockbukkit/MockBukkit). OpenRewrite enables automatic refactoring, making it easier to update code, enforce standards, and introduce best practices consistently.

## Overview

MockBukkit is a powerful library for unit testing Bukkit/Spigot plugins, providing mock implementations of core API classes. This set of OpenRewrite recipes helps developers transition older or deprecated usage of MockBukkit into modern patterns, ensuring cleaner and more maintainable test code.

## Features

- **Update Deprecated Classes**: Automatically replace deprecated classes in MockBukkit with their modern counterparts.

## Getting Started

### Prerequisites

- **Java 21**
- **Maven/Gradle** (for OpenRewrite)
- **MockBukkit 3.x or 4.x**

### Installation

Add OpenRewrite as a dependency to your project:

For **Maven**:

```xml
<dependency>
    <groupId>org.openrewrite</groupId>
    <artifactId>rewrite-maven-plugin</artifactId>
    <version>5.x.x</version>
</dependency>
```

For **Gradle**:

Groovy DSL
```groovy
plugins {
    id("org.openrewrite.rewrite") version "6.x.x"
}
```

Kotlin DSL

```kotlin
plugins {
    id("org.openrewrite.rewrite") version "6.x.x"
}
```

Add the Mockbukkit recipes:

For **Maven**

```xml
<dependency>
    <groupId>org.mockbukkit</groupId>
    <artifactId>rewrite-recipes</artifactId> 
    <version>1.0.0</version>
</dependency>
```

For **Gradle**:

Groovy DSL:
```groovy
dependencies {
    implementation("org.mockbukkit:rewrite-recipes:1.0.0") 
}
```

Kotlin DSL:
```kotlin
dependencies {
    implementation("org.mockbukkit:rewrite-recipes:1.0.0")
}
```

### Usage
To apply the recipes from this repository to your project:

1. Add the recipe source to your project’s rewrite configuration:
    
    For **Maven**:
    ```xml
    <configuration>
        <activeRecipes>
            <recipe>org.mockbukkit.rewrite.PackageRename</recipe>
            <recipe>org.mockbukkit.rewrite.ClassRename</recipe>
        </activeRecipes>
    </configuration>
    ```
    
    For **Gradle**:
    
    Groovy DSL:
    ```groovy
     rewrite {
       activeRecipe("org.mockbukkit.rewrite.PackageRename")
       activeRecipe("org.mockbukkit.rewrite.ClassRename")
     }
    ```
   Kotlin DSL:
    ```kotlin
     rewrite {
       activeRecipe("org.mockbukkit.rewrite.PackageRename")
       activeRecipe("org.mockbukkit.rewrite.ClassRename")
     }
    ```

2. Run OpenRewrite to refactor your code
   
   For **Maven**:
   ```shell
   mvn rewrite:run
   ```
   For **Gradle**:
   ```shell
   ./gradlew rewriteRun
   ```
   
### Included Recipes

- **PackageRename**: Rename the old `be.seeseemelk` package 
  to the new `org.mockbukkit package`
- **ClassRename**: Rename the Classes of several Mocks to use the new names

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for more Details