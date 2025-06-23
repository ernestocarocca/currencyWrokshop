package se.currency.workshop;



import se.currency.workshop.service.CurrencyService;
import se.currency.workshop.ui.MenuHandler;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {


        CurrencyService service = new CurrencyService();
        MenuHandler menu = new MenuHandler();
        // Menu
        menu.printMenu();
        menu.chooseCurrency(service);

    }

  }