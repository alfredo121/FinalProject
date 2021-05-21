package fx;

import data.BoardGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.FileUtils.*;

public class BoardGameController implements Initializable
{
    @FXML
    private Button btnRefresh;
    @FXML
    private ComboBox cmbAges;
    @FXML
    private TableColumn colAverageAge;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private TableView<BoardGame> tblBoardGame;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colCompany;
    @FXML
    private TableColumn colPlayers;
    @FXML
    private TableColumn colPrice;
    @FXML
    private Label txtPlayer;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtPlayers;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtGameInstructions;

    private ObservableList<BoardGame> boardGames;

    private ObservableList<BoardGame> boardGamesFiltered;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> myList = FXCollections.observableArrayList("All ages","+ 3","+ 5","+ 7","+ 8", "+ 12", "+ 16");

        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.colCompany.setCellValueFactory(new PropertyValueFactory("company"));
        this.colAverageAge.setCellValueFactory(new PropertyValueFactory("averageAge"));
        this.colPlayers.setCellValueFactory(new PropertyValueFactory("players"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.cmbAges.setItems(myList);
        this.cmbAges.setPromptText("Select age...");
        setImageButtons();
        boardGames = readBoardGames();
        this.tblBoardGame.setItems(boardGames);
    }

    public void search(ActionEvent actionEvent) {
        String txt = this.txtName.getText();
        ObservableList<BoardGame> boardGameSearch = FXCollections.observableArrayList();
        boolean ok = false;
        if(txt.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Title is empty");
            alert.showAndWait();
        }else {
            for (BoardGame b:boardGameSearch) {
                if(b.getName().toLowerCase().contains(txt.toLowerCase())) {
                    boardGameSearch.add(b);
                    this.tblBoardGame.setItems(boardGameSearch);
                    ok = true;
                }
            }
        }

        if(!ok)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Title not found");
            alert.showAndWait();
        }
    }

    public void addBoardGame(ActionEvent actionEvent) {
        try {
            String name = this.txtName.getText();
            String company = this.txtCompany.getText();
            String averageAge = this.cmbAges.getValue().toString();
            String players = this.txtPlayers.getText();
            double price = Double.parseDouble(this.txtPrice.getText());

            BoardGame boardGame = new BoardGame(name,company,averageAge,players,price);

            if (!this.boardGames.contains(boardGame)) {
                this.boardGames.add(boardGame);
                this.tblBoardGame.setItems(boardGames);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Successfully");
                alert.setContentText("Successfully added");
                alert.showAndWait();
                saveBoardGames(boardGames);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("The board game is already in the list");
                alert.showAndWait();
            }
        }catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("");
            alert.showAndWait();
        }
    }

    public void deleteBoardGame(ActionEvent actionEvent) {
        BoardGame boardGame = this.tblBoardGame.getSelectionModel().getSelectedItem();

        if(boardGame == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a board game");
            alert.showAndWait();
        }else {
            this.boardGames.remove(boardGame);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Successfully deleted");
            alert.showAndWait();
            saveBoardGames(boardGames);
            this.tblBoardGame.refresh();
        }
    }

    public void modifyBoardGame(ActionEvent actionEvent) {
        BoardGame boardGame = this.tblBoardGame.getSelectionModel().getSelectedItem();

        if(boardGame == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a board game");
            alert.showAndWait();
        }else
        {
            try {
                String name = this.txtName.getText();
                String company = this.txtCompany.getText();
                String averageAge = this.cmbAges.getValue().toString();
                String players = this.txtPlayers.getText();
                double price = Double.parseDouble(this.txtPrice.getText());

                BoardGame aux = new BoardGame(name, company,averageAge,players,price);

                if (!this.boardGames.contains(aux)) {
                    boardGame.setName(aux.getName());
                    boardGame.setCompany(aux.getCompany());
                    boardGame.setAverageAge(aux.getAverageAge());
                    boardGame.setPlayers(aux.getPlayers());
                    boardGame.setPrice(aux.getPrice());

                    saveBoardGames(boardGames);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Successfully modified");
                    alert.showAndWait();

                    this.tblBoardGame.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("The board game is already in the list");
                    alert.showAndWait();
                }
            }catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Incorrect Format");
                alert.showAndWait();
            }
        }
    }

    public void select(MouseEvent mouseEvent) {
        BoardGame boardGame = this.tblBoardGame.getSelectionModel().getSelectedItem();

        if(boardGame != null)
        {
            this.txtName.setText(boardGame.getName());
            this.txtCompany.setText(boardGame.getCompany());
            this.cmbAges.setValue(boardGame.getAverageAge());
            this.txtPlayers.setText(String.valueOf(boardGame.getPlayers()));
            this.txtPrice.setText(String.valueOf(boardGame.getPrice()));
        }
    }


    public void refresh(ActionEvent actionEvent) {
            this.txtName.setText("");
            this.txtCompany.setText("");
            this.cmbAges.setValue(null);
            this.cmbAges.setPromptText("Select age...");
            this.txtPlayers.setText("");
            this.txtPrice.setText("");

            this.tblBoardGame.refresh();
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

