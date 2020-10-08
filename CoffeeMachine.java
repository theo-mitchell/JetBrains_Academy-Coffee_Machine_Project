package machine;

import java.util.*;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int money = 550;
    static int water = 400;
    static int milk = 540;
    static int coffee = 120;
    static int cups = 9;

    enum Resources {
        ESPRESSO(250,0,16,4,1),
        LATTE(350,75,20,7,1),
        CAPPUCCINO(200,100,12,6,1);

        int waterNeeded;
        int milkNeeded;
        int coffeeNeeded;
        int moneyNeeded;
        int cupsNeeded;

        Resources(int waterNeeded, int milkNeeded, int coffeeNeeded, int moneyNeeded, int cupsNeeded){
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.coffeeNeeded = coffeeNeeded;
            this.moneyNeeded = moneyNeeded;
            this.cupsNeeded = cupsNeeded;
        }
    }

    enum State {
        TAKE,
        FILL,
        BUY,
        REMAINING;

        public void getStateInstruction() {

            switch(this) {
                case TAKE:
                    CoffeeMaker.take();
                    break;
                case FILL:
                    CoffeeMaker.fill();
                    break;
                case REMAINING:
                    CoffeeMaker.remaining();
                    break;
                case BUY:
                    CoffeeMaker.buy();
            }
        }

    }

    public static class CoffeeMaker {

        public static void getInput(String userInput){
            State.valueOf(userInput.toUpperCase()).getStateInstruction();
        }

        public static void take() {
            System.out.println("I gave you $" + money);
            money = 0;
        }

        public static void fill() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write how many ml of water do you want to add:");
            int water_add = scanner.nextInt();

            System.out.println("Write how many ml of milk do you want to add:");
            int milk_add= scanner.nextInt();

            System.out.println("Write how many grams of coffee beans do you want to add:");
            int coffee_add = scanner.nextInt();

            System.out.println("Write how many disposable cups of coffee do you want to add:");
            int cups_add = scanner.nextInt();

            water += water_add;
            milk += milk_add;
            coffee += coffee_add;
            cups += cups_add;
        }

        public static void remaining() {
            System.out.println("The coffee machine has:");
            System.out.println(water + " of water");
            System.out.println(milk + " of milk");
            System.out.println(coffee + " of coffee beans");
            System.out.println(cups + " of disposable cups");
            System.out.println(money + " of money");
        }

        public static void buy() {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String coffeeChoice = scanner.nextLine();

            switch(coffeeChoice) {
                case "1":
                    coffeeChoice = "ESPRESSO";
                    break;
                case "2":
                    coffeeChoice = "LATTE";
                    break;
                case "3":
                    coffeeChoice = "CAPPUCCINO";
                    break;
            }

            if (coffeeChoice.equals("back")){
                return;
            } else if (water - Resources.valueOf(coffeeChoice).waterNeeded < 0 || milk - Resources.valueOf(coffeeChoice).milkNeeded < 0 || coffee - Resources.valueOf(coffeeChoice).coffeeNeeded < 0 || cups - Resources.valueOf(coffeeChoice).cupsNeeded < 0){
                if (water - Resources.valueOf(coffeeChoice).waterNeeded < 0){
                    System.out.println("Sorry, not enough water!");
                }
                if (milk - Resources.valueOf(coffeeChoice).milkNeeded < 0){
                    System.out.println("Sorry, not enough milk!");
                }
                if (coffee - Resources.valueOf(coffeeChoice).coffeeNeeded < 0){
                    System.out.println("Sorry, not enough coffee!");
                }
                if (cups - Resources.valueOf(coffeeChoice).cupsNeeded < 0){
                    System.out.println("Sorry, not enough cups!");
                }
            }else{
                water -= Resources.valueOf(coffeeChoice).waterNeeded;
                milk -= Resources.valueOf(coffeeChoice).milkNeeded;
                coffee -= Resources.valueOf(coffeeChoice).coffeeNeeded;
                cups -= Resources.valueOf(coffeeChoice).cupsNeeded;
                money += Resources.valueOf(coffeeChoice).moneyNeeded;
            }
        }

    }

    public static void main(String[] args) {

        String userInput;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        userInput = scanner.nextLine();

        do {
            CoffeeMaker.getInput(userInput);
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            userInput = scanner.nextLine();
        } while (!userInput.equals("exit"));

    }
}