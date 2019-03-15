package com.ddtech.aspectj;

/**
 * Hello world!
 *
 */
public class App {

    @Tracer
    public void say() {
        System.out.println("App say");
    }

    @Tracer
    public void sss(){

    }
    public static void main(String[] args) {
        App app = new App();
        app.say();
    }
}
