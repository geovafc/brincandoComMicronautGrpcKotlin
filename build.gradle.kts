import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
    id ("com.google.protobuf") version "0.8.14"
    id ("org.jetbrains.kotlin.plugin.jpa") version "1.5.10"

}

//plug-ins gRPC e protobuf:
val protocVersion = "3.12.2"
val grpcVersion="1.29.0"
val grpcKotlinVersion="0.1.2"


sourceSets {
    main {
        java {
            srcDirs ("build/generated/source/proto/main/grpc")
            srcDirs ("build/generated/source/proto/main/grpckt")
            srcDirs ("build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:${protocVersion}" }
    plugins {

        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }

        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${grpcKotlinVersion}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

//fim plug-ins gRPC e protobuf

version = "0.1"
group = "com.agiledeveloper.stockprices"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {plugins
        incremental(true)
        annotations("com.agiledeveloper.stockprices.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("io.grpc:grpc-kotlin-stub:${grpcKotlinVersion}")
    implementation("io.micronaut.grpc:micronaut-grpc-server-runtime")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")

    implementation("io.micronaut:micronaut-validation")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.micronaut.discovery:micronaut-discovery-client")

    annotationProcessor("io.micronaut.data:micronaut-data-processor:2.4.3")
}


application {
    mainClass.set("com.agiledeveloper.stockprices.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("1.8")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }


}

//allOpen {
//    annotation("Entity")
//}




