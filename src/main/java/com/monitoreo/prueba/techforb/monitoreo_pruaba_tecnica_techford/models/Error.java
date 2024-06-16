package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.models;

import java.util.Date;

public class Error {

    private String mensaje;
    private String error;
    private int status;
    private Date date;
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
