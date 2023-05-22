package app.commands;

import app.utils.CollectionManager;
import app.utils.Output;

public class RemoveById extends ACommand {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;

    }

    @Override
    public boolean execute(String argument) {

        if (argument.isEmpty()) {
            Output.println("Не указан id. Попробуйте еще раз.");

        }
        try {
            Long id = Long.parseLong(argument);
            if (!collectionManager.getById(id)) {
                Output.println("Не найдена группа с данным id: " + id);

            }
            collectionManager.removeRoute(id);
            Output.println("Успешно удалено по id: " + argument);
            return true;

        } catch (NumberFormatException e) {
            Output.println("Некорректный формат id: " + argument);
        }
        return false;
    }
}
