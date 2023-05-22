package app.commands;

import app.Exeption.InvalidArgumentException;
import app.utils.CSVFile;
import app.utils.CollectionManager;
import app.utils.Output;


public class Save extends ACommand {
    private CollectionManager collectionManager;
    private CSVFile csvFile;

    public Save(CollectionManager collectionManager, CSVFile csvFile) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.csvFile = csvFile;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new InvalidArgumentException();
            collectionManager.saveCollectionToFile();

        } catch (InvalidArgumentException e) {
            Output.println("Неверное количество аргументов. ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Output.println("успешно сохранено");
        return false;
    }

}
