/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import proyecto.Ticket;

/**
 *
 * @author mafer
 */

public class NodoTicket {
    Ticket ticket;           
    NodoTicket siguiente;     
    int prioridad;           

    public NodoTicket(Ticket ticket) {
        this.ticket = ticket;
        this.siguiente = null;
        this.prioridad = 0; 
    }
}