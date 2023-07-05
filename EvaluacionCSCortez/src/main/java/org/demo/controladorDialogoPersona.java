package org.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUtil;
import org.demo.entidad.PersonaEntidad;
import org.demo.modelo.PersonaModelo;
import org.demo.utilidades.utilidadFecha;


public class controladorDialogoPersona {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtCodigoPostal;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCumpleanos;
    
    private Stage dialogStage;
    private PersonaModelo persona;
    private boolean okClicked = false;
    
    @FXML
    private void initialize(){
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setPersona(PersonaModelo persona) {
        this.persona = persona;

        txtNombre.setText(persona.getNombre());
        txtApellido.setText(persona.getApellido());
        txtDireccion.setText(persona.getDireccion());
        txtCodigoPostal.setText(Integer.toString(persona.getCodigoPostal()));
        txtCiudad.setText(persona.getCiudad());
        txtCumpleanos.setText(utilidadFecha.format(persona.getCumpleanos()));
        txtCumpleanos.setPromptText("dd.mm.yyyy");
    }
    
    public boolean isOkClicked(){
        
        return okClicked;
    }
    
    // Boton ok
    @FXML
    private void handleOk(){
        //PersistenceUtil.initialize();
        
        persona.setNombre(txtNombre.getText());
        persona.setApellido(txtApellido.getText());
        persona.setDireccion(txtDireccion.getText());
        persona.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
        persona.setCiudad(txtCiudad.getText());
        persona.setCumpleanos(utilidadFecha.parse(txtCumpleanos.getText()));

        PersonaEntidad personaen = new PersonaEntidad();
        personaen.setNombre(txtNombre.getText());
        personaen.setApellido(txtApellido.getText());
        personaen.setDireccion(txtDireccion.getText());
        personaen.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
        personaen.setCiudad(txtCiudad.getText());
        personaen.setCumpleanos(txtCumpleanos.getText());
        
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(personaen);
        em.getTransaction().commit();
        em.close();
        
        okClicked = true;
        dialogStage.close();
    }
    
    // Boton cancelar
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    // Validar entradas
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(txtNombre.getText()==null || txtNombre.getText().length()==0){
            errorMessage += "Nombre no valido\n";
        }
        
        if(txtApellido.getText()==null || txtApellido.getText().length()==0){
            errorMessage += "Apellido no valido\n";
        }
        
        if(txtDireccion.getText()==null || txtDireccion.getText().length()==0){
            errorMessage += "Direccion no valida\n";
        }
        
        if(txtCodigoPostal.getText()==null || txtCodigoPostal.getText().length()==0){
            errorMessage += "Codigo postal no valido\n";
        } else {
            try{
                Integer.parseInt(txtCodigoPostal.getText());
            }catch(NumberFormatException e){
                errorMessage += "Codigo postal no valido(no es un numero)";
            }
        }
        
        if (txtCiudad.getText() == null || txtCiudad.getText().length() == 0) {
            errorMessage += "Ciudad invalida\n"; 
        }

        if (txtCumpleanos.getText() == null || txtCumpleanos.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!utilidadFecha.validDate(txtCumpleanos.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrar el mensaje de error
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija campos");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}


