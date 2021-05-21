package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class to choose the form we want
 */
public class ManagementController implements Initializable
{

    @FXML
    private Button btnBoardGameManagement;
    @FXML
    private Button btnBookManagement;
    @FXML
    private Button btnGamesManagement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Button to open the game management
     * @param actionEvent A ActionEvent, when the button is pressed activates the action
     */

    public void openGameManagement(ActionEvent actionEvent)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            GameController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Button to open the book management
     * @param actionEvent A ActionEvent, when the button is pressed activates the action
     */

    public void openBookManagement(ActionEvent actionEvent)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Book.fxml"));
            Parent root = loader.load();
            BookController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Button to open the game management
     * @param actionEvent A ActionEvent, when the button is pressed activates the action
     */
    public void openBoardGameManagement(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardGame.fxml"));
            Parent root = loader.load();
            BoardGameController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
