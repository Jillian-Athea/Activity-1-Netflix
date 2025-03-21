import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publish(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}

interface EventListener {
    void onEvent(Event event);
}
