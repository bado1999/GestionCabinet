package com.gestioncabinet.metier;
import com.gestioncabinet.entities.RendezVous;
import com.gestioncabinet.entities.Role;
import com.gestioncabinet.entities.Soin;
import com.gestioncabinet.entities.User;

import java.util.List;
import java.util.Optional;

public interface ServiceMetier {
    public  void updateUser(User user);
    public void addUser(User user) throws Exception;
    public User loadByEmail(String email);
    public RendezVous addRDV(RendezVous rendezVous);
    public Soin findSoinById(String id);
    public List<Soin> listSoins();
    public Soin findSoinByName(String nom);
    public Role findRoleByName(String nom);
    public List<RendezVous> listRDVs();
    public void annullerRdv(String id,User user);
    public void addSoin(Soin soin);
    public void supprimerSoin(String id);
    public void ModifierSoin(Soin soin);
}
