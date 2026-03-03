/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Repositorio de configuración (persistencia)
 * @author lcast
 */
public class ConfigRepository {
    private static final String NOMBRE_ARCHIVO = "config.json";

    // Verifica si el archivo existe
    public boolean exists() {
        File archivo = new File(NOMBRE_ARCHIVO);
        return archivo.exists() && archivo.isFile();
    }

    //Guarda Configuracion en .json (serialización manual)
    public void save(Configuracion configuracion) throws IOException {
        String json = construirJson(configuracion);

        FileWriter fw = new FileWriter(NOMBRE_ARCHIVO);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(json);
        bw.close();
        fw.close();
    }

    //Carga Configuracion desde .json (deserialización manual)
    public Configuracion load() throws IOException {
        FileReader fr = new FileReader(NOMBRE_ARCHIVO);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            sb.append(linea);
        }

        br.close();
        fr.close();

        return parsearJson(sb.toString());
    }

    // Serialización manual 

    private String construirJson(Configuracion c) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        sb.append("  \"nombreTerminal\":\"").append(JsonUtilidades.escapar(c.getNombreTerminal())).append("\",\n");
        sb.append("  \"cantidadTotalBuses\":").append(c.getCantidadTotalBuses()).append(",\n");

        // buses
        sb.append("  \"buses\":[\n");
        Bus[] buses = c.getBuses();
        for (int i = 0; i < buses.length; i++) {
            sb.append("    {\"id\":").append(buses[i].getId())
              .append(",\"tipo\":\"").append(buses[i].getTipo()).append("\"}");
            if (i < buses.length - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ],\n");

        // usuarios
        sb.append("  \"usuarios\":[\n");
        Usuario[] usuarios = c.getUsuarios();
        for (int i = 0; i < usuarios.length; i++) {
            sb.append("    {\"usuario\":\"").append(JsonUtilidades.escapar(usuarios[i].getUsuario()))
              .append("\",\"contrasena\":\"").append(JsonUtilidades.escapar(usuarios[i].getContrasena())).append("\"}");
            if (i < usuarios.length - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n");

        sb.append("}\n");
        return sb.toString();
    }

    // Deserialización manual 

    private Configuracion parsearJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("config.json está vacío.");
        }

        String nombreTerminal = JsonUtilidades.extraerString(json, "nombreTerminal");
        int cantidadTotalBuses = JsonUtilidades.extraerInt(json, "cantidadTotalBuses");

        // buses
        String busesCrudo = JsonUtilidades.extraerArregloCrudo(json, "buses");
        String[] busesObj = JsonUtilidades.partirObjetos(busesCrudo);
        Bus[] buses = new Bus[busesObj.length];

        for (int i = 0; i < busesObj.length; i++) {
            int id = JsonUtilidades.extraerInt(busesObj[i], "id");
            String tipoStr = JsonUtilidades.extraerString(busesObj[i], "tipo");
            char tipo = (tipoStr != null && tipoStr.length() > 0) ? tipoStr.charAt(0) : 'N';
            buses[i] = new Bus(id, tipo);
        }

        // usuarios
        String usuariosCrudo = JsonUtilidades.extraerArregloCrudo(json, "usuarios");
        String[] usuariosObj = JsonUtilidades.partirObjetos(usuariosCrudo);
        Usuario[] usuarios = new Usuario[usuariosObj.length];

        for (int i = 0; i < usuariosObj.length; i++) {
            String usuario = JsonUtilidades.extraerString(usuariosObj[i], "usuario");
            String contrasena = JsonUtilidades.extraerString(usuariosObj[i], "contrasena");
            usuarios[i] = new Usuario(usuario, contrasena);
        }

        return new Configuracion(nombreTerminal, cantidadTotalBuses, buses, usuarios);
    }
}
