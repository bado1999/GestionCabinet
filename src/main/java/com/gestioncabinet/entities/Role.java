package com.gestioncabinet.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data

@Document("role")
public class Role {
    @Id
    private String id;
    private String roleName;
}
