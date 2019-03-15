package com.ddtech.aspectj;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //表示该注解标注在方法上
@Retention(RetentionPolicy.RUNTIME) //表示该注解保留到runtime阶段，将被JVM保留,所以它能在运行时被JVM或其他使用反射机制的代码所读取和使用.
@Documented
public @interface Tracer {
}
