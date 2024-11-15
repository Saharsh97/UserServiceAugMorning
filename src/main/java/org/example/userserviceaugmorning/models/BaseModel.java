package org.example.userserviceaugmorning.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    private boolean deleted = false;
    // when user logout, or malicious token is received
    // if this current token t1 is deleted
    // the next time user makes any request, they will t1
    // now I will see T1 is deleted => ask to login again and make a fresh token
}
