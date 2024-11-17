package org.example.userserviceaugmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.userserviceaugmorning.models.enums.UserStatus;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashedPassword;

    @Enumerated
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private UserStatus userStatus;
}
