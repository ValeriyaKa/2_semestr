package app;

import app.utils.RouteCreator;
import app.utils.CollectionManager;
import app.utils.CSVFile;
import app.utils.CommandManager;
import app.utils.Output;


import java.util.Scanner;

public class App {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        RouteCreator routeCreator = new RouteCreator();
        CollectionManager collectionManager = new CollectionManager();
        CSVFile csvFile = new CSVFile();
        collectionManager.loadCollection();
        CommandManager commandManager = new CommandManager(collectionManager, routeCreator, csvFile);


        while (true) {
            Output.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.trim().split("\\s+");
            String[] argument = new String[tokens.length - 1];
            if (tokens.length > 1) {
                System.arraycopy(tokens, 1, argument, 0, tokens.length - 1);
            }

            if (argument.length == 0) {
                argument = new String[]{""};
            }

            if (!commandManager.executeCommand(tokens[0], argument[0])) {
                Output.println("Неизвестная команда. Введите 'help' для получения списка доступных команд.");
            }
        }
    }
}