package home.todo_list;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }



}
