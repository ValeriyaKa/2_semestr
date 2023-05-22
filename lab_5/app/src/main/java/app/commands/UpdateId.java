package app.commands;

import app.Exeption.RouteNotFound;
import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;
import app.utils.RouteCreator;

import java.util.Scanner;

public class UpdateId extends ACommand {
    Scanner scanner = new Scanner(System.in);
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public UpdateId(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("update_id  ", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public boolean execute(String argument) {
        try {
            long id = Long.parseLong(scanner.nextLine().trim());
            for (Route route : collectionManager.getCollection()) {
                if (route.getId() == id) throw new RouteNotFound();
                collectionManager.removeRoute(route.getId());
                collectionManager.addRoute(new Route(collectionManager.generateNextId(), routeCreator.nameCreator(), routeCreator.coordinatesCreator(), routeCreator.getTime().getDate(), routeCreator.fromCreator(), routeCreator.toCreator(), routeCreator.distanceCreator()));
            }
            Output.println("Элемент с id " + id + " успешно обновлен.");
            return true;
        } catch (NumberFormatException e) {
            Output.println("Некорректный формат аргумента. Ожидалось целое число.");
        } catch (RouteNotFound ex) {
            Output.println("Элемент с таким id не найден.");
        }
        return false;
    }
}
