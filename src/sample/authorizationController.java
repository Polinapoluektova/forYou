package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class authorizationController {
    @FXML
    private Button loginBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField userNameTF;

    @FXML
    private Label getError;

    @FXML
    void initialize() {
        loginBtn.setOnAction(event -> {
            String loginText = userNameTF.getText().trim();
            String loginPassword = passwordTF.getText().trim();


            if (!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                getError.setText("Не все поля заполнены");
            }

        });
        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
        });
    }
    public void loginUser(String loginText, String loginPassword) throws SQLException, IOException {  // принимает вводимые пользователем данные
        dataBaseHandler dbHandler = new dataBaseHandler();//  и ищет их в бд через цикл

        Users user = new Users();
        Users.setUserName(loginText);
        Users.setPassword(loginPassword);
        ResultSet result = dbHandler.getUsers(user);
        int counter=0;
        try {
            while (result.next()){
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        String role = "";
        if(loginText.equals("j.doe@amonic.com") && (loginPassword.equals("123"))){
            role = "Администратор";
        } else {
            role = "Пользователь";
        }
        if ((counter>=1) && (role.equals("Администратор"))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminApp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        if ((counter>=1) && (role.equals("Пользователь"))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userApp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        if (counter==0) {
            getError.setText("Неверные данные, либо такого пользователя нет в системе");
        }
        else {
            getError.setText("Успешно");
        }
    }
}
