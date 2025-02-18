
public interface Tracker {

    void push(String message);

    Boolean has(String message);

    void handle(String message, EventHandler e);
}
