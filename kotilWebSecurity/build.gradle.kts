import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.12"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("com.epages.restdocs-api-spec")  version "0.16.0"
}

group = "kr.pe.deity"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

//    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")

    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.16.2")
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openapi3 {
    this.setServer("http://localhost:7090")
    title = "My API"
    description = "An ecommerce sample demonstrating restdocs-api-spec"
    version ="0.1.0"
    format = "yaml" // or json
}

tasks.register<Copy>("copyOasToSwagger") {
    // 기존 OAS 파일 삭제
    delete("src/main/resources/static/docs/openapi3.yaml")
    delete("src/main/resources/static/docs/postman-collection.json")
    from("$buildDir/api-spec/openapi3.yaml") // 복제할 OAS 파일 지정
    from("$buildDir/api-spec/postman-collection.json") // 복제할 OAS 파일 지정
    into("src/main/resources/static/docs/.") // 타겟 디렉터리로 파일 복제
    dependsOn("openapi3") // openapi3 Task가 먼저 실행되도록 설정
    dependsOn("postman")
}

//openapi3 {
//    server = 'http://localhost:9020'
//    title = 'My API'
//    description = 'An ecommerce sample demonstrating restdocs-api-spec'
//    version = '0.1.0'
//    format = 'yaml'
//
//    copy{
//        from 'build/api-spec'
//        into 'src/main/resources/static/docs'
//    }
//}