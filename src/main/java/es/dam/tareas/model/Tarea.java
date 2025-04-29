package es.dam.tareas.model;

import java.time.LocalDate;

public class Tarea {		
		private Integer id;
		private String titulo;
		private String descripcion;
		private LocalDate vencimiento;
		private Estado estado;
		private Prioridad prioridad;
		
		// Constructores
		
		// Vacio para JDBC
		public Tarea() {};
		
		// Sin ID
		public Tarea(String titulo, String descripcion, LocalDate vencimiento, Estado estado, Prioridad prioridad) {
			this(null, titulo, descripcion, vencimiento, estado, prioridad);
		}
		
		// Completo
		public Tarea(Integer id, String titulo, String descripcion, LocalDate vencimiento, Estado estado, Prioridad prioridad) {
			this.id = id;
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.vencimiento = vencimiento;
			this.estado = estado;
			this.prioridad = prioridad;
		}

		
		
		// Getters y setters
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public LocalDate getVencimiento() {
			return vencimiento;
		}

		public void setVencimiento(LocalDate vencimiento) {
			this.vencimiento = vencimiento;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		public Prioridad getPrioridad() {
			return prioridad;
		}

		public void setPrioridad(Prioridad prioridad) {
			this.prioridad = prioridad;
		}	
		@Override
		public String toString() {
		    return "[%d] %s – %s (%s)".formatted(
		        id != null ? id : 0,
		        titulo,
		        estado,
		        prioridad
		    );
		}
	}
