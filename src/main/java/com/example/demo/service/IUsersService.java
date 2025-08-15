package com.example.demo.service;

import com.example.demo.dto.DtoUsers;
import com.example.demo.dto.DtoUsersIU;
import com.example.demo.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUsersService {

    List<Users> getAllUsers();

    Optional<Users> getUserById(Long id);

    DtoUsers createUser(DtoUsersIU dtoUsersIU);

    DtoUsers updateUser(Long id, DtoUsersIU dtoUsersIU);

    void deleteUser(Long id);

    // İsim soyisim listeleme için
    List<DtoUsers> getAllUserNames();
}
