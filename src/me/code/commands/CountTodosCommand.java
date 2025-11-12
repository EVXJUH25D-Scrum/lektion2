package me.code.commands;

import me.code.services.ITodoService;

@CommandInfo(order = 7)
public class CountTodosCommand extends Command {

    public CountTodosCommand(ITodoService todoService) {
        super("count", "Count all todos", todoService);
    }

    @Override
    public void execute() {
        try {
            long count = todoService.getTodos().count();
            System.out.println("Todos: " + count);
        } catch (Exception exception) {
            System.out.println("Something went wrong, try again later!");
            exception.printStackTrace();
        }
    }
}