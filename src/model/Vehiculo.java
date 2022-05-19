/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author jtech
 */
public class Vehiculo {
    private String idVehiculo;
    private String modelo;
    private String categoria;
    private String descripcion;
    private Date fechaAlta;
    private int kilometrajeAlta;
    private boolean itv;
    private Object datoActualizado;
    private int columnaActualizada;

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getKilometrajeAlta() {
        return kilometrajeAlta;
    }

    public void setKilometrajeAlta(int kilometrajeAlta) {
        this.kilometrajeAlta = kilometrajeAlta;
    }

    public boolean isItv() {
        return itv;
    }

    public void setItv(boolean itv) {
        this.itv = itv;
    }

    public Object getDatoActualizado() {
        return datoActualizado;
    }

    public void setDatoActualizado(Object datoActualizado) {
        this.datoActualizado = datoActualizado;
    }

    public int getColumnaActualizada() {
        return columnaActualizada;
    }

    public void setColumnaActualizada(int columnaActualizada) {
        this.columnaActualizada = columnaActualizada;
    }
    
    
    
}

