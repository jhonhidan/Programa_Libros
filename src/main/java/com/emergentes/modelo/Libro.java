/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.modelo;

/**
 *
 * @author Jhonny
 */
public class Libro {

    private int id;
    private String isbm;
    private String titulo;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Libro() {
        this.id = 0;
        this.isbm = "";
        this.titulo = "";
        this.categoria = "";

    }

 
    @Override
    public String toString(){
        return "Libro{"+"id="+id+",isbm="+isbm+",titulo="+titulo+",categoria="+categoria+'}';
    }

}
