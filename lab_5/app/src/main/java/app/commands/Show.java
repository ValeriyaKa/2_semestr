package app.commands;

import app.Exeption.InvalidArgumentException;
import app.utils.CollectionManager;
import app.utils.Output;

public class Show extends ACommand {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;

    }

    @Override
    public boolean execute(String argument) {
        try {

            if (!argument.isEmpty()) throw new InvalidArgumentException();
            collectionManager.printRouteList();
        } catch (InvalidArgumentException e) {
            Output.println("Неверное количество аргументов: ");
        }
        return false;
    }

}