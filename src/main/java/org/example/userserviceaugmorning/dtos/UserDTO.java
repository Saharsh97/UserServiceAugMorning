package org.example.userserviceaugmorning.dtos;

import org.example.userserviceaugmorning.models.User;

public class UserDTO {
    public Long userId;
    public String name;

    public UserDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
    }
}
