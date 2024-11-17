package com.kenyi;
import io.javalin.*;
import com.models.*;
public class App {
    public App(Javalin app)
    {
        app.get("/", clientes::obtenerClientes);
    }
}
