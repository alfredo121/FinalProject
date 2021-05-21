package data;

import java.util.Objects;

/**
 * Class to define board games and their attributes
 */
public class BoardGame
{

    private String name;
    private String company;
    private String averageAge;
    /*Players are String cause un can introduce 2-8 players or 3-9 players. We assume the user will put correctly*/
    private String players;
    private double price;

    /**
     * Constructor with parameters
     * @param name A string with the board game's name
     * @param company A string with the board game's company
     * @param averageAge A string with the board game's average age
     * @param players A int with the number of players
     * @param price A double with the price of the board game
     * @return It will return a Board game with the specified attributes
     */
    public BoardGame(String name, String company, String averageAge, String players, double price) {
        this.name = name;
        this.company = company;
        this.averageAge = averageAge;
        this.players = players;
        this.price = price;
    }

    public BoardGame(){}

    /**
     * Method to compare if 2 board games are the same
     * @param o A object to compare with the object i have it selected.
     * @return True if the object and the parameter object are the same and false if not..
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return players == boardGame.players && Double.compare(boardGame.price, price) == 0 && Objects.equals(name, boardGame.name) && Objects.equals(company, boardGame.company) && Objects.equals(averageAge, boardGame.averageAge);
    }

    /**
     *Return the boardGame's name
     * @return BoardGame's name
     */
    public String getName() {
        return name;
    }

    /**
     *Establishes the board game's name
     * @param name Board game's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return board game's company name
     * @return Board game's company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Establishes the board game's company
     * @param company Board game's company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Return board game's average age
     * @return Board game's average age
     */
    public String getAverageAge() {
        return averageAge;
    }

    /**
     * Establishes the board game's average age
     * @param averageAge Average age
     */
    public void setAverageAge(String averageAge) {
        this.averageAge = averageAge;
    }

    /**
     * Return board game's players
     * @return Board game's players
     */
    public String getPlayers() {
        return players;
    }

    /**
     * Establishes the number of players
     * @param players Number of players
     */
    public void setPlayers(String players) {
        this.players = players;
    }

    /**
     * Return board game's price
     * @return Board game's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establishes the price of board game
     * @param price Board game's price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a text string made up of the attributes
     * @return Return a string with the atributes of board game
     */
    public String toString()
    {
        return name + ";" + company + ";" + averageAge + ";" + players + ";" + price;
    }
}
