package app.commands;

import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;

public class FilterStartsWithName extends ACommand {
    private CollectionManager collectionManager;

    public FilterStartsWithName(CollectionManager collectionManager) {
        super("filter_starts_with_name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            int count = 0;
            for (Route route : collectionManager.getCollection()) {
                if (route.getName().startsWith(argument)) {
                    count++;
                }
            }
            Output.println("Количество маршрутов, значение поля name которых начинается с заданной подстроки: " + count);
            for (Route route : collectionManager.getCollection()) {
                if (route.getName().startsWith(argument)) {
                    collectionManager.printRoute(route);
                }
            }
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
        }
        return false;
    }
}
