/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import proyecto.ColaPrioridad;
import proyecto.NodoTicket;
import proyecto.Ticket;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author mafer
 */
public class TicketRepository {

    private final String nombreArchivo = "tiquetes.json";

    //Metodo para guardar la cola en un archivo
    public void save(ColaPrioridad cola) {
        
        //Convertir la cola a un String JSON
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n  \"tickets\": [\n");

        NodoTicket actual = cola.frente;
        boolean primerElemento = true;

        while (actual != null) {
            if (!primerElemento) {
                jsonBuilder.append(",\n");
            }
            primerElemento = false;

            Ticket t = actual.ticket;
            jsonBuilder.append("    {\n");
            jsonBuilder.append("      \"nombre\": \"").append(t.nombre).append("\",\n");
            jsonBuilder.append("      \"id\": \"").append(t.id).append("\",\n");
            jsonBuilder.append("      \"edad\": ").append(t.edad).append(",\n");
            jsonBuilder.append("      \"moneda\": \"").append(t.moneda).append("\",\n");
            jsonBuilder.append("      \"horaCompra\": ").append(t.horaCompra).append(",\n");
            jsonBuilder.append("      \"horaAbordaje\": ").append(t.horaAbordaje).append(",\n");
            jsonBuilder.append("      \"servicio\": \"").append(t.servicio).append("\",\n");
            jsonBuilder.append("      \"tipoBus\": \"").append(t.tipoBus).append("\"\n");
            jsonBuilder.append("    }");

            actual = actual.siguiente;
        }

        jsonBuilder.append("\n  ]\n}");

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.print(jsonBuilder.toString());
            JOptionPane.showMessageDialog(null, "Tiquetes guardados en " + nombreArchivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        }
    }

    //Método para cargar la cola desde el archivo
    public ColaPrioridad load() {
        ColaPrioridad cola = new ColaPrioridad();
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo al guardar.");
            return cola;
        }

        String json = contenido.toString();
        if (json.trim().isEmpty() || !json.contains("tickets")) {
            return cola;
        }

        try {
            //Buscar el inicio del array de tickets
            int inicioArray = json.indexOf("[");
            int finArray = json.lastIndexOf("]");
            String ticketsJson = json.substring(inicioArray + 1, finArray).trim();

            if (ticketsJson.isEmpty()) {
                return cola;
            }

            String[] objetosTicket = ticketsJson.split("},");

            for (int i = 0; i < objetosTicket.length; i++) {
                String objTicket = objetosTicket[i];
                if (i < objetosTicket.length - 1) {
                    objTicket = objTicket + "}";
                }

                Ticket t = new Ticket();

                t.nombre = extraerValorString(objTicket, "nombre");
                t.id = extraerValorString(objTicket, "id");
                t.edad = extraerValorInt(objTicket, "edad");
                t.moneda = extraerValorString(objTicket, "moneda");
                t.horaCompra = extraerValorLong(objTicket, "horaCompra");
                t.horaAbordaje = extraerValorInt(objTicket, "horaAbordaje");
                t.servicio = extraerValorString(objTicket, "servicio");
                t.tipoBus = extraerValorString(objTicket, "tipoBus");

                cola.encolar(t);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo JSON: " + e.getMessage());
        }

        return cola;
    }

    private String extraerValorString(String jsonObj, String clave) {
        String patron = "\"" + clave + "\": \"";
        int inicio = jsonObj.indexOf(patron);
        if (inicio == -1) return "";
        inicio += patron.length();
        int fin = jsonObj.indexOf("\"", inicio);
        return jsonObj.substring(inicio, fin);
    }

    private int extraerValorInt(String jsonObj, String clave) {
        String patron = "\"" + clave + "\": ";
        int inicio = jsonObj.indexOf(patron);
        if (inicio == -1) return 0;
        inicio += patron.length();
        int fin = jsonObj.indexOf(",", inicio);
        if (fin == -1) fin = jsonObj.indexOf("}", inicio);
        return Integer.parseInt(jsonObj.substring(inicio, fin).trim());
    }

    private long extraerValorLong(String jsonObj, String clave) {
        String patron = "\"" + clave + "\": ";
        int inicio = jsonObj.indexOf(patron);
        if (inicio == -1) return 0L;
        inicio += patron.length();
        int fin = jsonObj.indexOf(",", inicio);
        if (fin == -1) fin = jsonObj.indexOf("}", inicio);
        return Long.parseLong(jsonObj.substring(inicio, fin).trim());
    }
}