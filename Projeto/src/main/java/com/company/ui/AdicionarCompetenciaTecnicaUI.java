/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.EspecificarAreaAtividadeController;
import com.company.controller.EspecificarCompetenciaTecnicaController;
import com.company.controller.EspecificarTarefaController;
import com.company.controller.RegistarOrganizacaoController;
import com.company.model.AreaAtividade;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarCompetenciaTecnicaUI implements Initializable {

    private TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI;

    @FXML
    private TextField txtCodUnico;
    @FXML
    private TextField txtDescBreve;
    @FXML
    private TextArea txtDescDetalhada;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> cmbAreaAtividade;
    private EspecificarCompetenciaTecnicaController ctCtrl;
    private EspecificarAreaAtividadeController aaCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ctCtrl = new EspecificarCompetenciaTecnicaController();
        this.aaCtrl = new EspecificarAreaAtividadeController();
    }

    void updateAA() {
        ctCtrl = new EspecificarCompetenciaTecnicaController();

        ObservableList obList = FXCollections.observableList(ctCtrl.getListaAreaAtividadeIdDescBreve());

        this.cmbAreaAtividade.getItems().clear();
        this.cmbAreaAtividade.setItems(obList);
    }

    @FXML
    private void actionAdicionarNovaCompetenciaTecnica(ActionEvent event) {
        ctCtrl = new EspecificarCompetenciaTecnicaController();
        if (cmbAreaAtividade.getValue() != null) {
            try {
                AreaAtividade aa = ctCtrl.getAreaAtividadeById(cmbAreaAtividade.getValue());
                if (ctCtrl.novaCompetenciaTecnica(txtCodUnico.getText(), txtDescBreve.getText(), txtDescDetalhada.getText(), aa)) {
                    showAACreationSuccess();
                    tabCompetenciaTecnicaUI.updateList();
                    encerrarNovaCompetenciaTecnicaUI(event);
                }
            } catch (Exception ex) {
                showIncorrectInformation(ex);
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO, "Insira uma Area de Atividade", "Não pode criar uma Competencia Tecnica sem uma Area de Atividade").show();
        }

    }

    @FXML
    private void actionCancel(ActionEvent event) {
        if (txtCodUnico.getText().isEmpty() && txtDescBreve.getText().isEmpty() && txtDescDetalhada.getText().isEmpty() && cmbAreaAtividade.getValue() == null) {
            encerrarNovaCompetenciaTecnicaUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume();
            } else {
                encerrarNovaCompetenciaTecnicaUI(event);
            }
        }
    }

    void associarParentUI(TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI) {
        this.tabCompetenciaTecnicaUI = tabCompetenciaTecnicaUI;
    }

    private void encerrarNovaCompetenciaTecnicaUI(ActionEvent event) {
        this.txtCodUnico.clear();
        this.txtDescBreve.clear();
        this.txtDescDetalhada.clear();
        this.cmbAreaAtividade.getSelectionModel().clearSelection();
        this.cmbAreaAtividade.setValue(null);

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Competencia Tecnica adicionada");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Competencia Tecnica.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    private void actionLimitTxtCodUnico(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (!event.getCode().isDigitKey()) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Codigo Unico tem de ser Numerico (0-9)");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        } 
    }
    
    @FXML
    private void actionLimitTxtDescBreve(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtDescBreve.getText().length() > 24) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Breve é 25 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void actionLimitTxtDescDetalhada(KeyEvent event) {
         if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtDescDetalhada.getText().length() > 99) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Detalhada é 100 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }
    


    

}
