# ishtech-base-jpa
A foundational Spring Boot JPA library providing a reusable boilerplate for entity, repository, service, specification, and mapper layers.
It encapsulates common CRUD patterns, filtering mechanisms, and mapping logic through well-structured base classes and interfaces.
This helps keep business modules lightweight, letting them focus only on domain-specific logic while inheriting standardized behavior and conventions.

## Usage

- Note: in pom.xml / build.gradle put required version number

### Maven

```
<dependency>
	<groupId>fi.ishtech.base</groupId>
	<artifactId>ishtech-base-jpa</artifactId>
	<version>${ishtech-base-jpa.version}</version>
</dependency>

```

### Gradle

```
implementation("fi.ishtech.base:ishtech-base-jpa:${ishtechBaseJpaVersion}")
```

## Deploy to Sonatype Central

```
./mvnw clean deploy -P gpg -P central-publishing
```
