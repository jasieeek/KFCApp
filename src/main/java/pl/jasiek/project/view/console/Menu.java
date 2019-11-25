package pl.jasiek.project.view.console;

import pl.jasiek.project.controller.Command;
import pl.jasiek.project.view.View;

import java.util.List;

public class Menu {
    private View view;
    private static final String MY_FORMAT = "[%d] %s";

    public Menu(View view) {
        this.view = view;
    }

    public void show(List<Command> commandList) {
        System.out.println("\nMenu:\n");
        for (Command command : commandList) {
            String message = String.format(MY_FORMAT, commandList.indexOf(command), command.getLabel());
            view.info(message);
        }
    }

    public Command getChoice(List<Command> commandList) {
        int choice = view.readInt("Choose");
        return commandList.get(choice);
    }
}
