package es.dam.tareas.dao;

import java.nio.file.Path;
import java.sql.*;

public class DbUtil {
	
	// Ruta del JAR	
	private static final String URL = "jdbc:sqlite:" + Path.of("data", "tareas.db").toString();
	
	private DbUtil() { }
	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		
		// Se crea la tabla con una sola ejecución
		try (Statement st = conn.createStatement()) {
			st.execute("""
	                CREATE TABLE IF NOT EXISTS tareas (
	                        id INTEGER PRIMARY KEY AUTOINCREMENT,
	                        titulo      TEXT    NOT NULL,
	                        descripcion TEXT,
	                        vencimiento DATE,
	                        estado      TEXT    DEFAULT 'PENDIENTE',
	                        prioridad   TEXT    DEFAULT 'NORMAL'
	                      )
	                  """);
		}
		return conn;
	}


}
