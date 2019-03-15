package com.ddtech.aspectj;


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