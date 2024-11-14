package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.dtos.enums.ResponseStatus;
import org.example.userserviceaugmorning.dtos.enums.TokenVerificationStatus;

@Getter
@Setter
public class ValidateTokenResponseDTO {
    public TokenVerificationStatus tokenVerificationStatus;
    public ResponseStatus responseStatus;
}
