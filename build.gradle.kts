plugins {
    // This is a Java project...
    id("java")
    /*
        We have a bit more going on in this project (i.e. launching the JavaFX platform) so we need Gradle to run the
         project, instead of Intellij. Therefore, we have to tell Gradle that this is an application it can run.
    */
    id("application")
    // We are going to use a Gradle plugin to help us utilize and manage JavaFX in our project.
    id("org.openjfx.javafxplugin") version "0.1.0"
}

// Always should declare where we are getting code from.
repositories.mavenCentral()

// Should explicitly specify the Java version that we are using to match the JavaFX version.
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

 // Need to state the fully-qualified name of the class for Gradle to run.
application.mainClass = "TimerAppLauncher"

/*
    1. Should specify the JavaFX version we are using.
        - It is usually a good idea to keep the JavaFX version the same as the Java version.
    2. Need to specify all the JavaFX modules that we are using.
        - These are the two modules that we most commonly need to run a JavaFX application.

 */
javafx {
    version = "21.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}

// Notice that since we don't have any testing code in this project, we are not declaring anything related to JUnit.