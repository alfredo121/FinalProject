package data;

import java.util.Objects;

/**
 * Class to define a Game with theid attributes
 */
public class Game
{
    private String name;
    private String company;
    private double hardDiskSpace;
    private double price;
    private String platform;

    /**
     * Constructor with parameters
     * @param name A String with the game's name
     * @param company A String with the game's company
     * @param hardDiskSpace A Double with the game's hard disk space
     * @param price A Double with the game's price
     * @param platform A String with the game's platform
     */

    public Game(String name, String company, double hardDiskSpace, double price, String platform) {
        this.name = name;
        this.company = company;
        this.hardDiskSpace = hardDiskSpace;
        this.price = price;
        this.platform = platform;
    }

    /**
     * Return the game's name
     * @return A String with the game's name
     */
    public String getName() {
        return name;
    }

    /**
     * Establishes the game's name
     * @param name A String with the game's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the game's platform name
     * @return A String with the platform's name
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Establishes the game's platform name
     * @param platform A String with the platform's name
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Constructor without parameters
     */
    public Game(){}

    /**
     * Return the game's company's name
     * @return A String with the company's name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Establishes the game's company's name
     * @param company A String with the company's name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Return the game's hard disk space
     * @return A Double with the hard disk space
     */
    public double getHardDiskSpace() {
        return hardDiskSpace;
    }

    /**
     * Establishes the game's hard disk space
     * @param hardDiskSpace A Double with the hard disk space
     */
    public void setHardDiskSpace(double hardDiskSpace) {
        this.hardDiskSpace = hardDiskSpace;
    }

    /**
     * Return the game's price
     * @return A Double with the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establishes the game's price
     * @param price A Double with the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Return the game's information
     * @return A String with the game's information
     */
    public String toString()
    {
        return name + ";" + company + ";" + hardDiskSpace + ";" + price + ";" + platform;
    }

    /**
     * Method to compare if 2 games are the same
     * @param o A Object to compare with the selected object
     * @return True if the object and the parameter object are the same and false if not..
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.hardDiskSpace, hardDiskSpace) == 0 && Double.compare(game.price, price) == 0 && Objects.equals(name, game.name) && Objects.equals(company, game.company) && Objects.equals(platform, game.platform);
    }
}
