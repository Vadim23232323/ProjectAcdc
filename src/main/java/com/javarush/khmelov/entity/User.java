package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements AbstractEntity {

    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private Role role;

    public String getImage() { //TODO move to DTO
        return "image-" + id;
    }

}
