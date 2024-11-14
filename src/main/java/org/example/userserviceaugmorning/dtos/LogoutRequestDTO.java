package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {
    public Long userId;
    public String tokenValue;
}
