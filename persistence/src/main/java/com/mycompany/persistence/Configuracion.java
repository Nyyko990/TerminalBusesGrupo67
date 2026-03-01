/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistence;

/**
 * Plantilla principal de la config dodne de guardara y se cargara desde el .Json
 * 
 * @author lcast
 */
public class Configuracion {
    private String nombreTerminal;
    private int cantidadTotalBuses;
    private Bus[] buses;
    private Usuario[] usuarios;

    public Configuracion() {
    }

    public Configuracion(String nombreTerminal, int cantidadTotalBuses, Bus[] buses, Usuario[] usuarios) {
        this.nombreTerminal = nombreTerminal;
        this.cantidadTotalBuses = cantidadTotalBuses;
        this.buses = buses;
        this.usuarios = usuarios;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public int getCantidadTotalBuses() {
        return cantidadTotalBuses;
    }

    public Bus[] getBuses() {
        return buses;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    public void setCantidadTotalBuses(int cantidadTotalBuses) {
        this.cantidadTotalBuses = cantidadTotalBuses;
    }

    public void setBuses(Bus[] buses) {
        this.buses = buses;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }
}
