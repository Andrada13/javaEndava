package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("api")
@ImportResource({"classpath*:config.xml","classpath*:registrations.xml"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);

    }
}
