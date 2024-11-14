package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.dtos.enums.ResponseStatus;

@Getter
@Setter
public class LogoutResponseDTO {
    public boolean tokenHasBeenDeleted;
    public ResponseStatus responseStatus;
}
