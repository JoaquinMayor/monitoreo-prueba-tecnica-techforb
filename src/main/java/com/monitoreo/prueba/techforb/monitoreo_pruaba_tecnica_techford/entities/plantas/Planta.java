package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "plantas")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String pais;
    @NotEmpty
    private String bandera; 

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "planta")
    private Set<Lectura> lecturas;

    public Planta(){

    }

    public Planta(Long id, @NotEmpty String nombre, String pais, String bandera, Set<Lectura> lecturas) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.bandera = bandera;
        this.lecturas = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public Set<Lectura> getLecturas() {
        return lecturas;
    }

    public void setLecturas(Set<Lectura> lecturas) {
        this.lecturas = lecturas;
    }

    public void setLectura(Lectura lectura){
        this.lecturas.add(lectura);
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
        Planta other = (Planta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Planta [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", lecturas=" + lecturas + "]";
    }


    
}
