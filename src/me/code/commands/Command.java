package me.code.commands;

import me.code.services.ITodoService;

/**
 * Abstrakt basklass för alla kommandon i applikationen.
 *
 * Detta är hjärtat i Command-mönstret! Istället för att ha en massa if-satser
 * skapar vi en klass för varje kommando som kan köras.
 *
 * 'abstract' betyder att du inte kan skapa instanser av Command direkt -
 * du måste skapa en subklass (som CreateTodoCommand) som implementerar execute().
 *
 * Alla kommandon delar samma struktur: name, description och en execute-metod.
 */
public abstract class Command {

    // 'protected' betyder att subklasser kan komma åt dessa fält
    protected final String name;           // Namnet på kommandot (t.ex. "create-todo")
    protected final String description;    // Beskrivning av vad kommandot gör
    protected final ITodoService todoService;  // Service för att hantera todos

    /**
     * Konstruktor som alla subklasser måste använda.
     *
     * Vi injicerar todoService här så att alla kommandon kan använda samma
     * service-instans. Detta kallas "Dependency Injection" och gör koden
     * lättare att testa och underhålla!
     *
     * @param name Namnet på kommandot
     * @param description Beskrivning av kommandot
     * @param todoService Service för todo-operationer
     */
    public Command(String name, String description, ITodoService todoService) {
        this.name = name;
        this.description = description;
        this.todoService = todoService;
    }

    /**
     * Abstrakt metod som alla subklasser MÅSTE implementera.
     *
     * Här lägger du logiken för vad kommandot ska göra när det körs.
     * Varje kommando (CreateTodoCommand, DeleteTodoCommand, etc.) har sin egen
     * implementation av denna metod.
     */
    public abstract void execute();

    /**
     * Hämtar kommandots namn.
     */
    public String getName() {
        return name;
    }

    /**
     * Hämtar kommandots beskrivning.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returnerar en snygg textrepresentation av kommandot.
     * Används när vi visar alla tillgängliga kommandon för användaren.
     */
    @Override
    public String toString() {
        return name + " - " + description;
    }
}
