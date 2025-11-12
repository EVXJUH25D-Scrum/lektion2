package me.code.commands;

import me.code.services.ITodoService;

public class CountTodosCommand extends  Command{

    public CountTodosCommand(ITodoService todoService) {
        super("count", "Count all todos", todoService);
    }

    @Override
    public void execute() {
        try {
            long count = todoService.getTodos().count();
            System.out.println("Todos: " + count);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
