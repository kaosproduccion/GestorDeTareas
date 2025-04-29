import java.time.LocalDate;

import es.dam.tareas.dao.TareaDao;
import es.dam.tareas.model.Estado;
import es.dam.tareas.model.Prioridad;
import es.dam.tareas.model.Tarea;

public class PruebaDAO {
	public static void main(String[] args) {
		TareaDao dao = new TareaDao();
		dao.crear(new Tarea("Probar DAO", "Inserción inicial", LocalDate.now(), Estado.PENDIENTE, Prioridad.NORMAL));
		dao.listar().forEach(System.out::println);
	}
}
