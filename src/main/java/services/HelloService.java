package services;

import model.HelloWorld;

public class HelloService {

    public String hello(){
        HelloWorld message = new HelloWorld("Hello World");
        return message.getMessage();
    }
}
