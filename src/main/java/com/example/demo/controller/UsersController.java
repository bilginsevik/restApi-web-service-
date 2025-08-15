package com.example.demo.controller;

import com.example.demo.dto.DtoUsers;
import com.example.demo.dto.DtoUsersIU;
import com.example.demo.model.Users;
import com.example.demo.service.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı."));
    }

    @PostMapping
    public DtoUsers createUser(@RequestBody DtoUsersIU dtoUsersIU) {
        return usersService.createUser(dtoUsersIU);
    }

    @PutMapping("/{id}")
    public DtoUsers updateUser(@PathVariable Long id, @RequestBody DtoUsersIU dtoUsersIU) {
        return usersService.updateUser(id, dtoUsersIU);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }

    @GetMapping("/names")
    public List<DtoUsers> getAllUserNames() {
        return usersService.getAllUserNames();
    }
}
