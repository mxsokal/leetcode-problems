import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
import static java.util.Objects.requireNonNull;


/*
    Problem Description:

        Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given
        timestamp, otherwise returns false.
        It is possible that several messages arrive roughly at the same time.

        Examples:

            Input: ["h","e","l","l","o"]
                Logger logger = new Logger();
                // logging string "foo" at timestamp 1
                logger.shouldPrintMessage(1, "foo"); returns true; 
                // logging string "bar" at timestamp 2
                logger.shouldPrintMessage(2,"bar"); returns true;
                // logging string "foo" at timestamp 3
                logger.shouldPrintMessage(3,"foo"); returns false;
                // logging string "bar" at timestamp 8
                logger.shouldPrintMessage(8,"bar"); returns false;
                // logging string "foo" at timestamp 10
                logger.shouldPrintMessage(10,"foo"); returns false;
                // logging string "foo" at timestamp 11
                logger.shouldPrintMessage(11,"foo"); returns true;

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Logger {

    private static final int NO_DUP_INTERVAL = 10; // seconds

    private final Map<String, Integer> lastMessages;
    private int firstTimestamp;

    public Logger() {
        this.lastMessages = new LinkedHashMap<>();
        this.firstTimestamp = Integer.MIN_VALUE;
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean result = false;

        requireNonNull(message, "message is null");
        if (timestamp < 0) {
            throw new IllegalArgumentException("timestamp value " + timestamp + " is negative");
        }
        if (timestamp >= firstTimestamp + NO_DUP_INTERVAL) {
            dropObsoleteMessages(timestamp);
        }
        if (!lastMessages.containsKey(message)) {
            lastMessages.put(message, timestamp);
            result = true;
        }
        return result;
    }

    private void dropObsoleteMessages(int timestamp) {
        Iterator<Integer> iterator = lastMessages.values().iterator();
        int threshold = timestamp - NO_DUP_INTERVAL;

        while (iterator.hasNext()) {
            firstTimestamp = iterator.next();
            if (firstTimestamp <= threshold) {
                iterator.remove();
            } else {
                break;
            }
        }
        if (lastMessages.isEmpty()) {
            firstTimestamp = timestamp;
        }
    }

}