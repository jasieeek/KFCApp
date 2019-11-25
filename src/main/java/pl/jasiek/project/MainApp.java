package pl.jasiek.project;

import pl.jasiek.project.config.Config;
import pl.jasiek.project.controller.Command;
import pl.jasiek.project.view.console.Menu;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        Config config = new Config();
        config.initializeProducts();
        config.initializeOrdersCsv();
        List<Command> commandList = config.initializeCommands();
        Menu menu = config.initializeMenu();

        while (true) {
            menu.show(commandList);
            Command command = menu.getChoice(commandList);
            command.execute();
        }
    }
}
