package ru.learnhub.learnhubtestproject.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleView {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;

    @Override
    public String toString() {
        return "RoleView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
