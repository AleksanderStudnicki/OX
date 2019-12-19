package app.studnicki.ox;

import app.studnicki.ox.config.Config;
import app.studnicki.ox.ui.UserInterfaceFactory;
import app.studnicki.ox.ui.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        UserInterface ui = UserInterfaceFactory.Console();

        ui.welcome();
        Config.getInstance().changeLanguage("pl");

        new Scanner(System.in).nextLine();


        ui.welcome();
        Config.getInstance().changeLanguage("ja");

        new Scanner(System.in).nextLine();

        ui.welcome();

        new Scanner(System.in).nextLine();
    }
}
