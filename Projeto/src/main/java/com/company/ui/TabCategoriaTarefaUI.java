/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.DefinirCategoriaTarefaController;
import com.company.model.CategoriaTarefa;
import com.company.model.CompetenciaTecnica;
import com.company.model.Tarefa;
import com.company.utils.Constantes;
import java.io.IOException;
import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabCategoriaTarefaUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private Stage novaCatTarefaStage;
    @FXML
    private TableView<CategoriaTarefa> tableViewCategoriaTarefa;
    @FXML
    private TableColumn<CategoriaTarefa, String> tableViewCatTarefaDesc;
    @FXML
    private TableColumn<CategoriaTarefa, String> tableViewCatTarefaAreaAtividade;
    @FXML
    private TableView<Map.Entry<CompetenciaTecnica, Boolean>> listViewCatTarefaListCompTecnica;
    @FXML
    private TableColumn<Map.Entry<CompetenciaTecnica, Boolean>, String> listViewCatTarefaCatTecnica;
    @FXML
    private TableColumn<Map.Entry<CompetenciaTecnica, Boolean>, String> listViewCatTarefaCatTecnicaMandatory;

    private DefinirCategoriaTarefaController catTarefaCtrll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableViewCatTarefaDesc.setCellValueFactory(new PropertyValueFactory<CategoriaTarefa, String>("descricao"));
        tableViewCatTarefaAreaAtividade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoriaTarefa,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoriaTarefa, String> p) {
                return new SimpleStringProperty("ID: "+p.getValue().getAreaAtividade().getId()
                        + "\n" + "Descrição: " + p.getValue().getAreaAtividade().getDescBreve());
            }

        });

        listViewCatTarefaCatTecnica.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<CompetenciaTecnica, Boolean>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<CompetenciaTecnica, Boolean>, String> p) {
                return new SimpleStringProperty("ID: " + p.getValue().getKey().getId() + "\n" + "DescBreve: " + p.getValue().getKey().getDescBreve());
            }
        });

        listViewCatTarefaCatTecnicaMandatory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<CompetenciaTecnica, Boolean>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<CompetenciaTecnica, Boolean>, String> p) {
                String mandatory = "";
                if (p.getValue().getValue()) {
                    mandatory = "Obrigatório";
                } else {
                    mandatory = "Optional";
                }

                return new SimpleStringProperty(mandatory);
            }
        });

    }

    @FXML
    private void actionAdicionarNovaCatTarefa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_CATTAREFA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaCatTarefaStage = new Stage();
            novaCatTarefaStage.initModality(Modality.APPLICATION_MODAL);
            novaCatTarefaStage.setTitle(Constantes.REGISTO_CATTAREFA_TITLE);
            novaCatTarefaStage.setResizable(false);
            novaCatTarefaStage.setScene(scene);

            AdicionarCategoriaTarefaUI adicionarCategoriaTarefaUI = loader.getController();
            adicionarCategoriaTarefaUI.associarParentUI(this);
            adicionarCategoriaTarefaUI.updateAA();

            novaCatTarefaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void updateList() {
        catTarefaCtrll = new DefinirCategoriaTarefaController();
        List<CategoriaTarefa> tmp = new ArrayList<>();
        tmp = catTarefaCtrll.getListaCatTarefa();

        if (tmp != null) {
            ObservableList<CategoriaTarefa> catTarefaList = FXCollections.observableArrayList(tmp);
            tableViewCategoriaTarefa.setItems(catTarefaList);
        }
    }

    @FXML
    private void mouseTabCat(MouseEvent event) {
        listViewCatTarefaListCompTecnica.getItems().clear();
        loadCompTecnica();

    }

    private void loadCompTecnica() {
        try {
            HashMap<CompetenciaTecnica, Boolean> tmp = new HashMap<CompetenciaTecnica, Boolean>();
            tmp = catTarefaCtrll.getCatTarefaHashMap(tableViewCategoriaTarefa.getSelectionModel().getSelectedItem());
            if (tmp != null && tmp.size() != 0) {
                ObservableList<Map.Entry<CompetenciaTecnica, Boolean>> CompTecnicaList = FXCollections.observableArrayList(tmp.entrySet());
                listViewCatTarefaListCompTecnica.setItems(CompTecnicaList);
            }
        } catch (Exception e) {
            //  AlertsUI.createErrorAlert(e, "Error at Load Products", e.getMessage()).show();
        }
    }

}
