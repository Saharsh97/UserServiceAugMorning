package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.dtos.enums.ResponseStatus;

@Getter
@Setter
public class SignUpResponseDTO {
    public ResponseStatus ResponseStatus;
    public String message;
}
