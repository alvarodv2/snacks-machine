package service;

import domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation that manages snacks persistence using a text file.
 * This class handles reading and writing snack data to a file.
 */
public class SnacksFileService implements ISnacksServie {

    private final String FILE_NAME = "snacks.txt";
    private List<Snack> snacksList = new ArrayList<>();

    public SnacksFileService() {
        var file = new File(FILE_NAME);
        boolean exists = false;

        try {
            exists = file.exists();
            if (exists) {
                this.snacksList = getFileSnacks();
            } else {
                try (var output = new PrintWriter(new FileWriter(file))) {
                    System.out.println("Successfully file created: " + FILE_NAME);
                }
            }

        } catch (Exception e) {
            System.err.println("Error while creating the file... " + e.getMessage());
        }

        if (!exists)
            defaultSnacksLoader();

    }

    /**
     * Loads default snacks into the system when initializing with an empty file.
     */
    private void defaultSnacksLoader() {
        this.addSnack(new Snack("Chips", 2.70));
        this.addSnack(new Snack("Pepsi", 1.2));
        this.addSnack(new Snack("Sandwich", 2.3));
    }

    /**
     * Reads all snacks from the file and returns them as a list.
     *
     * @return List of snacks read from the file
     */
    public List<Snack> getFileSnacks() {
        var snacks = new ArrayList<Snack>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));

            for (String line : lines) {
                try {
                    String[] snackLine = line.split(",");
                    if (snackLine.length >= 3) {
                        var snackName = snackLine[1];
                        String priceStr = snackLine[2].trim()
                                .replace("€", "")
                                .replace(",", ".");
                        var snackPrice = Double.parseDouble(priceStr);
                        var snack = new Snack(snackName, snackPrice);
                        snacks.add(snack);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error parsing snack line: " + line + " - " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading the snacks file: " + e.getMessage());
        }
        return snacks;
    }

    @Override
    public void addSnack(Snack snack) {
        this.snacksList.add(snack);

        this.addSnackToFile(snack);
    }

    private void addSnackToFile(Snack snack) {
        boolean appendData = false;
        var file = new File(FILE_NAME);

        try {
            appendData = file.exists();
            var output = new PrintWriter(new FileWriter(file, appendData));
            output.println(snack.snackWriter());
            output.close();
        } catch (Exception e) {
            System.err.println("Error while add snack: " + e.getMessage());
        }

    }

    /**
     * Displays all available snacks to the console.
     */
    @Override
    public void showSnacks() {
        System.out.println("*** Available Snacks ***");
        StringBuilder snackInventory = new StringBuilder();
        for (var snack : this.snacksList) {
            snackInventory.append(snack.toString()).append("\n");
        }
        System.out.println(snackInventory);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacksList;
    }

    @Override
    public void deleteSnack(Snack snack) {
        if (this.snacksList.remove(snack)) {
            rewriteFile();
            System.out.println("Snack deleted successfully: " + snack.getName());
        } else {
            System.out.println("Snack not found: " + snack.getName());
        }
    }

    private void rewriteFile() {
        var file = new File(FILE_NAME);
        try (var output = new PrintWriter(new FileWriter(file))) {
            for (var snack : this.snacksList) {
                output.println(snack.snackWriter());
            }
        } catch (Exception e) {
            System.err.println("Error rewriting file: " + e.getMessage());
        }
    }

}
