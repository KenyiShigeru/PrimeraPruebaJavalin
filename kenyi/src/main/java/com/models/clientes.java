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
            //Los context tienen dos tipos de resultados un texto y un json
        }
    };

    private Handler insertarCliente = ctx ->
    {
        try{
            consulta = "'CALL agg_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)'";
            List<Map<String,Object>> resultado = DatabaseConnection.listForQuery(consulta);
            DatabaseConnection.CerrarConsulta();
            ctx.result("Agregado correctamente");
        }
        catch(Exception ex)
        {
            ctx.result(ex.getMessage());
            //Los context tienen dos tipos de resultados un texto y un json
        }
    };

}
