package com.gestioncabinet.dao;

import com.gestioncabinet.entities.RendezVous;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface RendezvousRepository extends MongoRepository<RendezVous,String> {
    RendezVous findRendezVousById(String id);
}
