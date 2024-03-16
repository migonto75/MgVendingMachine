package de.mgonzales.main;

import de.rhistel.logic.ConsoleReader;

public class Main {

    //region Konstanten
    public static final String APP_NAME = "Mg VendingMachine";
    public static final String DRINK_SELECTION = "\nWählen sie ihr Getränk aus den folgenden Sorten:";
    public static final String HOW_MUCH_CASH_GIVEN = "\nWie viel Geld wirfst du zum bezahlen ein?";
    public static final String NOT_ENOUGH_PAID = "Nicht genügend bezahlt. Sie müssen noch %.2f € mehr bezahlen.";
    public static final String WRONG_INDEX_CHOOSEN = "Falscher Index. Bitte wählen sie erneut zwischen 0 und 3.";
    public static final String USER_INPUT = "\nIhre Eingabe:";
    public static final String USER_HAS_CHOOSEN = "\nSie haben die %s %s gewählt.\n";
    public static final String GET_MONEY_BACK = "\nIhr Wechselgeld beträgt %.2f €.\n";
    public static final String USER_PAID_FOR_PRODUCT = "Sie haben %.2f € für das Getränk %s bezahlt.\n";

    //endregion Konstanten

    //region Variablen
    public static String[] drinks = {"Cola", "Fanta", "Mezzo Mix", "Sprudel"};
    public static double productPrice = 2.0;
    //endregion Variablen

    //region Methoden
    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        printApplicationName();
        displayProduct(drinks);
        int userChoice = readUsersChoice(drinks);
        buyingProcess(userChoice, productPrice, drinks);
    }

    private static void printApplicationName() {
        System.out.println(APP_NAME);
    }

    private static void displayProduct(String[] drinks) {
        System.out.println(DRINK_SELECTION);
        for (int i = 0; i < drinks.length; i++) {
            System.out.println(i + ". " + drinks[i]);
        }
    }

    private static int readUsersChoice(String[] drinks) {
        int userChoice = -1;

        do {
            System.out.println(USER_INPUT);
            userChoice = ConsoleReader.in.readPositivInt();
            if (userChoice < 0 || userChoice >= drinks.length) {
                System.out.printf(USER_HAS_CHOOSEN, userChoice, drinks[userChoice]);
                System.out.printf(WRONG_INDEX_CHOOSEN);
            } else {
                System.out.printf(Main.USER_HAS_CHOOSEN, userChoice, drinks[userChoice]);
            }
        } while (userChoice < 0 || userChoice >= drinks.length);

        return userChoice;
    }

    private static double getUserMoney() {
        System.out.println(HOW_MUCH_CASH_GIVEN);
        double userChoice = ConsoleReader.in.readDouble();
        return userChoice;
    }

    public static boolean checkUserMoney(double money, double price) {
        return money >= price;
    }

    private static void buyingProcess(int userChoice, double productPrice, String[] drinks) {
        if (userChoice != -1) {
            double money = getUserMoney();
            if (checkUserMoney(money, productPrice)) {
                System.out.printf(USER_PAID_FOR_PRODUCT, money, drinks[userChoice]);
                if (money > productPrice) {
                    System.out.printf(GET_MONEY_BACK, (money - productPrice));
                }
            } else {
                System.out.printf(NOT_ENOUGH_PAID, (productPrice - money));
            }
        }
    }
    //endregion Methoden
}