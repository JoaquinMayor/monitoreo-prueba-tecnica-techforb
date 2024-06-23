package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.models;

//Clase creada para manejar más facilmente el manejo de la creación de lecturas.
public class ActualizacionPlanta {

    private Long idPlanta;
    private Integer canLecturas;
    private Integer cantLectOk;
    private Integer cantLectMedio;
    private Integer cantLectRojo;
    
    public Long getIdPlanta() {
        return idPlanta;
    }
    public void setIdPlanta(Long idPlanta) {
        this.idPlanta = idPlanta;
    }
    public Integer getCanLecturas() {
        return canLecturas;
    }
    public void setCanLecturas(Integer canLecturas) {
        this.canLecturas = canLecturas;
    }
    public Integer getCantLectOk() {
        return cantLectOk;
    }
    public void setCantLectOk(Integer cantLectOk) {
        this.cantLectOk = cantLectOk;
    }
    public Integer getCantLectMedio() {
        return cantLectMedio;
    }
    public void setCantLectMedio(Integer cantLectMedio) {
        this.cantLectMedio = cantLectMedio;
    }
    public Integer getCantLectRojo() {
        return cantLectRojo;
    }
    public void setCantLectRojo(Integer cantLectRojo) {
        this.cantLectRojo = cantLectRojo;
    }

    
}
