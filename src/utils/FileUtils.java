package utils;

import data.BoardGame;
import data.Book;
import data.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;

public class FileUtils
{
    /**
     * Method to save the information of the board games in txt
     * @param boardGames A ObservableList with the board game's information
     */
    public static void saveBoardGames(ObservableList<BoardGame> boardGames)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("boardGames.txt", false)));
            for (BoardGame b:boardGames
            ) {
                printWriter.println(b);
            }
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * Return the list of the board game's information
     * @return A ObservableList with the board game's information
     */
    public static ObservableList<BoardGame> readBoardGames()
    {
        ObservableList<BoardGame> myList = FXCollections.observableArrayList();

        if (! (new File("boardGames.txt")).exists() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("The file boardGames.txt not exist");
            alert.showAndWait();
            return myList;
        }

        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("boardGames.txt")));
            String line = inputFile.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                if(parts.length == 5)
                {
                    BoardGame b = new BoardGame();
                    b.setName(parts[0]);
                    b.setCompany(parts[1]);
                    b.setAverageAge(parts[2]);
                    b.setPlayers(parts[3]);
                    b.setPrice(Double.parseDouble(parts[4]));

                    myList.add(b);
                }
                line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }

        return myList;
    }

    /**
     * Method to save the book's information in txt
     * @param books A ObservableList with the book's information
     */
    public static void saveBooks(ObservableList<Book> books)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("books.txt", false)));
            for (Book b:books
            ) {
                printWriter.println(b);
            }
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * Return a list with the book's information
     * @return A ObservableList with the book's information
     */
    public static ObservableList<Book> readBook()
    {
        ObservableList<Book> myList = FXCollections.observableArrayList();

        if (! (new File("books.txt")).exists() ) {
            System.out.println("File books.txt not found");
            return myList;
        }

        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("books.txt")));
            String line = inputFile.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                if(parts.length == 6)
                {
                    Book b = new Book();
                    b.setTitle(parts[0]);
                    b.setAuthor(parts[1]);
                    b.setPrice(Double.parseDouble(parts[2]));
                    b.setNPages(Integer.parseInt(parts[3]));
                    b.setEditorial(parts[4]);
                    b.setYearOfPublication(Integer.parseInt(parts[5]));

                    myList.add(b);
                }
                line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }

        return myList;
    }

    /**
     * Method to save the game's information in txt
     * @param videoGames A ObservableList with the game's information
     */
    public static void saveGames(ObservableList<Game> videoGames)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("games.txt", false)));
            for (Game v:videoGames
            ) {
                printWriter.println(v);
            }
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * Return a list with the game's information
     * @return A ObservableList with the game's information
     */
    public static ObservableList<Game> readGames()
    {
        ObservableList<Game> myList = FXCollections.observableArrayList();

        if (! (new File("games.txt")).exists() ) {
            System.out.println("File games.txt not found");
            return myList;
        }

        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("games.txt")));
            String line = inputFile.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                if(parts.length == 5)
                {
                    Game g = new Game();
                    g.setName(parts[0]);
                    g.setCompany(parts[1]);
                    g.setHardDiskSpace(Double.parseDouble(parts[2]));
                    g.setPrice(Double.parseDouble(parts[3]));
                    g.setPlatform(parts[4]);

                    myList.add(g);
                }
                line = inputFile.readLine();
            }
            inputFile.close();
        }
        catch (IOException fileError) {
            System.out.println(
                    "There has been some problems: " +
                            fileError.getMessage() );
        }

        return myList;
    }
}
