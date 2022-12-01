package ru.learnhub.learnhubtestproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.learnhub.learnhubtestproject.annotation.PasswordValueMatch;
import ru.learnhub.learnhubtestproject.annotation.ValidPassword;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;

    private String login;

    private String email;

    private String phone;

    private String password;

    private String confirmPassword;

    private RoleDto role;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role=" + role +
                '}';
    }
}
