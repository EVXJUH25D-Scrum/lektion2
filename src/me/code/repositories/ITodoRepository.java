package me.code.repositories;

import me.code.models.Todo;

import java.util.List;
import java.util.UUID;

/**
 * Interface som definierar kontraktet för hur vi hanterar todo-data.
 *
 * Ett interface är som ett kontrakt som säger "dessa metoder MÅSTE finnas".
 * Vi använder interface för att kunna byta ut hur vi lagrar data (fil, databas, minne)
 * utan att ändra resten av koden. Detta kallas "Dependency Inversion" och är
 * en viktig del av SOLID-principerna!
 *
 * Repository-mönstret används för att separera databaslogik från affärslogik.
 * Tänk på ett repository som ett lager mellan applikationen och där datan finns.
 */
public interface ITodoRepository {

    /**
     * Hittar en todo baserat på dess ID.
     *
     * @param todoId UUID för todon vi söker efter
     * @return Den hittade todon, eller exception om den inte finns
     * @throws Exception Om något går fel vid läsning (t.ex. filen inte finns)
     */
    Todo findById(UUID todoId) throws Exception;

    /**
     * Hämtar ALLA todos som finns sparade.
     *
     * @return En lista med alla todos (tom lista om inga finns)
     * @throws Exception Om något går fel vid läsning
     */
    List<Todo> findAll() throws Exception;

    /**
     * Sparar en todo (både nya och uppdaterade).
     *
     * Om todon är ny skapas den, om den redan finns uppdateras den.
     *
     * @param todo Todon som ska sparas
     * @throws Exception Om något går fel vid sparning
     */
    void save(Todo todo) throws Exception;

    /**
     * Raderar en todo permanent.
     *
     * @param todoId ID för todon som ska raderas
     * @throws Exception Om något går fel vid radering
     */
    void delete(UUID todoId) throws Exception;
}
