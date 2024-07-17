plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.coffeeshop.Main"
        )
    }
}

// To ensure the main class is included in the jar manifest
tasks.withType<Jar> {
    manifest {
        attributes(
                "Main-Class" to "com.coffeeshop.Main"
        )
    }
}