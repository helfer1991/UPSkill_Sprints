/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.DefinirCategoriaTarefaController;
import com.company.model.AreaAtividade;
import com.company.model.CompetenciaTecnica;
import com.company.model.Plataforma;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarCategoriaTarefaUI implements Initializable {

    private TabCategoriaTarefaUI tabCategoriaTarefaUI;
    @FXML
    private TextArea txtCategoriaTarefaDesc;
    @FXML
    private ComboBox<String> cmbCategoriaTarefaAreaAtividade;
    @FXML
    private ListView<String> listViewCompTecnicaExistentes;
    @FXML
    private ListView<String> listViewCompTecnicaSelected;

    ObservableList listAreasAtividade = FXCollections.observableArrayList();
    ObservableList listCompTecExist = FXCollections.observableArrayList();
    ObservableList listCompTecSelec = FXCollections.observableArrayList();

    private DefinirCategoriaTarefaController catTarefaCtrll;
    private AreaAtividade areaAtividade;
    private String compTecExistenteSelec; // é o que escolhi!!!!!
    private String compTecSelecionadasSelec; // a que quero remover

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.catTarefaCtrll = new DefinirCategoriaTarefaController();

    }

    /**
     * Atualiza a lista de áreas de atividade na comboBox.
     */
    public void updateAA() {
        catTarefaCtrll = new DefinirCategoriaTarefaController();

        ObservableList obList = FXCollections.observableList(catTarefaCtrll.getListaAreaAtividadeDescBreve());

        this.cmbCategoriaTarefaAreaAtividade.getItems().clear();
        this.cmbCategoriaTarefaAreaAtividade.setItems(obList);
    }

    // adiciona uma nova categoria de tarefa e apresenta mensagem de erro caso não seja possível
    @FXML
    private void actionAddNovaCategoriaTarefa(ActionEvent event) {
        try {
            HashMap<CompetenciaTecnica, Boolean> lcompTec = catTarefaCtrll.getBuiltMap();
            if (catTarefaCtrll.novaCategoriaTarefa(txtCategoriaTarefaDesc.getText(), areaAtividade, catTarefaCtrll.getBuiltMap())) {
                if (catTarefaCtrll.registaCategoriaTarefa()) {
                    showAACreationSuccess();
                    tabCategoriaTarefaUI.updateList();
                    encerrarNovaCatTarefaUI(event);
                }
            }
        } catch (Exception ex) {
            showIncorrectInformation(ex);
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro ao adicionar Categoria de Tarefa.", ex.getMessage());
        }
    }

    // verifica qual a área de atividade selecionada e apresenta as respectivas competências técnicas
    @FXML
    private void cmbSelecionarAreaAtividade(ActionEvent event) {

        if ((!cmbCategoriaTarefaAreaAtividade.getValue().isEmpty())) {
            for (AreaAtividade aat : catTarefaCtrll.getListaAreasAtividade()) {
                String a = cmbCategoriaTarefaAreaAtividade.getValue().split(" ")[1];
                if (aat.getId().equals(a)) {
                    areaAtividade = aat;
                }

                listViewCompTecnicaExistentes.getItems().clear();
                listCompTecExist.clear();
                listViewCompTecnicaSelected.getItems().clear();
                listCompTecSelec.clear();

                //percorre a lista de competencias técnicas existente na plataforma, filtra por AreaAtividade escolhida na comboBox e
                //adiciona a uma lista para representar no ListView
//              for (CompetenciaTecnica ct : Plataforma.getInstance().getRct().getListaCompTec()) {
                for (CompetenciaTecnica ct : catTarefaCtrll.getListaCompetenciasTecnicas()) {
                    if (ct.getAreaAtividade().equals(areaAtividade)) {
                        listCompTecExist.add(ct.getDescBreve());
                    }
                }
                listViewCompTecnicaExistentes.getItems().addAll(listCompTecExist);
            }

        }
    }

    void associarParentUI(TabCategoriaTarefaUI tabCategoriaTarefaUI) {
        this.tabCategoriaTarefaUI = tabCategoriaTarefaUI;
    }

    private void encerrarNovaCatTarefaUI(ActionEvent event) {
        this.txtCategoriaTarefaDesc.clear();
        this.listViewCompTecnicaExistentes.getItems().clear();
        this.listViewCompTecnicaSelected.getItems().clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Area adicionada");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        encerrarNovaCatTarefaUI(event);
    }

    private void addCompetenciaTecnicaCT(String descricao, Boolean obrigatoriedade) {
        for (CompetenciaTecnica ct : catTarefaCtrll.getListaCompetenciasTecnicas()) {
            if (ct.getAreaAtividade().equals(areaAtividade) && ct.getDescBreve().equalsIgnoreCase(descricao)) {
                catTarefaCtrll.addCompTecnicaToList(ct, obrigatoriedade);
            }
        }
    }

    private void removeCompetenciaTecnicaCT(String descricao) {
        for (CompetenciaTecnica ct : catTarefaCtrll.getListaCompetenciasTecnicas()) {
            if (ct.getAreaAtividade().equals(areaAtividade) && ct.getDescBreve().equalsIgnoreCase(descricao)) {
                catTarefaCtrll.RemoveCompTecnicaFromList(ct);
            }
        }
    }

    @FXML
    private void actionAddCTObrigatorio(ActionEvent event) {
        if (!(compTecExistenteSelec == null || compTecExistenteSelec.isEmpty() || listViewCompTecnicaSelected.getItems().contains(compTecExistenteSelec))) {
            listViewCompTecnicaSelected.getItems().add(compTecExistenteSelec);
            listViewCompTecnicaExistentes.getItems().remove(compTecExistenteSelec);
            addCompetenciaTecnicaCT(compTecExistenteSelec, true);
            compTecExistenteSelec = null;
            compTecSelecionadasSelec = null;
        }
    }

    @FXML
    private void actionAddCTOpcional(ActionEvent event) {
        if (!(compTecExistenteSelec == null || compTecExistenteSelec.isEmpty() || listViewCompTecnicaSelected.getItems().contains(compTecExistenteSelec))) {
            listViewCompTecnicaSelected.getItems().add(compTecExistenteSelec);
            listViewCompTecnicaExistentes.getItems().remove(compTecExistenteSelec);
            addCompetenciaTecnicaCT(compTecExistenteSelec, false);
            compTecExistenteSelec = null;
            compTecSelecionadasSelec = null;
        }
    }

    @FXML
    private void actionRemoverCT(ActionEvent event) {
        if (compTecSelecionadasSelec != null) {
            if (!(listViewCompTecnicaExistentes.getItems().contains(compTecSelecionadasSelec))) {
                listViewCompTecnicaSelected.getItems().remove(compTecSelecionadasSelec);
                listViewCompTecnicaExistentes.getItems().add(compTecSelecionadasSelec);
                removeCompetenciaTecnicaCT(compTecSelecionadasSelec);
                compTecSelecionadasSelec = null;
            }
        } else {
            event.consume();
        }

    }

    @FXML
    private void mouseListViewCompTecnicaExistentes(MouseEvent event) {
        compTecExistenteSelec = listViewCompTecnicaExistentes.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void mouseListViewCompTecnicaSelec(MouseEvent event) {
        compTecSelecionadasSelec = listViewCompTecnicaSelected.getSelectionModel().getSelectedItem();
    }
}
