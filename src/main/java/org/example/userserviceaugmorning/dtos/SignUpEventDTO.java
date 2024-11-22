package org.example.userserviceaugmorning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpEventDTO {
    private String to;
    private String from;
    private String subject;
    private String body;

    @Override
    public String toString() {
        return "SignUpEventDTO{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
