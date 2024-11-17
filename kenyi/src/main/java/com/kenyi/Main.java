package com.kenyi;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin aplicacion = Javalin.create().start(3000);
        new App(aplicacion);
    }
}