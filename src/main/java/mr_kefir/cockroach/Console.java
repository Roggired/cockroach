package mr_kefir.cockroach;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

final class Console {
    private final Scanner scanner;
    private final PrintWriter printWriter;


    Console(InputStream inputStream,
            OutputStream outputStream) {
        scanner = new Scanner(inputStream);
        printWriter = new PrintWriter(outputStream);
    }


    void println(String string) {
        printWriter.println(string);
        printWriter.flush();
    }

    void read() {
        String userInput = scanner.nextLine();
        throw new RuntimeException(userInput);
    }
}
