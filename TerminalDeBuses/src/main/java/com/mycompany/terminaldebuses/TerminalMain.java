//Authors = Nyko
package com.mycompany.terminaldebuses;
import javax.swing.JOptionPane;

public class TerminalMain {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "|| Sistema Universal de Buses ||");
        try {
            iniciarSistema();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }
    private static void iniciarSistema() {
        verificarConfiguracion();
        cargarConfiguracion();
        cargarTickets();
        mostrarMenuPrincipal();
    }

    private static void verificarConfiguracion() {
        //verificacion del Json
        JOptionPane.showMessageDialog(null, "Verificando si existe config.json...");
    }
    private static void cargarConfiguracion() {
        // verificacion de config
        JOptionPane.showMessageDialog(null, "Cargando configuracion...");
    }
    private static void cargarTickets() {
        //verificacion de tickets
        JOptionPane.showMessageDialog(null, "Cargando tickets...");
    }
    
    private static void mostrarMenuPrincipal() {
        int opcion = 0;
        while (opcion != 3) {
            String menu = "|| Menu!! ||\n"
                        + "1. Crear Ticket\n"
                        + "2. Ver estado de las colas\n"
                        + "3. Salir\n\n"
                        + "Seleccione una opcion:";

            String input = JOptionPane.showInputDialog(null, menu);
            //cancelar
            if (input == null) {
                opcion = 3;
                continue;
            }
            try {
                opcion = Integer.parseInt(input);
                switch (opcion) {
                    case 1:
                        crearTicket();
                        break;
                    case 2:
                        mostrarColas();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Saliendo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido porfavor.");
            }
        }
    }

    private static void crearTicket() {

        JOptionPane.showMessageDialog(null, "Creacion de Ticket");

        String nombre = JOptionPane.showInputDialog(null, "Nombre:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "porfavor digite un nombre.");
            return;
        }

        String id = JOptionPane.showInputDialog(null, "ID:");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "porfavor digite un nombre.");
            return;
        }

        String edadTxt = JOptionPane.showInputDialog(null, "Edad:");
        if (edadTxt == null) return;

        int edad;
        try {
            edad = Integer.parseInt(edadTxt.trim());
            if (edad < 0) {
                JOptionPane.showMessageDialog(null, "Error! intentelo de nuevo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad invalida.");
            return;
        }

        String tipoBus = JOptionPane.showInputDialog(null, "Seleccione un Tipo de Bus (P / D / N):");
        if (tipoBus == null) return;

        tipoBus = tipoBus.trim().toUpperCase();

        if (!tipoBus.equals("P") && !tipoBus.equals("D") && !tipoBus.equals("N")) {
            JOptionPane.showMessageDialog(null, "Tipo de bus incorrecto.");
            return;
        }

        //simulacion
        String resumen = "Ticket creado correctamente (simulado).\n\n"
                       + "Nombre: " + nombre + "\n"
                       + "ID: " + id + "\n"
                       + "Edad: " + edad + "\n"
                       + "TipoBus: " + tipoBus + "\n\n"
                       + "HoraCompra:\n"
                       + "HoraAbordaje: \n"
                       + "Servicio: NA";

        JOptionPane.showMessageDialog(null, resumen);
    }

    private static void mostrarColas() {
        //simulado
        JOptionPane.showMessageDialog(null,
                "simulado");

    }

}