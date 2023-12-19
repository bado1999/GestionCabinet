package com.gestioncabinet;

import com.gestioncabinet.metier.ServiceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCabinetApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionCabinetApplication.class, args);
    }

}
