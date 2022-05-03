package machine;

import static machine.MachineState.*;

public class CoffeeMachine {

    int water;
    int milk;
    int beans;
    int cups;
    int cash;
    MachineState state;

    public CoffeeMachine(int water, int milk, int beans, int cups, int cash) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.cash = cash;
        this.state = MachineState.SHOW_MENU;
    }

    void showMainMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    void showCoffeeChoiceMenu() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    void printMachineState() {
        /* gets the state from class variables and outputs it in formatted way */
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.water);
        System.out.printf("%d ml of milk\n", this.milk);
        System.out.printf("%d g of coffee beans\n", this.beans);
        System.out.printf("%d disposable cups\n", this.cups);
        System.out.printf("$%d of money\n", this.cash);
        System.out.println();

        this.state = SHOW_MENU;
    }

    void runMachine(String input) {

        switch (input.toLowerCase()) {
            case "buy":
                this.state = BUY_COFFEE;
                break;
            case "fill":
                this.state = FILL_WATER;
                break;
            case "take":
                this.state = TAKE;
                break;
            case "remaining":
                this.state = REMAINING;
                break;
            case "exit":
                this.state = STOPPED;
                break;
            default:
                System.out.println("Unknown command");
                break;
        }

    }

    void buyCoffee(String input) {
        /* Allows to purchase different flavors of coffee and changes state of coffee machine accordingly */
        Coffee espresso = new Coffee(250, 0, 14, 4);
        Coffee latte = new Coffee(350, 75, 20, 7);
        Coffee cappuccino = new Coffee(200, 100, 12, 6);


        // change state of coffee machine according to user coffee type choice
        switch (input.toLowerCase()) {
            case "1":
                updateMachineState(espresso.water, espresso.milk, espresso.beans, espresso.cost);
                break;
            case "2":
                updateMachineState(latte.water, latte.milk, latte.beans, latte.cost);
                break;
            case "3":
                updateMachineState(cappuccino.water, cappuccino.milk, cappuccino.beans, cappuccino.cost);
                break;
            case "back":
                break;
            default:
                System.out.println("No such coffee type");
                break;
        }

        this.state = MachineState.SHOW_MENU;
    }

    void addWater(int input) {
        this.water += input;
        this.state = FILL_MILK;
    }

    void addMilk(int input) {
        this.milk += input;
        this.state = FILL_BEANS;
    }

    void addBeans(int input) {
        this.beans += input;
        this.state = FILL_CUPS;
    }

    void addCups(int input) {
        this.cups += input;
        this.state = SHOW_MENU;
    }

    void takeMoneyFromMachine() {
        /* take all the money from machine and change the state of machine accordingly */
        // remove cash from machine
        System.out.printf("I gave you $%d\n", this.cash);
        System.out.println();
        this.cash = 0;
        this.state = SHOW_MENU;
    }

    private boolean hasSufficientResources(int water, int milk, int beans) {
        if (water > this.water) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return false;
        } else if (milk > this.milk) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return false;
        } else if (beans > this.beans) {
            System.out.println("Sorry, not enough beans!");
            System.out.println();
            return false;
        } else if (this.cups == 0) {
            System.out.println("Sorry, not enough caps!");
            System.out.println();
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println();
            return true;
        }
    }

    private void updateMachineState(int water, int milk, int beans, int cost) {
        if (hasSufficientResources(water, milk, beans)) {
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.cups--;
            this.cash += cost;
        }
    }
}

enum MachineState {
    SHOW_MENU, BUY_COFFEE, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS, TAKE, REMAINING, STOPPED,
}
