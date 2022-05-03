package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    // supplies of coffee machine
    static int CASH_IN_MACHINE = 550;
    static int WATER_IN_MACHINE = 400;
    static int MILK_IN_MACHINE = 540;
    static int BEANS_IN_MACHINE = 120;
    static int CUPS_IN_MACHINE = 9;

    // state of machine
    static boolean IS_RUNNING = true;

    public static void main(String[] args) {

        // run the machine until user wants to exit
        while (IS_RUNNING) {
            runMachine();
        }

    }

    private static void printMachineState() {
        /* gets the state from class variables and outputs it in formatted way */
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", WATER_IN_MACHINE);
        System.out.printf("%d ml of milk\n", MILK_IN_MACHINE);
        System.out.printf("%d g of coffee beans\n", BEANS_IN_MACHINE);
        System.out.printf("%d disposable cups\n", CUPS_IN_MACHINE);
        System.out.printf("$%d of money\n", CASH_IN_MACHINE);
        System.out.println();
    }

    private static void runMachine() {
        /* run machine and let user choose the action according to operation he needs to do */
        IS_RUNNING = true;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();
        System.out.println();

        switch (action.toLowerCase()) {
            case "buy":
                buyCoffee();
                break;
            case "fill":
                fillMachine();
                break;
            case "take":
                takeMoneyFromMachine();
                break;
            case "remaining":
                printMachineState();
                break;
            case "exit":
                IS_RUNNING = false;
                break;
            default:
                System.out.println("Unknown command");
                break;
        }

    }

    private static void buyCoffee() {
        /* Allows to purchase different flavors of coffee and changes state of coffee machine accordingly */

        // required resources for coffee of each type
        int espressoWater = 250;
        int espressoMilk = 0;
        int espressoBeans = 16;
        int espressoCost = 4;

        int latteWater = 350;
        int latteMilk = 75;
        int latteBeans = 20;
        int latteCost = 7;

        int cappuccinoWater = 200;
        int cappuccinoMilk = 100;
        int cappuccinoBeans = 12;
        int cappuccinoCost = 6;
        int choice;

        // ask user to choose coffee flavor
        while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                String input = scanner.next();
                if ("back".equals(input)) {
                    System.out.println("Unknown input");
                    return;
                }
                continue;
            }
            break;
        }


        // change state of coffee machine according to user coffee type choice
        switch (choice) {
            case 1:
                updateMachineState(espressoWater, espressoMilk, espressoBeans, espressoCost);
                break;
            case 2:
                updateMachineState(latteWater, latteMilk, latteBeans, latteCost);
                break;
            case 3:
                updateMachineState(cappuccinoWater, cappuccinoMilk, cappuccinoBeans, cappuccinoCost);
                break;
            default:
                System.out.println("No such coffee type");
                break;
        }
    }

    private static void fillMachine() {
        /* Asks user how many assets he wants to replenish and change the state of machine accordingly */
        int waterToAdd;
        int beansToAdd;
        int cupsToAdd;
        int milkToAdd;

        // get user input
        while (true) {
            System.out.println("Write how many ml of water you want to add:");
            if (scanner.hasNextInt()) {
                waterToAdd = scanner.nextInt();
            } else {
                System.out.println("You should enter an integer number.");
                scanner.next();
                continue;
            }
            break;
        }

        while (true) {

            System.out.println("Write how many ml of milk you want to add:");

            if (scanner.hasNextInt()) {
                milkToAdd = scanner.nextInt();
            } else {
                System.out.println("You should enter an integer number.");
                scanner.next();
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Write how many grams of coffee beans you want to add:");

            if (scanner.hasNextInt()) {
                beansToAdd = scanner.nextInt();
            } else {
                System.out.println("You should enter an integer number.");
                scanner.next();
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Write how many disposable cups of coffee you want to add:");

            if (scanner.hasNextInt()) {
                cupsToAdd = scanner.nextInt();
            } else {
                System.out.println("You should enter an integer number.");
                scanner.next();
                continue;
            }
            break;
        }

        System.out.println();

        // replenish the machine
        WATER_IN_MACHINE += waterToAdd;
        MILK_IN_MACHINE += milkToAdd;
        BEANS_IN_MACHINE += beansToAdd;
        CUPS_IN_MACHINE += cupsToAdd;
    }


    private static void takeMoneyFromMachine() {
        /* take all the money from machine and change the state of machine accordingly */
        // remove cash from machine
        System.out.printf("I gave you $%d\n", CASH_IN_MACHINE);
        System.out.println();
        CASH_IN_MACHINE = 0;
    }

    private static boolean hasSufficientResources(int water, int milk, int beans) {
        if (water > WATER_IN_MACHINE) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return false;
        } else if (milk > MILK_IN_MACHINE) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return false;
        } else if (beans > BEANS_IN_MACHINE) {
            System.out.println("Sorry, not enough beans!");
            System.out.println();
            return false;
        } else if (CUPS_IN_MACHINE == 0) {
            System.out.println("Sorry, not enough caps!");
            System.out.println();
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println();
            return true;
        }
    }

    private static void updateMachineState(int water, int milk, int beans, int cost) {
        if (hasSufficientResources(water, milk, beans)) {
            WATER_IN_MACHINE -= water;
            MILK_IN_MACHINE -= milk;
            BEANS_IN_MACHINE -= beans;
            CUPS_IN_MACHINE--;
            CASH_IN_MACHINE += cost;
        }
    }


}
