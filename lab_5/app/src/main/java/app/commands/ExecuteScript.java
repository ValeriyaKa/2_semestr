package app.commands;

import app.Exeption.WrongAmountOfElements;
import app.utils.CollectionManager;
import app.utils.CommandManager;
import app.utils.Output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScript extends ACommand {
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private Set<String> scriptSet;


    public ExecuteScript(CollectionManager collectionManager, CommandManager commandManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла.");
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        scriptSet = new HashSet<>();

    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                throw new WrongAmountOfElements();
            }


            File scriptFile = new File(argument);

            if (!scriptFile.exists()) {
                Output.println("Файл со скриптом не найден!");
            }

            if (!scriptFile.isFile() || scriptFile.isDirectory()) {
                Output.println("Неверный формат файла со скриптом!");
            }

            if (scriptSet.contains(scriptFile.getAbsolutePath())) {
                Output.println("Скрипт вызывает бесконечную рекурсию!");
            }


            // проверяем, что скрипт не считывает сам себя

            if (scriptFile.getCanonicalPath().equals(new File(commandManager.getCurrentScript()).getCanonicalPath())) {
                Output.println("Скрипт не может вызывать сам себя!");
            }

            scriptSet.add(scriptFile.getAbsolutePath());

            BufferedReader reader = new BufferedReader(new FileReader(scriptFile));
            String line;
            while ((line = reader.readLine()) != null) {
                commandManager.process(line);
            }
            commandManager.setCurrentScript(null);
            scriptSet.remove(scriptFile.getAbsolutePath());

        } catch (WrongAmountOfElements e) {
            Output.println("использование: " + getName() + " <file_name>");

        } catch (IOException e) {
            Output.println("Ошибка при чтении файла со скриптом!");
        } catch (Exception e) {
            Output.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
        return false;
    }

}
