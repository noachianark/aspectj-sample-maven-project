# AspectJ Sample Maven Project
### AspectJ
AspectJ is an aspect-oriented programming (AOP) extension created at PARC for the Java programming language. It is available in Eclipse Foundation open-source projects, both stand-alone and integrated into Eclipse. AspectJ has become a widely used de facto standard for AOP by emphasizing simplicity and usability for end users. It uses Java-like syntax, and included IDE integrations for displaying crosscutting structure since its initial public release in 2001.

### About
This project is an sample maven project about how to integrate an AspectJ use intelliJ idea

### Key points
1. add dependency
```xml
<dependency>
   <groupId>org.aspectj</groupId>
   <artifactId>aspectjrt</artifactId>
   <version>1.8.9</version>
</dependency>
```

2. add mojo plugin under build/plugins tag
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
### The aspect implementation
1. Annotation. We use annotation to evaluate cost time of a function
```java
import java.lang.annotation.*;

@Target(ElementType.METHOD) //Annotation on the method 
@Retention(RetentionPolicy.RUNTIME) //
@Documented
public @interface Tracer {
}
```
2. Aspect class
```java
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnoAspect {
    private static final Logger logger = Logger.getLogger(AnnoAspect.class);

    @Around("@annotation(Tracer) && execution(* *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Long start = System.currentTimeMillis();
        //Default Object that we can use to return to the consumer
        Object returnObject = null;
        try {
            Thread.sleep(1000);
            returnObject = joinPoint.proceed();
            //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
        } catch (Throwable throwable) {
            //Here we can catch and modify any exceptions that are called
            //We could potentially not throw the exception to the caller and instead return "null" or a default object.
            throw throwable;
        }
        finally {
            Long end = System.currentTimeMillis();
            logger.info("cost time:"+(end - start));
        }
        return returnObject;
    }

}
```

3. Usage
```java
/**
 * Hello world!
 *
 */
public class App {

    @Tracer
    public void say() {
        System.out.println("App say");
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.say();
    }
}
```
