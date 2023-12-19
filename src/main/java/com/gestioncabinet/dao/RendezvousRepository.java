package com.gestioncabinet.dao;

import com.gestioncabinet.entities.RendezVous;
import org.springframework.data.repository.CrudRepository;

public interface RendezvousRepository extends CrudRepository<RendezVous,Long>{
    RendezVous findRendezVousById(Long id);
}
