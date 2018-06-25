# SPT Java Commons

[![circleci](https://img.shields.io/badge/circleci-spt--java--commons-brightgreen.svg)](https://circleci.com/gh/spt-oss/spt-java-commons)
[![maven central](https://img.shields.io/badge/maven_central-spt--java--commons-blue.svg)](https://mvnrepository.com/artifact/com.github.spt-oss/spt-java-commons)
[![javadoc](https://img.shields.io/badge/javadoc-spt--java--commons-blue.svg)](https://www.javadoc.io/doc/com.github.spt-oss/spt-java-commons)

* Custom libraries for Java projects

## Usage

## Use Java classes

* Add a dependency in your POM.

	```xml
	<dependency>
		<groupId>com.github.spt-oss</groupId>
		<artifactId>spt-java-commons</artifactId>
		<version>X.X.X</version>
	</dependency>
	```

### Enable settings for Eclipse

* Checkout the repository and import settings into your Eclipse.

	```bash
	src/main/resources/
	    spt/
	        settings/
	            checkstyle/
	                checkstyle.xml # For Checkstyle
	            eclipse/
	                platform/      # For Eclipse platform
	                project/       # For Eclipse projects
	```

### Enable Checkstyle for Maven

* Add a dependency in your POM.

	```xml
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-checkstyle-plugin</artifactId>
		<configuration>
			<configLocation>spt/settings/checkstyle/checkstyle.xml</configLocation>
		</configuration>
		<dependencies>
			<dependency>
				<groupId>com.puppycrawl.tools</groupId>
				<artifactId>checkstyle</artifactId>
				<version>X.X.X</version>
			</dependency>
			<dependency>
				<groupId>com.github.spt-oss</groupId>
				<artifactId>spt-java-commons</artifactId>
				<version>X.X.X</version>
			</dependency>
		</dependencies>
	</plugin>
	```

## License

* This software is released under the Apache License 2.0.
