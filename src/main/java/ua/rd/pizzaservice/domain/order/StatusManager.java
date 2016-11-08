package ua.rd.pizzaservice.domain.order;

import java.util.*;

import static ua.rd.pizzaservice.domain.order.StatusManager.Status.*;

public class StatusManager {

    public enum Status {
        NEW, IN_PROGRESS, CANCELED, DONE
    }

    private static Map<Status, Set<Status>> transitionTable = new HashMap<Status, Set<Status>>() {
        {
            put(null, new HashSet<Status>() {{
                add(IN_PROGRESS);
                add(CANCELED);
            }});
            put(NEW, new HashSet<Status>() {{
                add(IN_PROGRESS);
                add(CANCELED);
            }});
            put(IN_PROGRESS, new HashSet<Status>() {{
                add(CANCELED);
                add(DONE);
            }});
            put(CANCELED, Collections.EMPTY_SET);
            put(DONE, Collections.EMPTY_SET);

        }
    };

    public static boolean isTransitionAllowed(Status statusFrom, Status statusTo) {

        return transitionTable.
                get(statusFrom).
                contains(statusTo);
    }
}
