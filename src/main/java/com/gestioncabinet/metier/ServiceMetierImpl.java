package com.gestioncabinet.metier;

import com.gestioncabinet.dao.*;
import com.gestioncabinet.entities.RendezVous;
import com.gestioncabinet.entities.Role;
import com.gestioncabinet.entities.Soin;
import com.gestioncabinet.entities.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service @AllArgsConstructor
public class ServiceMetierImpl implements ServiceMetier{
    private UserRepository userRepository;
    private SoinRepository soinRepository;
    private RendezvousRepository rendezvousRepository;
    private  RoleRepository roleRepository;
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
    public void addRDV(RendezVous rendezVous) {
        rendezvousRepository.save(rendezVous);
    }

    @Override
    public Soin findSoinById(Long id) {
        return soinRepository.findSoinById(id);
    }

    @Override
    public List<Soin> listSoins() {
        return (List<Soin>) soinRepository.findAll();
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
    public void annullerRdv(Long id) {
        RendezVous rendezVous=rendezvousRepository.findRendezVousById(id);
        User user=rendezVous.getUser();
        user.getMesRDVs().remove(rendezVous);
        rendezvousRepository.deleteById(id);
    }

    @Override
    public void addSoin(Soin soin) {
        soinRepository.save(soin);
    }

    @Override
    public void supprimerSoin(Long soin) {
        soinRepository.deleteById(soin);
    }

    @Override
    public void ModifierSoin(Soin soin) {
    }
}
