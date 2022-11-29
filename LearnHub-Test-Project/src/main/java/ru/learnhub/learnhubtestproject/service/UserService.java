package ru.learnhub.learnhubtestproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnhub.learnhubtestproject.entity.User;
import ru.learnhub.learnhubtestproject.mapper.UserMapper;
import ru.learnhub.learnhubtestproject.model.UserDto;
import ru.learnhub.learnhubtestproject.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository repository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(int id) {
        return userMapper.mapToDto(userRepository.getReferenceById(id));
    }

    public UserDto findByUserNickname(String nickname) {
        return userMapper.mapToDto(userRepository.findUserByLogin(nickname));
    }

    @Transactional
    public UserDto create(UserDto user) {
        User entity = userMapper.mapToEntity(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        return userMapper.mapToDto(entity);
    }

    public void delete(int id) {
        User entity = userRepository.getReferenceById(id);
        userRepository.delete(entity);
    }

}
