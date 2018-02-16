package p1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
	Application is a to do list service that enables users to add, edit, and get information about To Do's.
*/
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}