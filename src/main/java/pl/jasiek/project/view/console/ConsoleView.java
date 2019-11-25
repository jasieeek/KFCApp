package pl.jasiek.project.view.console;

import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.SemifinishedProducts.*;
import pl.jasiek.project.view.View;

import java.util.*;

public class ConsoleView implements View {
    private Scanner scanner;
    private Random random;
    private final int END_VEGETABLES = Vegetable.values().length + 1;


    public ConsoleView() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    @Override
    public String readString(String label) {
        System.out.println(label + ": ");
        return scanner.nextLine();
    }

    @Override
    public int readInt(String label) {
        System.out.println(label + ": ");
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    @Override
    public double readDouble(String label) {
        try {
            System.out.println(label + ": ");
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Wpisz liczbe w formacie x.xx");
            scanner.nextLine();
            return -1;
        }
    }

    @Override
    public boolean readBoolean(String label) {
        System.out.println(label + ": ");
        return scanner.nextBoolean();
    }

    @Override
    public Groups readGroups(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < Groups.values().length; i++) {
            System.out.println("[" + i + "] " + Groups.values()[i]);
        }

        int choose = readInt("Your choice: ");

        return Groups.values()[choose];

    }

    @Override
    public Map<Chicken, Integer> readContains(String label, int maxAmountIngredients) {
        System.out.println(label + ": ");
        Map<Chicken, Integer> tmpContains = new HashMap<>();
        int a = 0;
        while (a <= maxAmountIngredients ) {
            int choose = readInt("Choose ingredient:" +
                    "\n[0] HOTWINGS" +
                    "\n[1] STRIPS" +
                    "\n[2] BITES" +
                    "\n[3] KENTUCKY\n");
            Chicken tmpChicken = Chicken.values()[choose];
            int amount = readInt("Insert amount of ingredient");
            tmpContains.put(tmpChicken, amount);
            a++;
        }
        return tmpContains;
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    public Extension readAdditions(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < Extension.values().length; i++) {
            System.out.println("[" + i + "] " + Extension.values()[i]);
        }

        int choose = readInt("Your choice: ");

        return Extension.values()[choose];
    }


    public Sandwiches readSandwich(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < Sandwiches.values().length; i++) {
            System.out.println("\n[" + i + "] " + Sandwiches.values()[i]);
        }
        int choice = readInt("Your choice");

        return Sandwiches.values()[choice];
    }


    public Chicken readChicken(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < Chicken.values().length; i++) {
            System.out.println("\n[" + i + "] " + Chicken.values()[i]);
        }
        int choice = readInt("Your choice");

        return Chicken.values()[choice];
    }


    public List<Vegetable> readVegetable(String label) {
        System.out.println(label + ": ");
        List<Vegetable> vegetables = new LinkedList<>();
        for (int i = 0; i < Vegetable.values().length; i++) {
            System.out.println("\n[" + i + "] " + Vegetable.values()[i]);
        }
        System.out.println("[" + END_VEGETABLES + "] End");

        int choice;
        do {
            choice = readInt("Your choice");
            if (choice != END_VEGETABLES) {
                vegetables.add(Vegetable.values()[choice]);
            }
        } while (choice != END_VEGETABLES);

        return vegetables;
    }


    public Sauces readSauce(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < Sauces.values().length; i++) {
            System.out.println("\n[" + i + "] " + Sauces.values()[i]);
        }
        int choice = readInt("Your choice");

        return Sauces.values()[choice];
    }


    public TasteOfShake readTaste(String label) {
        System.out.println(label + ": ");
        for (int i = 0; i < TasteOfShake.values().length; i++) {
            System.out.println("\n[" + i + "] " + TasteOfShake.values()[i]);
        }
        int choice = readInt("Your choice");

        return TasteOfShake.values()[choice];
    }
}
