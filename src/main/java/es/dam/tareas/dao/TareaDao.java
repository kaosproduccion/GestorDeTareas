package es.dam.tareas.dao;

import es.dam.tareas.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TareaDao {

	// Alta
	public void crear(Tarea t) {
		final String sql = """
				INSERT INTO tareas (titulo, descripcion, vencimiento, estado, prioridad)
				VALUES (?,?,?,?,?)
				""";

		try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, t.getTitulo());
			ps.setString(2, t.getDescripcion());
			ps.setString(3, t.getVencimiento() != null ? t.getVencimiento().toString() : null);
			ps.setString(4, t.getEstado().name());
			ps.setString(5, t.getPrioridad().name());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Actualizar
	public void actualizar(Tarea t) {
	    final String sql = """
	        UPDATE tareas
	        SET titulo=?, descripcion=?, vencimiento=?, estado=?, prioridad=?
	        WHERE id=?
	        """;
	    try (Connection c = DbUtil.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setString(1, t.getTitulo());
	        ps.setString(2, t.getDescripcion());
	        ps.setString(3, t.getVencimiento() != null ? t.getVencimiento().toString() : null);
	        ps.setString(4, t.getEstado().name());
	        ps.setString(5, t.getPrioridad().name());
	        ps.setInt   (6, t.getId());
	        ps.executeUpdate();
	    } catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	// Borrar por ID
	public void borrar(int id) {
	    try (Connection c = DbUtil.getConnection();
	         PreparedStatement ps = c.prepareStatement("DELETE FROM tareas WHERE id=?")) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) { e.printStackTrace(); }
	}
	
	// Listado
	public List<Tarea> listar() {
		List<Tarea> lista = new ArrayList<>();
		String sql = "SELECT * FROM tareas ORDER BY vencimiento";
		try (	Connection c = DbUtil.getConnection();
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
					
					while (rs.next()) {
						Tarea t = new Tarea (
						rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("vencimiento") != null
                            ? LocalDate.parse(rs.getString("vencimiento"))
                            : null,
                        Estado.valueOf(rs.getString("estado")),
                        Prioridad.valueOf(rs.getString("prioridad")));
						lista.add(t);
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return lista;
	}

	// Añadir actualizar, borrar, mover días a siguiente, etc
}
