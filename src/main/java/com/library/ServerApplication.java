package com.library;
/**
 * Created by user on 05.07.2017.
 */

import com.library.core.mvc.service.instruction.InstructionService;
import com.library.core.mvc.service.tag.TagService;
import com.library.core.mvc.service.user.UserService;
import com.library.dao.model.entities.instruction.PartType;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.instruction.PartDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.tag.TagDTO;
import com.library.dto.user.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrap(final TagService tagService, final InstructionService instructionService,
                                final UserService userService) {
        return (args) -> {
//            tagService.insert(
//                    new TagDTO("Peter")
//            );
//            tagService.insert(
//                    new TagDTO("Car")
//            );
//            tagService.insert(
//                    new TagDTO("Computer")
//            );
//            tagService.insert(
//                    new TagDTO("Game")
//            );
//            tagService.findAll().stream().map(TagDTO::toString).forEach(System.out::println);


//            instructionService.insert(createInstruction(tagService.findAll(), userService.findById(1L)));
        };
    }

    private InstructionDTO createInstruction(List<TagDTO> tags, UserDTO user) {
        List<PartDTO> parts = new ArrayList<>();

        PartDTO part1 = new PartDTO();
        part1.setType(PartType.TYPE_TEXT);
        part1.setData("qwert");
        parts.add(part1);

        PartDTO part2 = new PartDTO();
        part2.setType(PartType.TYPE_TEXT);
        part2.setData("rgwrfds");
        parts.add(part2);

        List<StepDTO> steps = new ArrayList<>();

        StepDTO step1 = new StepDTO();
        step1.setParts(parts);
        steps.add(step1);

        StepDTO step2 = new StepDTO();
        step2.setParts(parts);
        steps.add(step2);

        tags.addAll(tags);

        InstructionDTO instruction = new InstructionDTO();
        instruction.setSteps(steps);
        instruction.setTags(tags);
        instruction.setName("First instruction");
        instruction.setCreationDate(new Date());
        instruction.setLastModifiedDate(new Date());
        instruction.setUser(user);
        return instruction;
    }
}
