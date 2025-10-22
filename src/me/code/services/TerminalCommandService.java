package me.code.services;

import me.code.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service som hanterar kommandon via terminalen.
 *
 * Detta är användargränssnittet för vår applikation! Den visar en meny,
 * läser in användarens input och kör rätt kommando.
 *
 * Vi använder Command-mönstret vilket gör det lätt att lägga till nya
 * kommandon - du registrerar bara ett nytt Command-objekt!
 */
public class TerminalCommandService implements ICommandService {

    // Lista med alla registrerade kommandon
    private final List<Command> commands = new ArrayList<>();

    /**
     * Startar applikationen och visar huvudmenyn.
     *
     * Detta är huvudloopen för applikationen! Den:
     * 1. Visar välkomstmeddelande och alla tillgängliga kommandon
     * 2. Loopar tills användaren skriver "exit"
     * 3. Kör kommandon baserat på användarens input
     *
     * Vi fångar exceptions här så att ett fel i ett kommando inte kraschar
     * hela applikationen - användaren kan fortsätta använda programmet!
     */
    public void start() {
        System.out.println("=== TODO APPLICATION ===");
        System.out.println("Welcome! Choose from the following commands:");

        // Visa alla registrerade kommandon (använder Command.toString())
        for (Command command : commands) {
            System.out.println(command);
        }

        System.out.println("exit - Exit the application");

        // Skapa en Scanner för att läsa användarinput
        Scanner scanner = new Scanner(System.in);

        // Huvudloopen - körs tills användaren skriver "exit"
        while (true) {
            System.out.print("Enter command: ");
            String commandInput = scanner.nextLine();

            // Kolla om användaren vill avsluta
            if (commandInput.equalsIgnoreCase("exit")) {
                return;  // Avsluta loopen och därmed programmet
            }

            // Försök köra kommandot
            try {
                executeCommand(commandInput);
            } catch (Exception exception) {
                // Om något går fel, visa felet men fortsätt köra programmet
                exception.printStackTrace();
            }
        }
    }

    /**
     * Registrerar ett nytt kommando som blir tillgängligt för användaren.
     *
     * Du kan registrera hur många kommandon du vill - de läggs bara till i listan!
     *
     * @param command Kommandot att registrera
     */
    @Override
    public void registerCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Kör ett kommando baserat på användarens input.
     *
     * Vi loopar igenom alla registrerade kommandon och kollar om namnet matchar.
     * När vi hittar rätt kommando kör vi det och returnerar.
     * Om inget kommando matchar visar vi ett felmeddelande.
     *
     * Sökningen är case-insensitive (du kan skriva "LIST-TODOS" eller "list-todos").
     *
     * @param commandInput Namnet på kommandot användaren vill köra
     */
    @Override
    public void executeCommand(String commandInput) {
        // Sök efter kommandot i listan
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(commandInput)) {
                command.execute();  // Kör kommandot!
                return;              // Avsluta metoden när vi hittat och kört kommandot
            }
        }

        // Om vi kommer hit hittades inget matchande kommando
        System.out.println("The command does not exist, try again!");
    }
}
