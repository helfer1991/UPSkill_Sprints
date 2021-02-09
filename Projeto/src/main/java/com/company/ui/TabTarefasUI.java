/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarColaboradorController;
import com.company.controller.EspecificarTarefaController;
import com.company.model.CategoriaTarefa;
import com.company.model.Colaborador;
import com.company.model.CompetenciaTecnica;
import com.company.model.Tarefa;
import com.company.ui.AdicionarTarefaUI;
import com.company.ui.AlertaUI;
import com.company.ui.App;
import com.company.ui.MainSceneUI;
import com.company.utils.Constantes;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabTarefasUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private Stage novaTarefaStage;

    @FXML
    private TableView<Tarefa> tableViewTarefas;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefaDesignacao;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefaDescInformal;
    @FXML
    private TableColumn<Tarefa, String> tableViewDescricaoTecnica;
    @FXML
    private TableColumn<Tarefa, Integer> tableViewTarefaDuracao;
    @FXML
    private TableColumn<Tarefa, Double> tableViewTarefasCusto;
    @FXML
    private TableColumn<Tarefa, String> tableViewTarefasCatTarefa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewTarefaDesignacao.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("designacao"));
        tableViewTarefaDescInformal.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("descrInformal"));
        tableViewDescricaoTecnica.setCellValueFactory(new PropertyValueFactory<Tarefa, String>("descrTecnica"));

        tableViewTarefaDuracao.setCellValueFactory(new PropertyValueFactory<Tarefa, Integer>("duracaoEst"));
        tableViewTarefasCusto.setCellValueFactory(new PropertyValueFactory<Tarefa, Double>("custoEst"));
        tableViewTarefasCatTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tarefa,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tarefa, String> p) {
                return new SimpleStringProperty(p.getValue().getCatTarefa().getId()
                        + "\n" + "Descrição: " + p.getValue().getCatTarefa().getDescricao());
            }

        });
        updateList();
    }

    @FXML
    private void actionAdicionarNovaTarefa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_TAREFA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaTarefaStage = new Stage();
            novaTarefaStage.initModality(Modality.APPLICATION_MODAL);
            novaTarefaStage.setTitle(Constantes.REGISTO_TAREFA_TITLE);
            novaTarefaStage.setResizable(false);
            novaTarefaStage.setScene(scene);

            AdicionarTarefaUI adicionarTarefaUI = loader.getController();
            adicionarTarefaUI.associarParentUI(this);
            adicionarTarefaUI.updateCatTarCMB();

            novaTarefaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }

    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void updateList() {

        EspecificarTarefaController cCtrl = new EspecificarTarefaController();
        if (cCtrl.getListaTarefas() != null) {
            ObservableList<Tarefa> tarList = FXCollections.observableArrayList(cCtrl.getListaTarefas());
            tableViewTarefas.setItems(tarList);
        }
    }

}
