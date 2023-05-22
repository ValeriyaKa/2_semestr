package app.commands;

import app.Exeption.InvalidArgumentException;
import app.collectionClasses.Route;
import app.utils.CSVFile;
import app.utils.CollectionManager;
import app.utils.Output;
import app.utils.RouteCreator;

import java.util.Collections;

public class AddIfMin extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;
    private CSVFile csvFile;


    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new InvalidArgumentException();
            Route newRoute = new Route(collectionManager.generateNextId(), routeCreator.nameCreator(), routeCreator.coordinatesCreator(), routeCreator.getTime().getDate(), routeCreator.fromCreator(), routeCreator.toCreator(), routeCreator.distanceCreator());
            Route minRoute = Collections.min(collectionManager.getCollection());
            if (newRoute.compareTo(minRoute) == 1) {
                collectionManager.addRoute(newRoute);
            }

        } catch (InvalidArgumentException e) {
            Output.println("Неправильное количество аргументов!");
        } catch (NumberFormatException e) {
            Output.println("Неправильный формат числа");
        }
        return false;
    }

}
