package com.library;
/**
 * Created by user on 05.07.2017.
 */

import com.library.core.mvc.service.tag.TagService;
import com.library.dto.tag.TagDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrap(final TagService service) {
        return (args) -> {
            service.insert(
                    new TagDTO("Peter")
            );
            service.insert(
                    new TagDTO("Car")
            );
            service.insert(
                    new TagDTO("Computer")
            );
            service.insert(
                    new TagDTO("Game")
            );
            service.getAll().stream().map(TagDTO::toString).forEach(System.out::println);
        };
    }
}
