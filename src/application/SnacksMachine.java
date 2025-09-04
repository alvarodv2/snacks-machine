package application;

import domain.Snack;
import service.ISnacksServie;
import service.SnacksFileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksMachine {

    public static void main(String[] args) {
        runSnackMachine();
    }

    public static void runSnackMachine() {
        var exit = false;
        var scanner = new Scanner(System.in);

        ISnacksServie snacksService = new SnacksFileService();

        List<Snack> products = new ArrayList<>();
        System.out.println("*** Snack Machine ***");
        snacksService.showSnacks();
        while (!exit) {
            try {
                var option = showMenu(scanner);
                exit = executeOption(option, scanner, products, snacksService);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner scanner) {
        System.out.print("""
                Menu:
                1. Buy snack
                2. Show receipt
                3. Add new snack
                4. Delete snack
                5. Exit
                Choose an option:\s""");
        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean executeOption(int option, Scanner scanner,
                                         List<Snack> products, ISnacksServie snacksService) {
        var exit = false;
        switch (option) {
            case 1 -> buySnack(scanner, products, snacksService);
            case 2 -> showReceipt(products);
            case 3 -> addSnack(scanner, snacksService);
            case 4 -> deleteSnack(scanner, snacksService);
            case 5 -> {
                System.out.println("Come back soon!");
                exit = true;
            }
            default -> System.out.println("Invalid option: " + option);
        }
        return exit;
    }

    private static void buySnack(Scanner scanner,
                                 List<Snack> products, ISnacksServie snacksService) {
        System.out.print("Which snack do you want to buy (id)? ");
        var snackId = Integer.parseInt(scanner.nextLine());
        var snackFound = false;
        for (var snack : snacksService.getSnacks()) {
            if (snackId == snack.getSnackId()) {
                products.add(snack);
                System.out.println("Ok, Snack added: " + snack);
                snackFound = true;
                break;
            }
        }
        if (!snackFound) {
            System.out.println("Snack id not found: " + snackId);
        }
    }

    private static void showReceipt(List<Snack> products) {
        var receipt = "*** Sales Receipt ***";
        var total = 0.0;
        for (var product : products) {
            receipt += "\n\t- " + product.getName() + " - " + String.format("%.2f€", product.getPrice());
            total += product.getPrice();
        }
        receipt += "\n\tTotal -> " + String.format("%.2f€", total);
        System.out.println(receipt);
    }

    private static void addSnack(Scanner scanner, ISnacksServie snacksService) {
        System.out.print("Snack name: ");
        var name = scanner.nextLine();
        System.out.print("Snack price: ");
        var price = Double.parseDouble(scanner.nextLine());
        snacksService.addSnack(new Snack(name, price));
        System.out.println("Your snack has been added successfully");
        snacksService.showSnacks();
    }

    private static void deleteSnack(Scanner scanner, ISnacksServie snacksService) {
        System.out.print("Snack Id: ");
        var snackId = Integer.parseInt(scanner.nextLine());
        Snack snackToDelete = null;

        for (var snack : snacksService.getSnacks()) {
            if (snack.getSnackId() == snackId) {
                snackToDelete = snack;
                break;
            }
        }

        if (snackToDelete != null) {
            snacksService.deleteSnack(snackToDelete);
        } else {
            System.out.println("Snack id not found: " + snackId);
        }
    }


}
