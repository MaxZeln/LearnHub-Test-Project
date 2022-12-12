package ru.learnhub.learnhubtestproject.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.learnhub.learnhubtestproject.annotation.ValidPassword;
import ru.learnhub.learnhubtestproject.annotation.PasswordValueMatch;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserView {

    @JsonProperty
    private int id;


    @JsonProperty
    @NotEmpty(message = "Enter your login")
    @Size(min = 2, max = 100, message = "login must be between 2 and 100 characters long")
    private String login;

    @JsonProperty
    @NotEmpty(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;

    @JsonProperty
    @NotEmpty(message = "Enter your phone")
    @Size(min = 10, max = 20, message = "phone number must be between 10 and 20 characters long")
    private String phone;

    @JsonProperty
    @NotEmpty(message = "Enter your password")
    @ValidPassword
    private String password;

    @JsonProperty
    @NotEmpty(message = "Confirm your password")
    @ValidPassword
    private String confirmPassword;

    @JsonProperty
    private RoleView role;

    @Override
    public String toString() {
        return "UserView{" +
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
