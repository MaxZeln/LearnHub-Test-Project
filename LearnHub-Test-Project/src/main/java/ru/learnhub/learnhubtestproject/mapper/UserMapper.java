package ru.learnhub.learnhubtestproject.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnhub.learnhubtestproject.entity.User;
import ru.learnhub.learnhubtestproject.model.UserDto;
import ru.learnhub.learnhubtestproject.view.UserView;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;

    }

    public UserDto mapToDto(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .confirmPassword(entity.getPassword())
                .role(roleMapper.mapToDto(entity.getRole()))
                .build();
    }

    public User mapToEntity(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(roleMapper.mapToEntity(dto.getRole()));
        return user;
    }

    public UserView mapToView(UserDto dto){
        UserView view = new UserView();
        view.setId(dto.getId());
        view.setLogin(dto.getLogin());
        view.setEmail(dto.getEmail());
        view.setPhone(dto.getPhone());
        view.setPassword(dto.getPassword());
        view.setConfirmPassword(dto.getConfirmPassword());
        view.setRole(roleMapper.mapToView(dto.getRole()));
        return view;
    }

    public UserDto mapFromView(UserView view){
        UserDto userDto = new UserDto();
        userDto.setId(view.getId());
        userDto.setLogin(view.getLogin());
        userDto.setEmail(view.getEmail());
        userDto.setPhone(view.getPhone());
        userDto.setPassword(view.getPassword());
        userDto.setConfirmPassword(view.getConfirmPassword());
        userDto.setRole(roleMapper.mapFromView(view.getRole()));

        return userDto;
    }

}

