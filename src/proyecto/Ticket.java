/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author mafer
 */
public class Ticket {
    public String nombre;
    public String id;
    public int edad;
    public String moneda; 
    public long horaCompra;
    public int horaAbordaje;
    public String servicio; 
    public String tipoBus;

    public Ticket() {
        this.horaAbordaje = -1;
        this.servicio = "NA";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public long getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(long horaCompra) {
        this.horaCompra = horaCompra;
    }

    public int getHoraAbordaje() {
        return horaAbordaje;
    }

    public void setHoraAbordaje(int horaAbordaje) {
        this.horaAbordaje = horaAbordaje;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTipoBus() {
        return tipoBus;
    }

    public void setTipoBus(String tipoBus) {
        this.tipoBus = tipoBus;
    }
}