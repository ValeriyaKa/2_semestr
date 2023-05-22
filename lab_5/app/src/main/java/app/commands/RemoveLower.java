package app.commands;

import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;
import app.utils.RouteCreator;

public class RemoveLower extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public RemoveLower(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("remove_lower", " удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public boolean execute(String argument) {
        try {
            Route newRoute = new Route(collectionManager.generateNextId(), routeCreator.nameCreator(), routeCreator.coordinatesCreator(), routeCreator.getTime().getDate(), routeCreator.fromCreator(), routeCreator.toCreator(), routeCreator.distanceCreator());
            for (Route route : collectionManager.getCollection())

                if (route.compareTo(newRoute) == -1) {
                    collectionManager.removeRoute(route.getId());
                }
            Output.println("Элементы успешно удалены");
        } catch (Exception e) {
            Output.println("Ошибка при выполнении команды: " + e.getMessage());
        }
        return false;
    }
}
