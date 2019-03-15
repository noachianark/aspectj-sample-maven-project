# AspectJ Sample Maven Project
### AspectJ
AspectJ is an aspect-oriented programming (AOP) extension created at PARC for the Java programming language. It is available in Eclipse Foundation open-source projects, both stand-alone and integrated into Eclipse. AspectJ has become a widely used de facto standard for AOP by emphasizing simplicity and usability for end users. It uses Java-like syntax, and included IDE integrations for displaying crosscutting structure since its initial public release in 2001.

### About
This project is an sample maven project about how to integrate an AspectJ use intelliJ idea

### Notice
1. add dependency
```xml
<dependency>
   <groupId>org.aspectj</groupId>
   <artifactId>aspectjrt</artifactId>
   <version>1.8.9</version>
</dependency>
```

2. add mojo plugin under <build><plugins> tag
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>aspectj-maven-plugin</artifactId>
            <version>1.10</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <complianceLevel>1.8</complianceLevel>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
