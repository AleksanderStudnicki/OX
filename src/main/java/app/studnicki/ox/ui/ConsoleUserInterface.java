package app.studnicki.ox.ui;

import app.studnicki.ox.config.Config;

import java.util.ResourceBundle;

class ConsoleUserInterface implements UserInterface {
    private ResourceBundle rb = Config.getInstance().getRb();

    @Override
    public void welcome() {
        rb = Config.getInstance().getRb();
        clearScreen();
        System.out.println(rb.getString("welcome"));
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
