package me.code.models;

/**
 * Enum som representerar de olika status en todo kan ha.
 *
 * Ett enum är som en lista av fördefinierade konstanter - perfekt när du vet
 * alla möjliga värden på förhand (som här: pending, in-progress, completed).
 *
 * Vi använder enum istället för vanliga strängar för att undvika stavfel
 * och få bättre kompilatorstöd!
 */
public enum TodoStatus {
    // De tre olika statusarna en todo kan ha
    PENDING("pending"),           // Inte påbörjad än
    IN_PROGRESS("in-progress"),   // Pågående arbete
    COMPLETED("completed");        // Färdig!

    // Hur vi vill visa statusen för användaren (med bindestreck istället för underscore)
    private final String displayName;

    /**
     * Konstruktor som körs för varje enum-värde.
     * Vi skickar in displayName för att kunna visa statusen snyggt.
     */
    TodoStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Hämtar det användarvänliga namnet på statusen.
     * Exempel: TodoStatus.IN_PROGRESS.getDisplayName() returnerar "in-progress"
     */
    public String getDisplayName() {
        return displayName;
    }
}
