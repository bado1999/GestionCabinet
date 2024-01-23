package com.gestioncabinet.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor @NoArgsConstructor @Data
@Document("user")
public class User implements Serializable {
    @Id
    private String id;
    private String Nomprenom;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;
    private String email;
    private List<Role> roles=new ArrayList<>();
    private List<RendezVous> mesRDVs=new ArrayList<>();

}
