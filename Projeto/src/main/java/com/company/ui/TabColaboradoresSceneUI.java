/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarColaboradorController;
import com.company.model.Colaborador;
import com.company.utils.Constantes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabColaboradoresSceneUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private Stage novoColaboradorStage;

    @FXML
    private TableView<Colaborador> tableViewColaboradores;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresNome;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresEmail;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresTelefone;
    @FXML
    private TableColumn<Colaborador, String> tableViewEmpty;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_COLABORADOR_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            
            novoColaboradorStage = new Stage();
            novoColaboradorStage.initModality(Modality.APPLICATION_MODAL);
            novoColaboradorStage.setTitle(Constantes.REGISTO_COLABORADOR_TITLE);
            novoColaboradorStage.setResizable(false);
            novoColaboradorStage.setScene(scene);

            tableViewColaboradoresNome.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("nome"));
            tableViewColaboradoresEmail.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("email"));
            tableViewColaboradoresTelefone.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("telefone"));

            updateList();
            AdicionarColaboradorUI adicionarColaboradorUI = loader.getController();
            adicionarColaboradorUI.associarParentUI(this);


        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    @FXML
    private void actionAdicionarNovoColaborador(ActionEvent event) {
        novoColaboradorStage.show();
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void updateList() {
        EspecificarColaboradorController cCtrl = new EspecificarColaboradorController();

        ObservableList<Colaborador> ctList = FXCollections.observableArrayList(cCtrl.getListColaboradores());
        tableViewColaboradores.setItems(ctList);
    }
}
