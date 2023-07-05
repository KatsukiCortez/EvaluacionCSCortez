package org.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import org.demo.modelo.PersonaModelo;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<PersonaModelo> personData = FXCollections.observableArrayList();
    
    public App(){
        // Agregar algunos datos
        personData.add(new PersonaModelo("Rocky","Balboa"));
        /*personData.add(new PersonaModelo("Avril", "Lavigne")); 
        personData.add(new PersonaModelo("Koe", "Wetzel"));
	personData.add(new PersonaModelo("David", "Bowie"));
	personData.add(new PersonaModelo("Peter", "Parker"));
	personData.add(new PersonaModelo("Wanda", "Maximoff"));
	personData.add(new PersonaModelo("Mario", "Vargas"));
	personData.add(new PersonaModelo("Linda", "Steff"));
	personData.add(new PersonaModelo("Lizeth", "Ortiz"));*/
    }
    
    public ObservableList<PersonaModelo> getPersonaData() {
        return personData;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda");
        
        // Agregar icono
        this.primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/img/icon.png")));
        
        
        initRootLayout();
        showPersonOverview();
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/VistaPrincipal.fxml"));
            rootLayout = (BorderPane) loader.load();
        
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/VistaAgenda.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
            
            controladorVistaAgenda controlador = loader.getController();
            controlador.setMainApp(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean showPersonEditDialog(PersonaModelo persona) {
        try {
            // Cargar archivo fxml 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/VistaEditaPersona.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crear dialogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Usar el controlador
            controladorDialogoPersona controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPersona(persona);

            // Mostrar hasta que el usuario cierre la ventana
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
