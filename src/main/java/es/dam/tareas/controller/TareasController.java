package es.dam.tareas.controller;

import es.dam.tareas.dao.TareaDao;
import es.dam.tareas.model.*;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class TareasController {

	/* ------------ FXML vínculos ------------ */
	@FXML
	private TableView<Tarea> tabla;
	@FXML
	private TableColumn<Tarea, String> colTitulo;
	@FXML
	private TableColumn<Tarea, LocalDate> colFecha;
	@FXML
	private TableColumn<Tarea, Estado> colEstado;
	@FXML
	private TableColumn<Tarea, Prioridad> colPrioridad;
	@FXML
	private ComboBox<String> comboFiltro;

	@FXML
	private Button btnNueva, btnEditar, btnHecha, btnManiana, btnBorrar;

	/* ------------ datos ------------ */
	private final TareaDao dao = new TareaDao();
	private final ObservableList<Tarea> datos = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		// mapeo de columnas
		colTitulo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitulo()));
		colFecha .setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getVencimiento()));
		colEstado.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEstado()));
		colPrioridad.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPrioridad()));


		// cargar BD
		datos.setAll(dao.listar());
		tabla.setItems(datos);

		// valores del combo
		comboFiltro.getItems().addAll("Todas", "Pendientes", "Hechas");
		comboFiltro.getSelectionModel().selectFirst();
		comboFiltro.valueProperty().addListener((obs, ant, act) -> aplicarFiltro());

		// deshabilitar botones vacíos
		tabla.getSelectionModel().selectedItemProperty().addListener((obs, ant, sel) -> actualizarBotones());
		actualizarBotones();
	}

	/* ---------- acciones ---------- */

	@FXML
	private void onNueva() {
		// diálogo mínimo usando TextInputDialog; se reemplazará por formulario FXML en
		// fase 3
		TextInputDialog d = new TextInputDialog();
		d.setHeaderText("Nueva tarea – título");
		d.showAndWait().ifPresent(titulo -> {
			Tarea t = new Tarea(titulo, "", LocalDate.now(), Estado.PENDIENTE, Prioridad.NORMAL);
			dao.crear(t);
			datos.add(t); // refresco inmediato
		});
	}

	@FXML
	private void onEditar() {
	    // 1. Obtener la tarea seleccionada
	    Tarea sel = tabla.getSelectionModel().getSelectedItem();
	    if (sel == null) {
	        // Nada seleccionado → salir
	        return;
	    }

	    // 2. Mostrar diálogo para editar el título (por ahora solo eso)
	    TextInputDialog dialog = new TextInputDialog(sel.getTitulo());
	    dialog.setTitle("Editar tarea");
	    dialog.setHeaderText("Modificar título de la tarea");
	    dialog.setContentText("Título:");

	    dialog.showAndWait().ifPresent(nuevoTitulo -> {
	        // 3. Validar que no esté vacío
	        String tituloTrim = nuevoTitulo.trim();
	        if (tituloTrim.isEmpty()) {
	            // Mostrar alerta simple
	            new Alert(Alert.AlertType.WARNING, "El título no puede estar vacío.").showAndWait();
	            return;
	        }

	        // 4. Actualizar objeto y BD
	        sel.setTitulo(tituloTrim);
	        dao.actualizar(sel);     // requiere que el método actualizar() exista en TareaDao
	        tabla.refresh();         // refresca la fila en la TableView
	    });
	}
	
	@FXML
	private void onBorrar() {
		Tarea sel = tabla.getSelectionModel().getSelectedItem();
		if (sel == null)
			return;
		dao.borrar(sel.getId()); // ← añade este método en el DAO
		datos.remove(sel);
	}

	@FXML
	private void onHecha() {
		Tarea sel = tabla.getSelectionModel().getSelectedItem();
		if (sel == null)
			return;
		sel.setEstado(Estado.HECHA);
		dao.actualizar(sel); // método pendiente en DAO
		tabla.refresh();
	}

	@FXML
	private void onManiana() {
		Tarea sel = tabla.getSelectionModel().getSelectedItem();
		if (sel == null)
			return;
		sel.setVencimiento(sel.getVencimiento().plusDays(1));
		dao.actualizar(sel);
		tabla.refresh();
	}

	/* ---------- utilidades ---------- */

	private void aplicarFiltro() {
		String f = comboFiltro.getValue();
		if ("Todas".equals(f)) {
			tabla.setItems(datos);
		} else {
			Estado e = "Pendientes".equals(f) ? Estado.PENDIENTE : Estado.HECHA;
			FilteredList<Tarea> list = datos.filtered(t -> t.getEstado() == e);
			tabla.setItems(list);
		}
	}

	private void actualizarBotones() {
		boolean sel = tabla.getSelectionModel().getSelectedItem() != null;
		btnEditar.setDisable(!sel);
		btnHecha.setDisable(!sel);
		btnManiana.setDisable(!sel);
		btnBorrar.setDisable(!sel);
	}
}
