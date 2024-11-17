package com.kenyi;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static Dotenv dotenv = Dotenv.load();

    // Datos de la conexión
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USERNAME");  // Usuario de la base de datos
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");  // Contraseña de la base de datos
    private static Connection connection;

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            // Conexión a la base de datos
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para ejecutar una consulta y devolver los resultados como un List<Map<String, Object>>
    public static List<Map<String, Object>> listForQuery(String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        // Establecer la conexión con la base de datos
        try {
            connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

            // Obtener los metadatos para saber los nombres de las columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Iterar sobre los resultados
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                
                // Iterar sobre cada columna
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);  // Obtener el nombre de la columna
                    Object columnValue = resultSet.getObject(i);    // Obtener el valor de la columna
                    row.put(columnName, columnValue);                // Agregar al mapa
                }
                
                results.add(row);  // Agregar la fila (mapa) a la lista de resultados
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static void CerrarConsulta() throws SQLException
    {
        connection.close();
    }
}
