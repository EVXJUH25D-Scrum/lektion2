package me.code;

import me.code.commands.*;
import me.code.models.Todo;
import me.code.repositories.FileTodoRepository;
import me.code.services.ICommandService;
import me.code.services.ITodoService;
import me.code.services.TerminalCommandService;
import me.code.services.DefaultTodoService;

import java.util.ArrayList;

public class Main {

    /*

    1. Skapa todos (som sparas)
    2. Lista todos
    3. Avklara todos
    4. Radera todos
    5. Prioritera todos
    6. Uppdatera/redigera todos
    7. Kategorisera todos
    8. Statusmarkera todos (ej påbörjad, in progress, avslutad)

     */

    public static void main(String[] args) {
        ICommandService commandService = new TerminalCommandService();
        ITodoService todoService = new DefaultTodoService(
                new FileTodoRepository()
        );

        commandService.registerCommand(new ListTodosCommand(todoService));
        commandService.registerCommand(new CreateTodoCommand(todoService));
        commandService.registerCommand(new SearchTodosCommand(todoService));
        commandService.registerCommand(new CompleteTodoCommand(todoService));
        commandService.registerCommand(new DeleteTodoCommand(todoService));
        commandService.registerCommand(new StartTodoCommand(todoService));

        if (commandService instanceof TerminalCommandService service) {
            service.start();
        }
    }
}
