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
<plugins>
   <plugin>
      <groupId>org.openrewrite.maven</groupId>
      <artifactId>rewrite-maven-plugin</artifactId>
      <version>5.42.2</version>
      <!--Add the recipe source to your project’s rewrite configuration-->
      <configuration>
         <activeRecipes>
            <recipe>org.mockbukkit.rewrite.PackageRename</recipe>
            <recipe>org.mockbukkit.rewrite.ClassRename</recipe>
         </activeRecipes>
      </configuration>
      <!--Add the Mockbukkit recipes-->
      <dependencies>
         <dependency>
            <groupId>org.mockbukkit.rewrite</groupId>
            <artifactId>rewrite-recipes</artifactId>
            <version>1.0.0</version>
         </dependency>
      </dependencies>
   </plugin>
   <!-- Other plugins-->
</plugins>
```

For **Gradle**:

Groovy DSL
```groovy
plugins {
   id("org.openrewrite.rewrite") version "6.x.x"
}

dependencies {
   // Add the Mockbukkit recipes
   rewrite("org.mockbukkit.rewrite:rewrite-recipes:1.0.0")
}

// Add the recipe source to your project’s rewrite configuration
rewrite {
   activeRecipe("org.mockbukkit.rewrite.PackageRename")
   activeRecipe("org.mockbukkit.rewrite.ClassRename")
}
```

Kotlin DSL
```kotlin
plugins {
   id("org.openrewrite.rewrite") version "6.x.x"
}

dependencies {
   // Add the Mockbukkit recipes
   rewrite("org.mockbukkit.rewrite:rewrite-recipes:1.0.0")
}

// Add the recipe source to your project’s rewrite configuration
rewrite {
   activeRecipe("org.mockbukkit.rewrite.PackageRename")
   activeRecipe("org.mockbukkit.rewrite.ClassRename")
}
```

### Usage
Run OpenRewrite to refactor your code
   
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