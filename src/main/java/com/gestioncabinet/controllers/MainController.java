package com.gestioncabinet.controllers;
import com.gestioncabinet.entities.RendezVous;
import com.gestioncabinet.entities.Role;
import com.gestioncabinet.entities.Soin;
import com.gestioncabinet.entities.User;
import com.gestioncabinet.metier.ImageService;
import com.gestioncabinet.metier.ServiceMetier;
import com.gestioncabinet.utilities.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Date;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {
    private ServiceMetier serviceMetier;
    private ImageService imageService;
    private static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/image";
    public User getAuthenticatedUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return null;
        }
        String username=authentication.getName();
        return serviceMetier.loadByEmail(username);
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("authenticatedUser",getAuthenticatedUser());
        model.addAttribute("listSoin",serviceMetier.listSoins());
        return "index";}

    @GetMapping("/")
    public String accueil(){return "redirect:/home";}

    @GetMapping("/admin_add_consultation")
    public String addConsultation(){return "admin_add_consultation";}

    @GetMapping("/apointment")
    public String appointment(Model model){
        User user=getAuthenticatedUser();
        model.addAttribute("mesRDV",user.getMesRDVs());
        return "apointment";}

    @GetMapping("/signup")
    public String signup(){return "signup";}

    @GetMapping("/makeappointment")
    public String makeapointment(@RequestParam("id") String id,Model model){
        model.addAttribute("soin",serviceMetier.findSoinById(id));
        return "makeappointment";}

    @GetMapping("/admin_panel")
    public String adminHome(Model model){
        model.addAttribute("authenticatedUser",getAuthenticatedUser());
        model.addAttribute("listSoin",serviceMetier.listSoins());
        return "admin_Home";}

    @GetMapping("/login")
    public String signin(){return "signin";}
    @GetMapping("/admin_appointment")
    public String adminAppointment(Model model){
        model.addAttribute("listRDVs",serviceMetier.listRDVs());
        return "admin_appointment";}
    @GetMapping("/ad_annuler_rdv")
    public String ad_annuler_rdv(@RequestParam("rdvId")String id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        String username=userDetails.getUsername();
        User user=serviceMetier.loadByEmail(username);
        serviceMetier.annullerRdv(id,user);
        return "redirect:/admin_appointment";
    }
    @PostMapping("/inscription")
    public String inscription(@RequestParam("email") String email,@RequestParam("fullname")String fullname,@RequestParam("phone") String phone,@RequestParam("password") String password , Model model){
        User user=new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setNomprenom(fullname);

        try {

            serviceMetier.addUser(user);

        }catch (Exception e){
            model.addAttribute("erreur",e.getMessage());
            return "signup";
        }
        model.addAttribute("succes","Vous vous etes bien inscrit");
        return "signup";
    }

    @PostMapping("/addConsultation")
    public String priseRDV(@RequestParam("dateRDV") Date date, @RequestParam("heureRDV") Integer heureRDV, @RequestParam("soin_id")String id, Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        String username=userDetails.getUsername();
        RendezVous rendezVous=new RendezVous();
        rendezVous.setHeureRDV(heureRDV);
        rendezVous.setDateRDV(date);
        Soin soin=serviceMetier.findSoinById(id);
        rendezVous.setConsultation(soin);
        User user=serviceMetier.loadByEmail(username);
        rendezVous=serviceMetier.addRDV(rendezVous);
        user.getMesRDVs().add(rendezVous);
        serviceMetier.updateUser(user);

        model.addAttribute("soin",soin);
        model.addAttribute("result","Rendez-vous bien enregistre");
        return "makeappointment";
    }
    @GetMapping("/annulerRdv")
    public String annulerRdv(@RequestParam("rdvId")String id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        String username=userDetails.getUsername();
        User user=serviceMetier.loadByEmail(username);
        serviceMetier.annullerRdv(id,user);
        return "redirect:/apointment";
    }
    @PostMapping("/addSoin")
    public String addConsult(@RequestParam("image")MultipartFile multipartFile,@RequestParam("nom")String nom,@RequestParam("prix")Double prix){
        Soin soin=new Soin();
        soin.setNom(nom);
        soin.setPrix(prix);
        try {
            String imageId=imageService.saveImage(multipartFile.getOriginalFilename(),multipartFile);
            soin.setImage("/images/"+imageId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serviceMetier.addSoin(soin);
        return "admin_add_consultation";
    }
    @GetMapping("/supprimerSoin")
    public String supprimerSoin(@RequestParam("soinId")String id){
        serviceMetier.supprimerSoin(id);
        return "redirect:admin_panel";
    }
    @PostMapping("/modifierSoin")
    public String modifierSoin(@RequestParam("soinId")String id,@RequestParam("nom")String nom,@RequestParam("prix") Double prix,@RequestParam("image")MultipartFile multipartFile,Model model){
        Soin soin=serviceMetier.findSoinById(id);
        soin.setPrix(prix);
        soin.setNom(nom);
        if (!multipartFile.isEmpty()){
            try {
                String imageId=imageService.saveImage(multipartFile.getOriginalFilename(),multipartFile);
                soin.setImage("/images/"+imageId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        serviceMetier.addSoin(soin);
        model.addAttribute("soin",soin);
        return "modifierSoin";
    }
    @GetMapping("/modifierSoin")
    public String modifySoin(@RequestParam("soinId")String soinId,Model model){
        Soin soin=serviceMetier.findSoinById(soinId);
        model.addAttribute("soin",soin);
        return "modifierSoin";
    }
    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") String imageId) throws IOException {
        GridFsResource resource=imageService.getImage(imageId);
        if (resource != null) {
            try {
                /*byte[] data = new byte[(int) resource.contentLength()];
                resource.getInputStream().readAllBytes();*/
                return ResponseEntity.ok(resource.getInputStream().readAllBytes());
            } catch (IOException e) {
                throw  e;
                //return ResponseEntity.badRequest().body("Error reading image data");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
