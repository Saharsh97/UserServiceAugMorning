package org.example.userserviceaugmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value; // A.B.C

    @ManyToOne
    private User user;

    private Date expiryDate;
}


// T : U
// 1 : 1
// M : 1
// Many to One