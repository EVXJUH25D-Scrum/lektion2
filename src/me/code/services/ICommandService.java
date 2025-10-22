package me.code.services;

import me.code.commands.Command;

/**
 * Interface för hantering av kommandon i applikationen.
 *
 * Detta interface definierar hur vi registrerar och kör kommandon.
 * Genom att använda Command-mönstret kan vi enkelt lägga till nya kommandon
 * utan att ändra den här koden!
 */
public interface ICommandService {

    /**
     * Registrerar ett nytt kommando som användaren kan köra.
     *
     * När du registrerar ett kommando blir det tillgängligt för användaren
     * att köra i terminalen.
     *
     * @param command Kommandot som ska registreras
     */
    void registerCommand(Command command);

    /**
     * Kör ett kommando baserat på användarens input.
     *
     * Metoden letar igenom alla registrerade kommandon och kör det som
     * matchar inputen.
     *
     * @param commandInput Namnet på kommandot användaren vill köra
     */
    void executeCommand(String commandInput);

}
