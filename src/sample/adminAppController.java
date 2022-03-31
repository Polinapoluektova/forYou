package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class adminAppController extends config{
    Connection dbconnection;
    public Connection getDbConnection()                                                    //метод подключения к базе данных (записывать В
            throws ClassNotFoundException, SQLException {                                  // нее, что-то считывать С нее)
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbconnection;
    }

    @FXML
    private Button addUserBtn;

    @FXML
    private MenuButton allUserManu;

    @FXML
    private Button changeRoleBtn;

    @FXML
    private Button disEnLoginBtn;

    @FXML
    private TableView<Flyers> flyersTable;

    @FXML
    private TableColumn<Flyers, String> emailAddressColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<Flyers, String> lastNameColumn;

    @FXML
    private TableColumn<Flyers, String> nameColoumn;

    @FXML
    private TableColumn<Flyers, String> officeColumn;

    @FXML
    private TableColumn<Flyers, String> userRoleColumn;

    private ObservableList<Flyers> bookData = FXCollections.observableArrayList();

    private DbConnection dc;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColoumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userrole"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        officeColumn.setCellValueFactory(new PropertyValueFactory<>("office"));

        dc=new DbConnection();
        try {
            loadDataFromDataBase();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }


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

    private void loadDataFromDataBase() throws SQLException, ClassNotFoundException {
        Connection conn = dc.Connect();
        ResultSet resSet = null;
        String select = "SELECT * FROM " +constants.FLYERS_TABLE + " WHERE " +
                constants.FLYERS_FIRSTNAME + "=? AND " + constants.FLYERS_LASTNAME + "=? AND " +
                constants.FLYERS_LOGIN + "=? AND " + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, String.valueOf(Flyers.getName()));
            prst.setString(2, String.valueOf(Flyers.getLastName()));
            prst.setString(3, String.valueOf(Flyers.getUserRole()));
            /*prst.setString(4, String.valueOf(Flyers.getEmail()));
            prst.setString(5, String.valueOf(Flyers.getOffice()));*/
            resSet = prst.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM a4.users");

        while (rs.next()){
            String name = rs.getString(1);
            String lastname = rs.getString(2);
            String userrole = rs.getString(3);
            String email = rs.getString(4);
            String  office= rs.getString(5);

            bookData.add(new Flyers(name, lastname, userrole, email, office));
        } // заполняем таблицу данными
        flyersTable.setItems(bookData);
    }
}
