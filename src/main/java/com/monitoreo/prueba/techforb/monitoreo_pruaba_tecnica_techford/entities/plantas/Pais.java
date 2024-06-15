package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.entities.plantas;

public class Pais {

   private String nombre;
   private String bandera;


    public Pais(){
        
    }

   public Pais(String nombre, String bandera) {
    this.nombre = nombre;
    this.bandera = bandera;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getBandera() {
    return bandera;
}

public void setBandera(String bandera) {
    this.bandera = bandera;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
    Pais other = (Pais) obj;
    if (nombre == null) {
        if (other.nombre != null)
            return false;
    } else if (!nombre.equals(other.nombre))
        return false;
    return true;
}
   
   
}
