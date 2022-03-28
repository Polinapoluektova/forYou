package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class userAppController {

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<?, ?> loginTimeColumn;

    @FXML
    private TableColumn<?, ?> logoutTimeColumn;

    @FXML
    private TableColumn<?, ?> timeSpentOnSystemColumn;

    @FXML
    private TableColumn<?, ?> unsuccesfulLogoutReasonColomn;
    @FXML
    void initialize() {
        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
        });
    }
}
