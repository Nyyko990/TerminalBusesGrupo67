/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistence;

/**
 * Aqui estaran los modelos de los buses donde se reconoceran de la siguiente forma:
 * P = Prefencial
 * D = Directo
 * N = Normal 
 * Prefencial y Directo solo se debe tener 1
 * @author lcast
 */
public class Bus {
    private int id;
    private char tipo;

    public Bus() {
    }

    public Bus(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
