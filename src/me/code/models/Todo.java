package me.code.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Modellklass som representerar en todo-uppgift.
 *
 * Detta är hjärtat i vår applikation - här lagrar vi all information om en todo.
 * Klassen är en "modell" vilket betyder att den bara håller data, ingen affärslogik.
 *
 * Tänk på det som en mall eller ritning för hur en todo ska se ut!
 */
public class Todo {

    // Vi skapar ett gemensamt datumformat som alla todos använder (år-månad-dag)
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Unikt ID för varje todo - använder UUID som garanterar unikhet
    // 'final' betyder att ID:t aldrig kan ändras efter det skapats
    private final UUID id;

    // Titel på todon, t.ex. "Handla mat"
    private String title;

    // När todon ska vara klar
    private Date deadline;

    // Vilken kategori todon tillhör, t.ex. "arbete" eller "privat"
    private String category;

    // Nuvarande status (pending, in-progress, eller completed)
    private TodoStatus status;

    // Prioritet där högre nummer = viktigare todo
    private int priority;

    /**
     * Konstruktor för att skapa en helt ny todo.
     *
     * När du använder denna konstruktor skapar vi automatiskt ett nytt unikt ID
     * och sätter status till PENDING (eftersom todon precis skapades).
     *
     * @param title Todoens titel
     * @param deadline När todon ska vara klar
     * @param category Vilken kategori todon tillhör
     * @param priority Prioritet (högre = viktigare)
     */
    public Todo(String title, Date deadline, String category, int priority) {
        this.id = UUID.randomUUID();  // Genererar ett nytt slumpmässigt unikt ID
        this.title = title;
        this.deadline = deadline;
        this.category = category;
        this.priority = priority;
        this.status = TodoStatus.PENDING;  // Nya todos är alltid "pending"
    }

    /**
     * Konstruktor för att återskapa en befintlig todo (t.ex. från en fil).
     *
     * Denna används när vi läser in todos som redan finns sparade,
     * då har vi redan ett ID och status som vi vill behålla.
     *
     * @param id Befintligt ID
     * @param title Todoens titel
     * @param deadline När todon ska vara klar
     * @param category Vilken kategori todon tillhör
     * @param priority Prioritet
     * @param status Nuvarande status
     */
    public Todo(UUID id, String title, Date deadline, String category, int priority, TodoStatus status) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.category = category;
        this.priority = priority;
        this.status = status;
    }

    /**
     * Översätter todon till en snygg textrepresentation.
     *
     * Vi överlagrar (override) toString() från Object-klassen för att kunna
     * skriva ut todos på ett läsbart sätt. Detta är användbart för debugging
     * och för att visa todos i terminalen!
     *
     * @return En formaterad sträng med all information om todon
     */
    @Override
    public String toString() {
        return this.title + "\n    " +
                "Id: " + this.id + "\n    " +
                "Status: " + this.status.getDisplayName() + "\n    " +
                "Category: " + this.category + "\n    " +
                "Deadline: " + DATE_FORMAT.format(this.deadline) + "\n    " +
                "Priority: " + this.priority;
    }

    // === GETTERS & SETTERS ===
    // Dessa metoder ger oss kontrollerad åtkomst till klassens privata fält.
    // Vi använder private fält + public getters/setters för att skydda datan (inkapsling).

    /**
     * Hämtar todoens titel.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Uppdaterar todoens titel.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Hämtar deadline för todon.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Uppdaterar deadline för todon.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Hämtar kategorin som todon tillhör.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Uppdaterar kategorin för todon.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Hämtar nuvarande status för todon (PENDING, IN_PROGRESS, eller COMPLETED).
     */
    public TodoStatus getStatus() {
        return status;
    }

    /**
     * Uppdaterar statusen för todon.
     */
    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    /**
     * Hämtar prioriteten för todon (högre nummer = viktigare).
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Uppdaterar prioriteten för todon.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Hämtar todoens unika ID.
     * Observera: Vi har ingen setter här eftersom ID:t är 'final' och aldrig får ändras!
     */
    public UUID getId() {
        return id;
    }
}
