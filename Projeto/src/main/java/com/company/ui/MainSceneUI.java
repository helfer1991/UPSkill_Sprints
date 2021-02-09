/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.MainSceneController;
import com.company.controller.UsersAPI;
import com.company.model.Plataforma;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class MainSceneUI implements Initializable {

    private LoginUI loginUI;
    private TabAreasAtividadeUI tabAreasAtividadeUI;
    private TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI;
    private TabColaboradoresSceneUI tabColaboradoresSceneUI;
    private TabCategoriaTarefaUI tabCategoriaTarefaUI;
    private TabTarefasUI tabTarefasUI;

    @FXML
    private TabPane tabPane;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuItem menuItemAbout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabsByUserRole();
    }

    public void loadTabsByUserRole() {
        try {
            unloadAllTabs();
            String role = Plataforma.getInstance().getUsersAPI().getRole();
            switch (Plataforma.getInstance().getUsersAPI().getRole()) {
                case Constantes.ROLE_ADMINISTRADOR:
                    loadTab(Constantes.TAB_MANAGE_AREAATIVIDADE_FILE, Constantes.TAB_MANAGE_AREAATIVIDADE_TITLE);
                    loadTab(Constantes.TAB_MANAGE_COMPTECNICA_FILE, Constantes.TAB_MANAGE_COMPTECNICA_TITLE);
                    loadTab(Constantes.TAB_MANAGE_CATTAREFA_FILE, Constantes.TAB_MANAGE_CATTAREFA_TITLE);
                    break;
                case Constantes.ROLE_GESTOR_ORGANIZACAO:
                    loadTab(Constantes.TAB_MANAGE_COLABORADOR_FILE, Constantes.TAB_MANAGE_COLABORADOR_TITLE);
                    loadTab(Constantes.TAB_MANAGE_TAREFAS_FILE, Constantes.TAB_MANAGE_TAREFAS_TITLE);
                    break;
                case Constantes.ROLE_COLABORADOR_ORGANIZACAO:
                    loadTab(Constantes.TAB_MANAGE_TAREFAS_FILE, Constantes.TAB_MANAGE_TAREFAS_TITLE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTab(String TAB_FILE, String TAB_TITLE) {
        try {
            Tab tab = new Tab();
            tab.setText(TAB_TITLE);
            tabPane.getTabs().add(tab);
            //Load tab    
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(TAB_FILE));
            tab.setContent(loader.load());

            switch (TAB_FILE) {
                case (Constantes.TAB_MANAGE_COMPTECNICA_FILE):
                    TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI = loader.getController();
                    tabCompetenciaTecnicaUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_AREAATIVIDADE_FILE):
                    TabAreasAtividadeUI tabAreasAtividadeUI = loader.getController();
                    tabAreasAtividadeUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_COLABORADOR_FILE):
                    TabColaboradoresSceneUI tabColaboradoresSceneUI = loader.getController();
                    tabColaboradoresSceneUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_CATTAREFA_FILE):
                    TabCategoriaTarefaUI tabCategoriaTarefaUI = loader.getController();
                    tabCategoriaTarefaUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_TAREFAS_FILE):
                    TabTarefasUI tabTarefasUI = loader.getController();
                    tabTarefasUI.associarParentUI(this);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void unloadAllTabs() {
        try {
            tabPane.getTabs().removeAll(tabPane.getTabs());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuItemLogoutAction(ActionEvent event) {
        MainSceneController mSC = new MainSceneController();

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Confirmação da ação.", "Deseja mesmo efectuar logout?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        }

        if (mSC.logout()) {
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO, "Logout bem sucedido", "").showAndWait();
            Stage newLogin = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.LOGIN_FILE));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");

                newLogin.setTitle(Constantes.LOGIN_TITLE);
                newLogin.setScene(scene);

                newLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                                "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                            event.consume();
                        }
                    }
                });

                Window window = tabPane.getScene().getWindow();
                window.hide();

                mSC.restartUAPI();
                newLogin.show();
            } catch (IOException ex) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO,
                        "Problemas no arranque da aplicação.", ex.getMessage()).show();
            }
        }

    }

    @FXML
    private void menuItemAboutAction(ActionEvent event) {
    }

    void associarParentUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }
}
