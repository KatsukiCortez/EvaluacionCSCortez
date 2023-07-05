










package org.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.demo.modelo.Persona;
import org.demo.utilidades.utilidadFecha;


public class controladorVistaAgenda {
    @FXML
    private TableView<Persona> personaTabla;
    @FXML
    private TableColumn<Persona, String> nombreColumna;
    @FXML
    private TableColumn<Persona, String> apellidoColumna;

    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblCodigoPostal;
    @FXML
    private Label lblCiudad;
    @FXML
    private Label lblCumpleanos;

    // Referencia a la aplicación principal
    private App mainApp;

    // Constructor
    public controladorVistaAgenda() {
    }

    /* Inicializar controlador, el método será llamado después de
    que el archivo fxml sea cargado */
    @FXML
    private void initialize() {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidoColumna.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());

        // Limpia los detalles de la persona al inicio
        showPersonDetails(null);

        // Escucha los cambios de selección y muestra los detalles de la persona seleccionada
        personaTabla.getSelectionModel().selectedItemProperty().addListener
         ((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        // Agrega la lista observable a la tabla
        personaTabla.setItems(mainApp.getPersonaData());
    }

    private void showPersonDetails(Persona persona) {
        if (persona != null) {
            lblNombre.setText(persona.getNombre());
            lblApellido.setText(persona.getApellido());
            lblDireccion.setText(persona.getDireccion());
            lblCodigoPostal.setText(Integer.toString(persona.getCodigoPostal()));
            lblCiudad.setText(persona.getCiudad());
            lblCumpleanos.setText(utilidadFecha.format(persona.getCumpleanos()));
        } else {
            lblNombre.setText("");
            lblApellido.setText("");
            lblDireccion.setText("");
            lblCodigoPostal.setText("");
            lblCiudad.setText("");
            lblCumpleanos.setText("");
        }
    }
    // METODO DE BORRADO
    
    @FXML
    public void handleDeletePerson() {
    int selectedIndex = personaTabla.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            personaTabla.getItems().remove(selectedIndex);
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    // CLICK EN BOTON NUEVA
    @FXML
    private void handleNewPerson() {
        Persona tempPerson = new Persona();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonaData().add(tempPerson);
        }
    }
    
    // CLICK EN BOTON EDITAR
    @FXML
    private void handleEditPerson() {
    Persona selectedPerson = personaTabla.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
}