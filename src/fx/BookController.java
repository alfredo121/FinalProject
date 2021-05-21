package fx;

import data.Book;
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
import static utils.FileUtils.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable
{
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private TableView<Book> tblBook;
    @FXML
    private TableColumn colTitle;
    @FXML
    private TableColumn colAuthor;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colNPages;
    @FXML
    private TableColumn colYear;
    @FXML
    private TableColumn colEditorial;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtNPages;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtYearOfPublication;

    private ObservableList<Book> books;

    private ObservableList<Book> filterBooks;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        filterBooks = FXCollections.observableArrayList();
        this.colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        this.colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.colNPages.setCellValueFactory(new PropertyValueFactory("NPages"));
        this.colEditorial.setCellValueFactory(new PropertyValueFactory("editorial"));
        this.colYear.setCellValueFactory(new PropertyValueFactory("yearOfPublication"));
        setImageButtons();
        books = readBook();
        this.tblBook.setItems(books);
    }

    public void select(MouseEvent mouseEvent)
    {
        Book book = this.tblBook.getSelectionModel().getSelectedItem();

        if(book != null)
        {
            this.txtTitle.setText(book.getTitle());
            this.txtAuthor.setText(book.getAuthor());
            this.txtPrice.setText(String.valueOf(book.getPrice()));
            this.txtNPages.setText(String.valueOf(book.getNPages()));
            this.txtEditorial.setText(book.getEditorial());
            this.txtYearOfPublication.setText(String.valueOf(book.getYearOfPublication()));
        }
    }

    public void addBook(ActionEvent actionEvent)
    {
        try {
            String title = this.txtTitle.getText();
            String author = this.txtAuthor.getText();
            double price = Double.parseDouble(this.txtPrice.getText());
            int nPages = Integer.parseInt(this.txtNPages.getText());
            String editorial = this.txtEditorial.getText();
            int yearOfPublication = Integer.parseInt(this.txtYearOfPublication.getText());

            Book newBook = new Book(title, author, price, nPages,editorial,yearOfPublication);

            if (!this.books.contains(newBook)) {
                this.books.add(newBook);
                this.tblBook.setItems(books);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Successfully added");
                alert.showAndWait();
                saveBooks(books);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("The book is already in the list");
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

    public void deleteBook(ActionEvent actionEvent)
    {
        Book book = this.tblBook.getSelectionModel().getSelectedItem();

        if(book == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a book");
            alert.showAndWait();
        }else {
            this.books.remove(book);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Successfully deleted");
            alert.showAndWait();
            saveBooks(books);
            this.tblBook.refresh();
        }
    }

    public void modifyBook(ActionEvent actionEvent)
    {
        Book book = this.tblBook.getSelectionModel().getSelectedItem();

        if(book == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Select a book");
            alert.showAndWait();
        }else
        {
            try {
                String title = this.txtTitle.getText();
                String author = this.txtAuthor.getText();
                double price = Double.parseDouble(this.txtPrice.getText());
                int nPages = Integer.parseInt(this.txtNPages.getText());
                String editorial = this.txtEditorial.getText();
                int yearOfPublication = Integer.parseInt(this.txtYearOfPublication.getText());

                Book aux = new Book(title, author, price, nPages,editorial,yearOfPublication);

                if (!this.books.contains(aux)) {
                    book.setTitle(aux.getTitle());
                    book.setAuthor(aux.getAuthor());
                    book.setPrice(aux.getPrice());
                    book.setNPages(aux.getNPages());
                    book.setEditorial(aux.getEditorial());
                    book.setYearOfPublication(aux.getYearOfPublication());

                    saveBooks(books);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Successfully modified");
                    alert.showAndWait();

                    this.tblBook.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("The book is already in the list");
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

    public void search(ActionEvent actionEvent)
    {
        String txt = this.txtTitle.getText();
        ObservableList<Book> bookSearch = FXCollections.observableArrayList();
        boolean ok = false;

        if(txt.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Title is empty");
            alert.showAndWait();
        }else {
            for (Book b:books) {
                if(b.getTitle().toLowerCase().contains(txt.toLowerCase())) {
                    bookSearch.add(b);
                    this.tblBook.setItems(bookSearch);
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

    public void placeButton()
    {
        URL newLink = getClass().getResource("/img/LUPA.png");

        Image newImage = new Image(newLink.toString(),20,20,false,true);

        btnSearch.setGraphic(new ImageView(newImage));
    }

    public void refresh(ActionEvent actionEvent) {
        this.txtTitle.setText("");
        this.txtAuthor.setText("");
        this.txtPrice.setText("");
        this.txtEditorial.setText("");
        this.txtNPages.setText("");
        this.txtYearOfPublication.setText("");
        this.tblBook.refresh();
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

    /*public void filterTitle(KeyEvent keyEvent)
    {
        //COGIENDO EL NOMBRE QUE QUEREMOS FILTRAR
        String filterBook = this.txtTitle.getText();

        //SI ESTA VACIO, HACEMOS UN SET CON EL ORIGINAL()
        //EN LIBROS SIEMPRE ESTA EL OBSERVABLE LIST ORIGINAL
        //SI FILTRAMOS COGEMOS EL OTRO OBSERVABLE LIST(FILTEREDBOOK)
        if(filterBook.isEmpty()) {
            this.filterBooks.clear();
            for (Book b:this.books) {
                //SI CONTIENE EL NOMBRE que esta en el filtro
                if(b.getTitle().toLowerCase().contains(filterBook.toLowerCase())) {
                    this.filterBooks.add(b);
                }
            }
            //entonces filtramos utilizando el de filtrado para que nos lo muestre en la list view
            this.tblBook.setItems(filterBooks);


        }else
        {
            this.tblBook.setItems(books);
        }
    }*/
