package ru.learnhub.learnhubtestproject.mapper;

import org.springframework.stereotype.Component;
import ru.learnhub.learnhubtestproject.entity.Role;
import ru.learnhub.learnhubtestproject.model.RoleDto;
import ru.learnhub.learnhubtestproject.view.RoleView;

@Component
public class RoleMapper {

    public RoleDto mapToDto(Role entity) {
        return RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Role mapToEntity(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    public RoleView mapToView(RoleDto dto) {
        RoleView view = new RoleView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        return view;
    }

    public RoleDto mapFromView(RoleView view) {
        return RoleDto.builder()
                .id(view.getId())
                .name(view.getName())
                .build();
    }

}
