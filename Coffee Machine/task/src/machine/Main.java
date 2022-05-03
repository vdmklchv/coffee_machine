package machine;

import java.util.Scanner;

import static machine.MachineState.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        String input;
        while (machine.state != STOPPED) {
            switch (machine.state) {
                case SHOW_MENU:
                    machine.showMainMenu();
                    input = scanner.nextLine();
                    machine.runMachine(input);
                    break;
                case BUY_COFFEE:
                    machine.showCoffeeChoiceMenu();
                    input = scanner.nextLine();
                    machine.buyCoffee(input);
                    break;
                case FILL_WATER:
                    int waterToAdd = getIntegerInput("water");
                    machine.addWater(waterToAdd);
                    break;
                case FILL_MILK:
                    int milkToAdd = getIntegerInput("milk");
                    machine.addMilk(milkToAdd);
                    break;
                case FILL_BEANS:
                    int beansToAdd = getIntegerInput("beans");
                    machine.addBeans(beansToAdd);
                case FILL_CUPS:
                    int cupsToAdd = getIntegerInput("cups");
                    machine.addCups(cupsToAdd);
                    break;
                case TAKE:
                    machine.takeMoneyFromMachine();
                    break;
                case REMAINING:
                    machine.printMachineState();
                    break;
                default:
                    System.out.println("Unknown state");
                    break;
            }
        }
    }

    private static int getIntegerInput(String ingredient) {
        int number;
        while (true) {
            if ("water".equals(ingredient) || "milk".equals(ingredient)) {
                System.out.printf("Write how many ml of %s you want to add:\n", ingredient);
            } else if ("beans".equals(ingredient)) {
                System.out.println("Write how many grams of coffee beans you want to add:");
            } else if ("cups".equals(ingredient)) {
                System.out.println("Write how many disposable cups of coffee you want to add:");
            }

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
            } else {
                System.out.println("You should enter an integer number.");
                scanner.next();
                continue;
            }
            break;
        }
        return number;
    }
}
