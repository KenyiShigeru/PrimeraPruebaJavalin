package com.models;
import java.util.*;
import com.kenyi.DatabaseConnection;
import io.javalin.Javalin;
import io.javalin.http.*;

public class Clientes {
    private String consulta;
    
    public Clientes(Javalin app)
    {
        app.get("/clientes", obtenerClientes);
    }

    private Handler obtenerClientes = ctx ->
    {
        try{
            consulta = "CALL consulta_producto(\"\");";
            List<Map<String,Object>> resultado = DatabaseConnection.listForQuery(consulta);
            DatabaseConnection.CerrarConsulta();
            ctx.json(resultado);
        }
        catch(Exception ex)
        {
            ctx.result(ex.getMessage());
        }
        /* ctx.result("Aqui va un mensaje");
        tipos de salidas que puede dar una funcion que opera con context
        todas las rutas deben llevar como segundo parametro un context
        */
    };
}
