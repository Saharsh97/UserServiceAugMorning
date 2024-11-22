package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.models.User;

@Getter
@Setter
public class UserDTO {
    public Long userId;
    public String name;

    public UserDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
    }
}
