package bll.util;

public class IDGenerator {
    public static int count = 1;

    public IDGenerator() {
    }

    public static int getNextID() {
        return count++;
    }
}
