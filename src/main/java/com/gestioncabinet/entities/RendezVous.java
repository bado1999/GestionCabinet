package com.gestioncabinet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document("rdv")
@Data @AllArgsConstructor @NoArgsConstructor
public class RendezVous {
    @Id
    private String id;
    private Date dateRDV;
    private Integer heureRDV;
    private Soin consultation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RendezVous that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
