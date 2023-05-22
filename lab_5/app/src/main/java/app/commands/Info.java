package app.commands;

import app.Exeption.InvalidArgumentException;
import app.utils.CollectionManager;
import app.utils.Output;

public class Info extends ACommand {
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции ");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument != null && !argument.isEmpty()) {
                throw new InvalidArgumentException();
            }
            Output.println("Размер коллекции: " + collectionManager.getSize());
            Output.println("Тип коллекции: " + collectionManager.getTypeOfCollection());

        } catch (InvalidArgumentException e) {
            Output.println(e.getMessage());
        } catch (NullPointerException e) {
            Output.println("Ошибка: " + e.getMessage());
        }
        return false;
    }

}
