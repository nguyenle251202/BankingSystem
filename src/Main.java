import exception_handling.testThread.RaceCondition;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        RaceCondition myThread = new RaceCondition();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}
