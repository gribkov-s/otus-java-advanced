package ru.otus.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.User;
import ru.otus.model.UserData;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserClientResilienceAdapter userClientResilienceAdapter;

    @GetMapping("/")
    @Operation(summary = "Gets all users", tags = "user")
    public Flux<String> getAll() {
        return userClientResilienceAdapter.getAll();
    }

    @GetMapping("/{login}")
    @Operation(summary = "Gets user by login", tags = "user")
    public Mono<String> getUser(@PathVariable String login) {
        return userClientResilienceAdapter.findByLogin(login);
    }

    @GetMapping("/{login}/age")
    @Operation(summary = "Gets user age", tags = "user")
    public Mono<Long> getUserAge(@PathVariable String login) {
        return userClientResilienceAdapter.getUserAge(login);
    }

    @GetMapping("/report")
    @Operation(summary = "Gets user monitoring report", tags = "user")
    public Flux<UserData> getUserReport() {
        return userClientResilienceAdapter.getUserReport();
    }

    @PostMapping
    @Operation(summary = "Saves new user", tags = "user")
    public Mono<String> saveUser(@RequestBody User user) {
        return userClientResilienceAdapter.save(user);
    }

    @PutMapping
    @Operation(summary = "Updates user", tags = "user")
    public Mono<String> updateUser(@RequestBody User user) {
        return userClientResilienceAdapter.update(user);
    }

    @DeleteMapping("/{login}")
    @Operation(summary = "Deletes user", tags = "user")
    public Mono<String> deleteUser(@PathVariable String login) {
        return userClientResilienceAdapter.delete(login);
    }

    @PostMapping("/save-test-users")
    @Operation(summary = "Saves new test users", tags = "user")
    public Flux<String> saveTestUsers(@RequestParam String loginPrefix, @RequestParam Integer usersNumber) {
        int usersNumberChecked = usersNumber <= 10000 && usersNumber > 0 ? usersNumber : 1;
        CopyOnWriteArrayList<Mono<String>> responses = new CopyOnWriteArrayList<>();
        IntStream.rangeClosed(1, usersNumberChecked).parallel()
                .forEach(ic -> {
                    String login = loginPrefix + ic;
                    User user = new User(login, login);
                    Mono<String> response = userClientResilienceAdapter.save(user);
                    responses.add(response);
                });
        return Flux.concat(responses);
    }
}
