package com.gestioncabinet.metier;

import com.gestioncabinet.dao.*;
import com.gestioncabinet.entities.RendezVous;
import com.gestioncabinet.entities.Role;
import com.gestioncabinet.entities.Soin;
import com.gestioncabinet.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@Service @AllArgsConstructor
public class ServiceMetierImpl implements ServiceMetier{
    private UserRepository userRepository;
    private SoinRepository soinRepository;
    private RendezvousRepository rendezvousRepository;
    private  RoleRepository roleRepository;

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) throws Exception{
        String email=user.getEmail();
        User user1=userRepository.findByEmail(email);
        if (user1!=null)
            throw new Exception("L'utilisateur exite existe deja");
        PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.getRoles().add(findRoleByName("USER"));
        userRepository.save(user);
    }

    @Override
    public User loadByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public RendezVous addRDV(RendezVous rendezVous) {
        return rendezvousRepository.save(rendezVous);
    }

    @Override
    public Soin findSoinById(String id) {
        return soinRepository.findSoinById(id);
    }

    @Override
    public List<Soin> listSoins() {
        return soinRepository.findAll();
    }

    @Override
    public Soin findSoinByName(String nom) {
        return soinRepository.findByNom(nom);
    }

    @Override
    public Role findRoleByName(String nom) {
        return roleRepository.findByRoleName(nom);
    }

    @Override
    public List<RendezVous> listRDVs() {
        return (List<RendezVous>) rendezvousRepository.findAll();
    }

    @Override
    public void annullerRdv(String id,User user) {
        RendezVous rendezVous=rendezvousRepository.findRendezVousById(id);
        user.getMesRDVs().remove(rendezVous);
        updateUser(user);
        rendezvousRepository.deleteById(id);
    }

    @Override
    public void addSoin(Soin soin) {
        soinRepository.save(soin);
    }

    @Override
    public void supprimerSoin(String soin) {
        soinRepository.deleteById(soin);
    }

    @Override
    public void ModifierSoin(Soin soin) {
    }
}
