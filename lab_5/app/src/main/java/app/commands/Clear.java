package app.commands;

import app.Exeption.InvalidArgumentException;
import app.utils.CollectionManager;
import app.utils.Output;

public class Clear extends ACommand {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;

    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new InvalidArgumentException();
            collectionManager.clear();
            Output.println("очищенно");
        } catch (InvalidArgumentException e) {
            Output.println("Неверное количество аргументов.");
        }
        return false;
    }

}
