package com.gestioncabinet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import javax.annotation.processing.Generated;
import java.sql.Date;

@Entity
@Data
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
}
