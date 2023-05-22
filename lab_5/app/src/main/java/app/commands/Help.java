package app.commands;

import app.Exeption.WrongAmountOfElements;
import app.utils.CommandManager;
import app.utils.Output;

public class Help extends ACommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", " вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElements();
            commandManager.helpCommand(argument);

        } catch (WrongAmountOfElements exception) {
            Output.println(" использование: " + getName() + "");
        }
        return false;
    }
}