/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package proyecto;

import proyecto.ColaPrioridad;
import proyecto.Ticket;
import proyecto.TicketRepository;
import javax.swing.JOptionPane;

/**
 *
 * @author mafer
 */
public class Proyecto {

    public static void main(String[] args) {

        TicketRepository repo = new TicketRepository();
        ColaPrioridad colaTickets = repo.load();
        System.out.println("Tickets cargados desde el archivo.");

        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog("""
                    Menu:
                    1. Crear un nuevo ticket
                    2. Mostrar cola actual
                    3. Salir
                    Ingrese su opción:""");

            if (opcion == null) { 
                salir = true;
                continue;
            }

            if (opcion.equals("1")) {

                Ticket nuevoTicket = new Ticket();

                nuevoTicket.setNombre(JOptionPane.showInputDialog("Ingrese el nombre:"));
                nuevoTicket.setId(JOptionPane.showInputDialog("Ingrese el ID:"));
                nuevoTicket.setEdad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad:")));
                nuevoTicket.setMoneda(JOptionPane.showInputDialog("Ingrese la moneda (colones o dólares):"));

                nuevoTicket.setHoraCompra(System.currentTimeMillis());

                String tipo = "";
                while (!(tipo.equals("P") || tipo.equals("D") || tipo.equals("N"))) {
                    tipo = JOptionPane.showInputDialog("Ingrese el tipo de bus (P, D o N):").toUpperCase();
                }
                nuevoTicket.setTipoBus(tipo);

                colaTickets.encolar(nuevoTicket);
                JOptionPane.showMessageDialog(null, "Ticket creado y encolado.");

            } else if (opcion.equals("2")) {
                colaTickets.mostrar();
            } else if (opcion.equals("3")) {
                salir = true;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no valida.");
            }
        }

        repo.save(colaTickets);
        System.out.println("Programa finalizado.");
    }
}