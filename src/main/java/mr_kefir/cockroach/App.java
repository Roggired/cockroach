package mr_kefir.cockroach;

public final class App {
    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);

        Field.Point initCockroachPoint = new Field.Point(2, 1);
        Cockroach cockroach = new Cockroach(initCockroachPoint);

        Field field = new Field(5, 5, initCockroachPoint);

        new Controller(console, cockroach, field, 7).start();
    }
}
