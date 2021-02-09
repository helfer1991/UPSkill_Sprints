/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarAreaAtividadeController;
import com.company.controller.EspecificarCompetenciaTecnicaController;
import com.company.model.AreaAtividade;
import com.company.model.CompetenciaTecnica;
import com.company.model.Plataforma;
import com.company.model.Tarefa;
import com.company.utils.Constantes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class TabCompetenciaTecnicaUI implements Initializable {

    private MainSceneUI mainSceneUI;

    private Stage novaCTStage;

    @FXML
    private TableView<CompetenciaTecnica> tableViewCompTecnica;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewCodUnico;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewAreaAtividade;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewDescBreve;
    @FXML
    private TableColumn<CompetenciaTecnica, String> tableViewDescDetalhada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableViewCodUnico.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("id"));
        tableViewAreaAtividade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompetenciaTecnica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CompetenciaTecnica, String> p) {
                return new SimpleStringProperty("ID: " + p.getValue().getAreaAtividade().getId()
                        + "\n" + "Descrição: " + p.getValue().getAreaAtividade().getDescBreve());
            }

        });
        tableViewDescBreve.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("descBreve"));
        tableViewDescDetalhada.setCellValueFactory(new PropertyValueFactory<CompetenciaTecnica, String>("descDetalhada"));

    }

    @FXML
    private void actionAdicionarNovaCT(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_COMPTECNICA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            novaCTStage = new Stage();
            novaCTStage.initModality(Modality.APPLICATION_MODAL);
            novaCTStage.setTitle(Constantes.REGISTO_COMPTECNICA_TITLE);
            novaCTStage.setResizable(false);
            novaCTStage.setScene(scene);

            AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI = loader.getController();
            adicionarCompetenciaTecnicaUI.associarParentUI(this);
            adicionarCompetenciaTecnicaUI.updateAA();

            novaCTStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void refreshTable() {
        tableViewCompTecnica.getItems().setAll(parseCompTecnicaList());
    }

    private List<CompetenciaTecnica> parseCompTecnicaList() {
        return Plataforma.getInstance().getRct().getListaCompTec();
    }

    public void updateList() {
        EspecificarCompetenciaTecnicaController cCtrl = new EspecificarCompetenciaTecnicaController();
        List<CompetenciaTecnica> tmp = new ArrayList<>();
        tmp = cCtrl.getListCompTec();

        if (tmp != null) {
            ObservableList<CompetenciaTecnica> ctList = FXCollections.observableArrayList(tmp);
            tableViewCompTecnica.setItems(ctList);
        }

    }
}
