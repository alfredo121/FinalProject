package data;

import java.util.Objects;

/**
 * Class to define a Book with their attributes
 */
public class Book
{

    private String title;
    private String author;
    private double price;
    private int nPages;
    private String editorial;
    private int yearOfPublication;

    /**
     * Constructor with parameters
     * @param title A String with the book's title
     * @param author A String with the book's author's name
     * @param price A Double with the book's price
     * @param nPages A Integer with the book's number of pages
     * @param editorial A String with the book's editorial's name
     * @param yearOfPublication A Integer with the book's year of publication
     * @return Returns a book with their specified attributes
     */
    public Book(String title, String author, double price, int nPages, String editorial, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.nPages = nPages;
        this.editorial = editorial;
        this.yearOfPublication = yearOfPublication;
    }

    public Book(){}

    /**
     * Return the book's title
     * @return Book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establishes the book's title
     * @param title A String with the book's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the book's author's name
     * @return A String with the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Establishes the author's name
     * @param author A String with the author's name
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Return the book's price
     * @return A Integer with the book's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establishes the book's price
     * @param price A Integer with the book's price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Return the book's number of pages
     * @return A Integer with the number of pages
     */
    public int getNPages() {
        return nPages;
    }

    /**
     * Establishes the book's number of pages
     * @param nPages A Integer with the number of pages
     */
    public void setNPages(int nPages) {
        this.nPages = nPages;
    }

    /**
     * Return the book's editorial's name
     * @return A String with editorial's name
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establishes the book's editorial
     * @param editorial A String with the editorial's name
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Return the book's year of publication
     * @return A Integer with the year of publication
     */
    public int getYearOfPublication() {
        return yearOfPublication;
    }

    /**
     * Establishes the book's year of publication
     * @param yearOfPublication A Integer with the year of publication
     */
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    /**
     * Method to compare if 2 books are the same
     * @param o A Object to compare with the selected object
     * @return True if the object and the parameter object are the same and false if not..
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book musicDisc = (Book) o;
        return Double.compare(musicDisc.price, price) == 0 && nPages == musicDisc.nPages && yearOfPublication == musicDisc.yearOfPublication && Objects.equals(title, musicDisc.title) && Objects.equals(author, musicDisc.author) && Objects.equals(editorial, musicDisc.editorial);
    }

    /**
     * Return a String with the book's information
     * @return A String with the book's information
     */
    public String toString()
    {
        return title + ";" + author + ";" + price + ";" + nPages + ";" + editorial + ";" + yearOfPublication;
    }
}
