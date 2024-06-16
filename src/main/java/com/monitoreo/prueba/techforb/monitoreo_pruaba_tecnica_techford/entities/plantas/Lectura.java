package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "lecturas")
public class Lectura{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_lectura")
    private TipoLectura tipo;
    
    
    @ManyToOne
    @JoinColumn(name = "id_alerta")
    private Alerta alerta;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    @JsonIgnore
    private Planta planta;

    public Lectura(){

    }

    public Lectura(Long id, @NotNull TipoLectura tipo, @NotNull Alerta alerta) {
        this.id = id;
        this.tipo = tipo;
        this.alerta = alerta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLectura getTipo() {
        return tipo;
    }

    public void setTipo(TipoLectura tipo) {
        this.tipo = tipo;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
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
        Lectura other = (Lectura) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lectura [id=" + id + ", tipo=" + tipo + ", alerta=" + alerta  + "]";
    }

    



    
}
