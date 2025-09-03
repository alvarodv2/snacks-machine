package domain;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int snackCounter = 0;
    private int snackId;
    private String name;
    private double price;

    /**
     * Default constructor that assigns a new unique ID.
     */
    public Snack() {
        this.snackId = ++Snack.snackCounter;
    }

    /**
     * Constructor with name and price parameters.
     * @param name The name of the snack
     * @param price The price of the snack
     */
    public Snack(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public static int getSnackCounter() {return snackCounter;}
    public int getSnackId() {return snackId;}
    public String getName() {return name;}
    public double getPrice() {return price;}

    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return String.format("Snack{id=%d, name='%s', price=%.2f€}",
                snackId, name, price);
    }

    /**
     * Formats the snack data for file storage.
     * @return A string representation of the snack in CSV format
     */
    public String snackWriter() {
        return String.format("%d,%s,%.2f€", snackId, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return snackId == snack.snackId &&
                Double.compare(price, snack.price) == 0 &&
                Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snackId, name, price);
    }
}









