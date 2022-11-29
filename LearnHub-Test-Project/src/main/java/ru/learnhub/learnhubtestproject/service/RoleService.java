package ru.learnhub.learnhubtestproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnhub.learnhubtestproject.mapper.RoleMapper;
import ru.learnhub.learnhubtestproject.model.RoleDto;
import ru.learnhub.learnhubtestproject.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository repository,
                       RoleMapper roleMapper) {
        this.roleRepository = repository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDto> getRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public RoleDto findById(int id) {
        return roleMapper.mapToDto(roleRepository.getReferenceById(id));
    }

    public RoleDto findByRoleName(String name) {
        return roleMapper.mapToDto(roleRepository.findRoleByName(name));
    }

}
