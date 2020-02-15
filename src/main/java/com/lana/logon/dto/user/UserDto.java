package com.lana.logon.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;

    private String email;

    private String avatar;

    private String firstName;

    private String lastName;

    private Character gender;
}