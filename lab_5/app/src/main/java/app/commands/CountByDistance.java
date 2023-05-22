package app.commands;

import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;

public class CountByDistance extends ACommand {
    private CollectionManager collectionManager;

    public CountByDistance(CollectionManager collectionManager) {
        super("count_by_distance", "вывести количество элементов, значение поля distance которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {

        try {
            int count = 0;
            Float d = Float.parseFloat(argument);
            for (Route route : collectionManager.getAllRoutes()) {
                if (route.getDistance().equals(d)) {
                    count++;
                }
            }
            Output.println("Количество маршрутов с дистанцией " + count);
            return true;
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
            return false;
        }
    }
}

