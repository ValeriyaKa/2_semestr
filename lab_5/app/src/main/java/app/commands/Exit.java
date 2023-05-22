package app.commands;

import app.utils.Output;

public class Exit extends ACommand {
    public Exit() {
        super("exit", "завершить программу ");
    }

    @Override
    public boolean execute(String argument) {
        try {
            Output.println("Завершение программы...");
            System.exit(0);
        } catch (Exception e) {
            Output.println("Ошибка при завершении программы: " + e.getMessage());
        }
        return false;
    }


}
