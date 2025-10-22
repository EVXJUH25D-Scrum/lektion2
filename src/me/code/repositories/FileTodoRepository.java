package me.code.repositories;

import me.code.models.Todo;
import me.code.models.TodoStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation av ITodoRepository som lagrar todos i textfiler.
 *
 * Varje todo sparas i sin egen fil med filnamn = UUID.txt
 * Detta är en enkel lösning för persistent lagring utan databas!
 *
 * Fördel: Inga externa dependencies (ingen databas behövs)
 * Nackdel: Inte optimalt för stora mängder data
 *
 * Observera att denna klass implementerar ITodoRepository, vilket betyder att
 * vi följer kontraktet som interfaces definierar.
 */
public class FileTodoRepository implements ITodoRepository {

    // Alla todo-filer slutar med .txt
    private static final String EXTENSION = ".txt";

    /**
     * Läser en todo från fil baserat på dess ID.
     *
     * Vi använder try-with-resources (try med parenteser) som automatiskt
     * stänger filen åt oss - mycket smidigare än att använda finally!
     *
     * Filformatet är enkelt: en rad per fält (id, title, category, status, priority, deadline)
     *
     * @param todoId ID för todon vi vill läsa
     * @return Todo-objektet som lästes från filen
     * @throws Exception Om filen inte finns eller är korrupt
     */
    @Override
    public Todo findById(UUID todoId) throws Exception {
        String fileName = getFileName(todoId);

        // try-with-resources: stänger automatiskt reader när vi är klara
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Hoppa över första raden (ID, vi har det redan)
            String title = reader.readLine();
            String category = reader.readLine();
            String statusString = reader.readLine();
            String priorityString = reader.readLine();
            String deadlineString = reader.readLine();

            // Konvertera strängarna till rätt datatyper
            TodoStatus status = TodoStatus.valueOf(statusString);  // String -> Enum
            int priority = Integer.parseInt(priorityString);       // String -> int
            long deadlineTime = Long.parseLong(deadlineString);    // String -> long
            Date deadline = new Date(deadlineTime);                 // long -> Date

            // Skapa och returnera todo-objektet med befintligt ID
            return new Todo(todoId, title, deadline, category, priority, status);
        }
    }

    /**
     * Hittar alla todos genom att skanna igenom alla .txt-filer i projektmappen.
     *
     * Vi kollar varje fil och om filnamnet är ett giltigt UUID och slutar på .txt
     * så läser vi in den som en todo!
     *
     * @return Lista med alla todos som hittades
     * @throws Exception Om något går fel vid läsning
     */
    @Override
    public List<Todo> findAll() throws Exception {
        ArrayList<Todo> todos = new ArrayList<>();

        // Hämta alla filer i nuvarande mapp (./)
        File directory = new File("./");
        File[] todoFiles = directory.listFiles();
        if (todoFiles == null) {
            return todos;  // Returnera tom lista om mappen inte finns
        }

        // Gå igenom varje fil
        for (File todoFile : todoFiles) {
            String name = todoFile.getName();

            // Hoppa över filer som inte slutar med .txt
            if (!name.endsWith(EXTENSION)) {
                continue;
            }

            // Ta bort .txt från filnamnet för att få UUID:t
            String fileName = name.substring(0, name.length() - EXTENSION.length());

            // Försök konvertera filnamnet till ett UUID
            UUID todoId;
            try {
                todoId = UUID.fromString(fileName);
            } catch (IllegalArgumentException ignored) {
                // Om filnamnet inte är ett giltigt UUID, hoppa över filen
                continue;
            }

            // Läs in todon och lägg till i listan
            Todo todo = findById(todoId);
            todos.add(todo);
        }

        return todos;
    }

    /**
     * Sparar en todo till fil.
     *
     * Om filen redan finns skrivs den över (vilket uppdaterar todon).
     * Om filen inte finns skapas en ny fil.
     *
     * Vi konverterar allt till strängar och skriver en rad per fält!
     *
     * @param todo Todon som ska sparas
     * @throws Exception Om något går fel vid skrivning
     */
    @Override
    public void save(Todo todo) throws Exception {
        String fileName = getFileName(todo.getId());

        // try-with-resources för att automatiskt stänga filen
        try (BufferedWriter stream = new BufferedWriter(new FileWriter(fileName))) {
            // Konvertera datum och prioritet till strängar
            String priority = todo.getPriority() + "";        // int -> String
            String deadline = todo.getDeadline().getTime() + "";  // Date -> long -> String

            // Skriv all data rad för rad
            stream
                    .append(todo.getId().toString())
                    .append("\n")
                    .append(todo.getTitle())
                    .append("\n")
                    .append(todo.getCategory())
                    .append("\n")
                    .append(todo.getStatus().toString())
                    .append("\n")
                    .append(priority)
                    .append("\n")
                    .append(deadline);
        }
    }

    /**
     * Raderar en todo genom att ta bort dess fil.
     *
     * @param todoId ID för todon som ska raderas
     * @throws Exception Om något går fel (men inte om filen inte fanns)
     */
    @Override
    public void delete(UUID todoId) throws Exception {
        String fileName = getFileName(todoId);

        File file = new File(fileName);
        boolean ignored = file.delete();  // true om filen raderades, false om den inte fanns
        // Vi bryr oss inte om returvärdet just nu, därav namnet 'ignored'
    }

    /**
     * Hjälpmetod som konverterar ett UUID till ett filnamn.
     *
     * Exempel: UUID "123e4567-..." blir "123e4567-....txt"
     *
     * Vi gör detta 'private static' eftersom det bara är en intern hjälpmetod
     * som inte behöver tillgång till klassens fält.
     *
     * @param todoId UUID att konvertera
     * @return Filnamn med .txt-ändelse
     */
    private static String getFileName(UUID todoId) {
        return todoId.toString() + EXTENSION;
    }
}
