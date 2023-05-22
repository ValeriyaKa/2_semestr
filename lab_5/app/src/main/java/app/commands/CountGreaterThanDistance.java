package app.commands;

import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;

public class CountGreaterThanDistance extends ACommand {
    private CollectionManager collectionManager;

    public CountGreaterThanDistance(CollectionManager collectionManager) {
        super("count_greater_than_distance distance ", "вывести количество элементов, значение поля distance которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            int count = 0;
            Float d = Float.parseFloat(argument);
            for (Route route : collectionManager.getAllRoutes()) {
                if (route.getDistance() > d) {
                    count++;
                }
            }
            Output.println("Количество маршрутов с дистанцией " + +count);
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
        }
        return false;
    }
}
