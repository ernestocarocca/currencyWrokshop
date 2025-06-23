
package se.currency.workshop;

import se.currency.workshop.service.CurrencyService;
import se.currency.workshop.ui.MenuHandler;

import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        CurrencyService service = new CurrencyService();
        MenuHandler menu = new MenuHandler();
        Scanner scanner = new Scanner(System.in);

        String continueChoice = "";
        do {
            menu.printMenu();

            System.out.print("Ditt val (1-3): ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Fel: Ange ett heltal mellan 1 och 3.\n");
                continue;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Ogiltigt val, välj 1, 2 eller 3.\n");
                continue;
            }

            menu.chooseCurrency(service, choice, scanner);

            System.out.print("Vill du göra en ny konvertering? (j/n): ");
            continueChoice = scanner.nextLine().trim().toLowerCase();

        } while (continueChoice.equals("j"));

        System.out.println("Programmet avslutades.");
        scanner.close();
    }
}
