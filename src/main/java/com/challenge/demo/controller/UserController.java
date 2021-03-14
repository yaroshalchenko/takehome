package com.challenge.demo.controller;

import com.challenge.demo.domain.QuestionAnswer;
import com.challenge.demo.domain.User;
import com.challenge.demo.dto.QuestionAnswerDTO;
import com.challenge.demo.dto.UserDTO;
import com.challenge.demo.repository.QuestionAnswerRepository;
import com.challenge.demo.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        final User newUser = User.builder()
                .userUUID(UUID.randomUUID())
                .username(userDTO.getUsername())
                .build();

        return UserDTO.build(userRepository.save(newUser));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map(UserDTO::build).collect(Collectors.toList());
    }

    @PostMapping("/{id}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createAnswer(@PathVariable(value = "id") Long id, @RequestBody QuestionAnswerDTO questionAnswerDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User with id " + id  + " is not exist"));
        QuestionAnswer questionAnswer = questionAnswerRepository.findById(
                questionAnswerDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Question answer with id " + questionAnswerDTO.getId()  + " is not exist"));
        user.getQuestionAnswers().add(questionAnswer);

        return UserDTO.build(userRepository.save(user));
    }

    @GetMapping("/{id}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public List<QuestionAnswerDTO> getAllAnswersByUser(@PathVariable Long id){
            User user =  userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user with id " + id));

            return user.getQuestionAnswers().stream().map(QuestionAnswerDTO::build)
                    .collect(Collectors.toList());
    }
}
