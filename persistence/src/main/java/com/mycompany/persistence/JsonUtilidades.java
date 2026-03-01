/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistence;

/**
 *
 * @author lcast
 */
public class JsonUtilidades {
    //Escapa comillas y slash para meter texto dentro de Json.
    public static String escapar(String texto) {
        if (texto == null) return "";
        return texto.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    //Extrae un String del JSON usando una clave, ejemplo: "nombreTerminal":"Terminal X"

    public static String extraerString(String json, String clave) {
        String patron = "\"" + clave + "\":";
        int i = json.indexOf(patron);
        if (i < 0) return null;

        int inicioComillas = json.indexOf("\"", i + patron.length());
        if (inicioComillas < 0) return null;

        int finComillas = buscarFinDeString(json, inicioComillas + 1);
        if (finComillas < 0) return null;

        return json.substring(inicioComillas + 1, finComillas)
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }


     //Extrae un int del JSON usando una clave, ejemplo: "cantidadTotalBuses":5

    public static int extraerInt(String json, String clave) {
        String patron = "\"" + clave + "\":";
        int i = json.indexOf(patron);
        if (i < 0) return -1;

        int j = i + patron.length();
        while (j < json.length() && Character.isWhitespace(json.charAt(j))) j++;

        int k = j;
        while (k < json.length() && (Character.isDigit(json.charAt(k)) || json.charAt(k) == '-')) k++;

        String numero = json.substring(j, k);
        return Integer.parseInt(numero);
    }

    //Busca el fin de un string JSON considerando escapes 
    private static int buscarFinDeString(String json, int desde) {
        boolean escape = false;
        for (int i = desde; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escape) {
                escape = false;
                continue;
            }
            if (c == '\\') {
                escape = true;
                continue;
            }
            if (c == '"') return i;
        }
        return -1;
    }


    //Obtiene el contenido de un arreglo JSON por clave, sin los corchetes. Ejemplo: "buses":[ {...},{...} ] y Devuelve el texto entre [ y ].

    public static String extraerArregloCrudo(String json, String clave) {
        String patron = "\"" + clave + "\":[";
        int i = json.indexOf(patron);
        if (i < 0) return null;

        int inicio = i + patron.length();
        int fin = encontrarCierreArreglo(json, inicio - 1);
        if (fin < 0) return null;

        return json.substring(inicio, fin).trim(); // dentro del arreglo
    }

    private static int encontrarCierreArreglo(String json, int desdeCorcheteAbre) {
        int nivel = 0;
        for (int i = desdeCorcheteAbre; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '[') nivel++;
            if (c == ']') {
                nivel--;
                if (nivel == 0) return i;
            }
        }
        return -1;
    }


    //Divide objetos de un arreglo JSON: {...},{...},{...} Retorna String[] con cada objeto {...}
    public static String[] partirObjetos(String contenidoArreglo) {
        if (contenidoArreglo == null || contenidoArreglo.isEmpty()) {
            return new String[0];
        }

        // contar objetos por llaves de nivel 0
        int contador = 0;
        int nivel = 0;
        for (int i = 0; i < contenidoArreglo.length(); i++) {
            char c = contenidoArreglo.charAt(i);
            if (c == '{') {
                if (nivel == 0) contador++;
                nivel++;
            } else if (c == '}') {
                nivel--;
            }
        }

        String[] objetos = new String[contador];
        int idx = 0;

        int inicio = -1;
        nivel = 0;
        for (int i = 0; i < contenidoArreglo.length(); i++) {
            char c = contenidoArreglo.charAt(i);
            if (c == '{') {
                if (nivel == 0) inicio = i;
                nivel++;
            } else if (c == '}') {
                nivel--;
                if (nivel == 0 && inicio >= 0) {
                    objetos[idx] = contenidoArreglo.substring(inicio, i + 1);
                    idx++;
                    inicio = -1;
                }
            }
        }

        return objetos;
    }  
}
