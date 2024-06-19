package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas;

import java.util.Set;

import com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.enums.TipoAlerta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoAlerta alerta;

  
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "alerta")
    private Set<Lectura> lectura;

    public Alerta(){
        
    }

    public Alerta(TipoAlerta alerta) {
        this.alerta = alerta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAlerta getAlerta() {
        return alerta;
    }

    public void setAlerta(TipoAlerta alerta) {
        this.alerta = alerta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alerta other = (Alerta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alerta [id=" + id + ", alerta=" + alerta + "]";
    }

    

}
