package fx;
import data.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.security.PrivilegedAction;
import java.util.ResourceBundle;

import static utils.FileUtils.*;


public class GameController implements Initializable
{
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnSearch;
    @FXML
    private TableColumn colPlatform;
    @FXML
    private ComboBox cmbPlatform;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddGame;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtHardDiskSpace;
    @FXML
    private TextField txtPrice;
    @FXML
    private TableView<Game> tblGame;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colCompany;
    @FXML
    private TableColumn colHardDiskSpace;
    @FXML
    private TableColumn colPrice;

    private ObservableList<Game> games;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> myList = FXCollections.observableArrayList("PS3","PS4","PS5","NINTENDO SWITCH","NINTENDO WII","PC");

        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.colCompany.setCellValueFactory(new PropertyValueFactory("company"));
        this.colHardDiskSpace.setCellValueFactory(new PropertyValueFactory("hardDiskSpace"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.colPlatform.setCellValueFactory(new PropertyValueFactory("platform"));
        this.cmbPlatform.setItems(myList);
        this.cmbPlatform.setPromptText("Choose a platform...");
        setImageButtons();
        games = readGames();
        this.tblGame.setItems(games);
    }


    public void addGame(ActionEvent actionEvent) {
        try {
            String name = this.txtName.getText();
            String company = this.txtCompany.getText();
            double hardDiskSpace = Double.parseDouble(this.txtHardDiskSpace.getText());
            double price = Double.parseDouble(this.txtPrice.getText());
            String platform = this.cmbPlatform.getValue().toString();

            Game newGame = new Game(name, company, hardDiskSpace, price,platform);

            if (!this.games.contains(newGame)) {
                this.games.add(newGame);
                this.tblGame.setItems(games);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Successfully added");
                alert.showAndWait();
                saveGames(games);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("The game is already in the list");
                alert.showAndWait();
            }
        }catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Incorrect Format");
            alert.showAndWait();
        }
    }

    public void deleteGame(ActionEvent actionEvent)
    {
        Game game = this.tblGame.getSelectionModel().getSelectedItem();

        if(game == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a person");
            alert.showAndWait();
        }else
        {
            this.games.remove(game);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Successfully deleted");
            alert.showAndWait();
            saveGames(games);
            this.tblGame.refresh();
        }
    }

    public void modifyGame(ActionEvent actionEvent)
    {
        Game game = this.tblGame.getSelectionModel().getSelectedItem();

        if(game == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a person");
            alert.showAndWait();
        }else
        {
            try {
                String name = this.txtName.getText();
                String company = this.txtCompany.getText();
                double hardDiskSpace = Double.parseDouble(this.txtHardDiskSpace.getText());
                double price = Double.parseDouble(this.txtPrice.getText());
                String platform = this.cmbPlatform.getValue().toString();

                Game aux = new Game(name, company, hardDiskSpace, price,platform);

                if (!this.games.contains(aux)) {

                    game.setName(aux.getName());
                    game.setCompany(aux.getCompany());
                    game.setHardDiskSpace(aux.getHardDiskSpace());
                    game.setPrice(aux.getPrice());
                    game.setPlatform(aux.getPlatform().toString());
                    saveGames(games);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Successfully");
                    alert.setContentText("Successfully modified");
                    alert.showAndWait();

                    this.tblGame.refresh();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("The game is already in the list");
                    alert.showAndWait();
                }
            }catch(NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Incorrect Format");
                alert.showAndWait();
            }
        }

    }

    public void select(MouseEvent mouseEvent)
    {
        Game game = this.tblGame.getSelectionModel().getSelectedItem();

        if(game != null)
        {
            this.txtName.setText(game.getName());
            this.txtCompany.setText(game.getCompany());
            this.txtHardDiskSpace.setText(String.valueOf(game.getHardDiskSpace()));
            this.txtPrice.setText(String.valueOf(game.getPrice()));
            this.cmbPlatform.setValue(game.getPlatform());
        }
    }

    public void search(ActionEvent actionEvent)
    {
        String txt = this.txtName.getText();
        ObservableList<Game> gameSearch = FXCollections.observableArrayList();
        boolean ok = false;

        if(txt.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Name is empty");
            alert.showAndWait();
        }else {
            for (Game b:games) {
                if(b.getName().toLowerCase().contains(txt.toLowerCase())) {
                    gameSearch.add(b);
                    this.tblGame.setItems(gameSearch);
                    ok = true;
                }
            }
        }

        if(!ok)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Name not found");
            alert.showAndWait();
        }
    }

    public void refresh(ActionEvent actionEvent) {
        this.txtName.setText("");
        this.txtCompany.setText("");
        this.cmbPlatform.setValue(null);
        this.txtPrice.setText("");
        this.txtHardDiskSpace.setText("");

        this.tblGame.refresh();
    }

    private void setImageButtons()
    {
        URL urlSearch = getClass().getResource("/img/LUPA3.png");
        URL urlRefresh = getClass().getResource("/img/reload.png");

        Image imgSearch = new Image(urlSearch.toString(),24,24,false,true);
        Image imgRefresh = new Image(urlRefresh.toString(),24,24,false,true);

        btnSearch.setGraphic(new ImageView(imgSearch));
        btnRefresh.setGraphic(new ImageView(imgRefresh));
    }
}
