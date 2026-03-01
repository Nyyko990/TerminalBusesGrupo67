/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistence;

import javax.swing.JOptionPane;

/**
 * Aqui vamos a probar que los .jsoin se creen o sse carguien, comentando o descomentando
 * lo que queremos probar
 * @author lcast
 */
public class Persistence {

    public static void main(String[] args) {
        //Probart crear .Json
//        try {
//
//            ConfigRepository repositorio = new ConfigRepository();
//
//            // Crear buses manualmente
//            Bus[] buses = new Bus[3];
//            buses[0] = new Bus(1, 'P');
//            buses[1] = new Bus(2, 'D');
//            buses[2] = new Bus(3, 'N');
//
//            // Crear usuarios manualmente
//            Usuario[] usuarios = new Usuario[2];
//            usuarios[0] = new Usuario("Admin", "Admin1234");
//            usuarios[1] = new Usuario("Usuario", "Fide123");
//
//            Configuracion config = new Configuracion(
//                    "Terminal Central",
//                    3,
//                    buses,
//                    usuarios
//            );
//
//            repositorio.save(config);
//
//            JOptionPane.showMessageDialog(null,
//                    "config.json creado correctamente.");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,
//                    "Error: " + e.getMessage());
//        }
//    }
     // Probar cargar .Json
            try {

            ConfigRepository repositorio = new ConfigRepository();

            Configuracion config = repositorio.load();

            JOptionPane.showMessageDialog(null,
                    "Terminal: " + config.getNombreTerminal()
                    + "\nCantidad Buses: " + config.getCantidadTotalBuses()
                    + "\nPrimer Usuario: " + config.getUsuarios()[0].getUsuario()
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e.getMessage());
        }
    }
}
