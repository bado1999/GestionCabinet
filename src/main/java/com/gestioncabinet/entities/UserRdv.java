package com.gestioncabinet.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_rdv")
public class UserRdv {
    @Id
    String id;
    String user_id;
    String rdv_id;
}
