package app.utils;

import app.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final CollectionManager collectionManager;
    private final RouteCreator routeCreator;
    private final CSVFile csvFile;
    private String currentScript = null;
    private final Map<String, Command> commandMap;

    public CommandManager(CollectionManager collectionManager, RouteCreator routeCreator, CSVFile csvFile) {
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
        this.csvFile = csvFile;

        commandMap = new HashMap<>();
        commandMap.put("help", new Help(this));
        commandMap.put("exit", new Exit());
        commandMap.put("info", new Info(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("add", new Add(routeCreator, collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager, routeCreator));
        commandMap.put("remove_by_id", new RemoveById(collectionManager));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("save", new Save(collectionManager, csvFile));
        commandMap.put("execute_script", new ExecuteScript(collectionManager, this));
        commandMap.put("remove_greater", new RemoveGreater(collectionManager, routeCreator));
        commandMap.put("remove_lower", new RemoveLower(collectionManager, routeCreator));
        commandMap.put("count_by_distance", new CountByDistance(collectionManager));
        commandMap.put("count_greater_than_distance", new CountGreaterThanDistance(collectionManager));
        commandMap.put("add_if_min", new AddIfMin(collectionManager));
        commandMap.put("filter_starts_with_name", new FilterStartsWithName(collectionManager));
    }

    public boolean executeCommand(String commandName, String argument) {
        if (!commandMap.containsKey(commandName)) {
            Output.print("Команда '" + commandName + "' не найдена. ");
            return false;
        }
        Command command = commandMap.get(commandName);
        command.execute(argument);
        return true;
    }


    public String getCurrentScript() {
        return currentScript;
    }

    public void setCurrentScript(String currentScript) {
        this.currentScript = currentScript;
    }

    public void process(String line) {
        String[] tokens = line.trim().split("\\s+", 2);
        String commandName = tokens[0];
        String argument = null;
        if (tokens.length > 1) {
            argument = tokens[1];
        }
        Command command = commandMap.get(commandName);
        if (command == null) {
            Output.print("Команда '" + commandName + "' не найдена. ");
            return;
        }
        command.execute(argument);
    }

    public void helpCommand(String argument) {
        if (argument.isEmpty()) {
            Output.println("Список команд:");
            commandMap.keySet().forEach(Output::println);
        } else {
            Command command = commandMap.get(argument);
            if (command != null) {
                Output.println(command.getDescription());
            } else {
                Output.println("Команда '" + argument + "' не найдена.");
            }
        }
    }
}