package com.example.demo.service.impl;

import com.example.demo.dto.DtoUsers;
import com.example.demo.dto.DtoUsersIU;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUsersService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements IUsersService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public DtoUsers createUser(DtoUsersIU dtoUsersIU) {
        Users user = modelMapper.map(dtoUsersIU, Users.class);
        user.setCreatedAt(LocalDateTime.now());
        Users savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, DtoUsers.class);
    }

    @Override
    public DtoUsers updateUser(Long id, DtoUsersIU dtoUsersIU) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFirstName(dtoUsersIU.getFirstName());
        user.setLastName(dtoUsersIU.getLastName());
        user.setEmail(dtoUsersIU.getEmail());
        user.setPhoneNumber(dtoUsersIU.getPhoneNumber());
        user.setRole(dtoUsersIU.getRole());

        Users updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, DtoUsers.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<DtoUsers> getAllUserNames() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    DtoUsers dto = new DtoUsers();
                    dto.setId(user.getId());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
