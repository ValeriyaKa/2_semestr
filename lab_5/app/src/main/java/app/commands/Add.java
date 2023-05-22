package app.commands;

import app.Exeption.WrongAmountOfElements;
import app.collectionClasses.Route;
import app.utils.CollectionManager;
import app.utils.Output;
import app.utils.RouteCreator;

public class Add extends ACommand {
    private RouteCreator routeCreator;
    private CollectionManager collectionManager;

    public Add(RouteCreator routeCreator, CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.routeCreator = routeCreator;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElements();
            Output.println("* Создание нового продукта:");
            collectionManager.addRoute(new Route(collectionManager.generateNextId(), routeCreator.nameCreator(), routeCreator.coordinatesCreator(), routeCreator.getTime().getDate(), routeCreator.fromCreator(), routeCreator.toCreator(), routeCreator.distanceCreator()));
            Output.println("Продукт успешно добавлен!");
        } catch (WrongAmountOfElements exception) {
            Output.println("Неправильное количество аргументов!");
        }
        return false;
    }

}
