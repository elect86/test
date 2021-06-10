
plugins {
    `java-library`
    kotlin("jvm") version "1.5.10"
    application
    id("elect86.jextract") //version "0.1.6"
//    id("io.github.krakowski.jextract") version "0.1.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    implementation(files("opengl.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
//    mainClass.set("Teapot")
    applicationDefaultJvmArgs += "-Djava.library.path=.:/usr/lib/x86_64-linux-gnu"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
}

tasks {

    withType<JavaCompile>().configureEach {
        options.isIncremental = false
//        options.
    }

    compileJava {
//        inputs.property("moduleName", moduleName)
        doFirst {
            options.compilerArgs = options.compilerArgs + listOf(
                "--add-modules", "jdk.incubator.foreign")
//            classpath = files()
        }
    }


    jextract {

        header("/usr/include/GL/glut.h") {
            // The library name
            libraries.addAll("glut", "GLU", "GL")

            // The package under which all source files will be generated
            targetPackage.set("opengl")

            includes.add("/usr/lib/x86_64-linux-gnu")

            // The generated class name
//            className = 'Linux'
        }
    }
}
//println(project.properties["org.gradle.java.installations.paths"])
