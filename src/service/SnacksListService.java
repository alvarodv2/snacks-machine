package service;

import domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnacksListService implements ISnacksServie {
    private static final List<Snack> snacks;

    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public void showSnacks() {
        StringBuilder inventarioSnacks = new StringBuilder();
        for (var snack : snacks) {
            inventarioSnacks.append(snack.toString()).append("\n");
        }
        System.out.println("--- Snacks in Inventory ---");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    @Override
    public void deleteSnack(Snack snack) {
        if (snacks.remove(snack)) {
            System.out.println("Snack deleted successfully: " + snack.getName());
        } else {
            System.out.println("Snack not found: " + snack.getName());
        }
    }

}
