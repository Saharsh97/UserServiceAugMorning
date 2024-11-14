package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.dtos.enums.ResponseStatus;

@Getter
@Setter
public class LoginResponseDTO {
    public String tokenValue;  // A.B.C
    public ResponseStatus responseStatus;
}
