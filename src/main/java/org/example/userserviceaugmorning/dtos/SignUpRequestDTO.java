package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    public String userName;
    public String email;
    public String password;
}
