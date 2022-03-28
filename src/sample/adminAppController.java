package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class adminAppController {

    @FXML
    private Button addUserBtn;

    @FXML
    private TableColumn<?, ?> ageColumn;

    @FXML
    private MenuButton allUserManu;

    @FXML
    private Button changeRoleBtn;

    @FXML
    private Button disEnLoginBtn;

    @FXML
    private TableColumn<?, ?> emailAddressColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<?, ?> lastNameColumn;

    @FXML
    private TableColumn<?, ?> nameColoumn;

    @FXML
    private TableColumn<?, ?> offiseColumn;

    @FXML
    private TableColumn<?, ?> userRoleColumn;

    @FXML
    void initialize() {
        addUserBtn.setOnAction(event -> {
            addUserBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addUser.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        changeRoleBtn.setOnAction(event -> {
            changeRoleBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("changeRole.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
        });
    }
}
