/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import proyecto.Ticket;
import javax.swing.JOptionPane;

/**
 *
 * @author mafer
 */
public class ColaPrioridad {
    NodoTicket frente; 
    private NodoTicket fin;

    public ColaPrioridad() {
        this.frente = null;
        this.fin = null;
    }

    // Metodo para encolar con prioridad
    public void encolar(Ticket ticket) {
        NodoTicket nuevoNodo = new NodoTicket(ticket);

        //Prioridad según el tipo de bus
        if (ticket.tipoBus.equals("P")) {
            nuevoNodo.prioridad = 1;
        } else if (ticket.tipoBus.equals("D")) {
            nuevoNodo.prioridad = 2;
        } else {
            nuevoNodo.prioridad = 3;
        }

        //Si la cola está vacía, el nuevo nodo es el frente y el fin
        if (frente == null) {
            frente = nuevoNodo;
            fin = nuevoNodo;
            return;
        }

        if (nuevoNodo.prioridad < frente.prioridad) {
            nuevoNodo.siguiente = frente;
            frente = nuevoNodo;
            return;
        }

        //Posición correcta en el medio o al final
        NodoTicket actual = frente;
        NodoTicket anterior = null;

        while (actual != null && actual.prioridad <= nuevoNodo.prioridad) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual != null) {
            anterior.siguiente = nuevoNodo;
            nuevoNodo.siguiente = actual;
        } else {
            // Si llegamos al final, lo insertamos al final
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
    }

    //Metodo para desencolar 
    public Ticket desencolar() {
        if (frente == null) {
            JOptionPane.showMessageDialog(null, "La cola está vacía.");
            return null;
        }
        Ticket ticketDesencolado = frente.ticket;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }
        return ticketDesencolado;
    }

    // Metodo para ver el frente sin desencolar
    public Ticket peek() {
        if (frente == null) {
            JOptionPane.showMessageDialog(null, "La cola está vacía.");
            return null;
        }
        return frente.ticket;
    }

    public boolean isEmpty() {
        return frente == null;
    }
    
    public void mostrar() {
        if (frente == null) {
            System.out.println("La cola está vacía.");
            return;
        }

        NodoTicket actual = frente;
        System.out.println("Contenido de la cola por prioridad: ");
        while (actual != null) {
            System.out.println("  Nombre: " + actual.ticket.nombre + ", Tipo: " + actual.ticket.tipoBus + ", Prioridad: " + actual.prioridad);
            actual = actual.siguiente;
        }
    }
}