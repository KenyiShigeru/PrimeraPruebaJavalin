package com.models;
import java.util.*;
import io.javalin.http.Context;
public class clientes {
    public static void obtenerClientes(Context ctx)
    {
        Map<String, Object> response = new HashMap<>();
            response.put("id", 1);
            response.put("name", "John Doe");
            response.put("email", "john.doe@example.com");
        ctx.json(response);
        /* ctx.result("Aqui va un mensaje");
        tipos de salidas que puede dar una funcion que opera con context
        todas las rutas deben llevar como segundo parametro un context
        */
    }
}
