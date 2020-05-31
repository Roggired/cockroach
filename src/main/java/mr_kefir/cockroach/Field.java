package mr_kefir.cockroach;

/**
 * 0 - empty cell.
 * 1 - fired cell.
 * 2 - mr_kefir.cockroach cell.
 */
final class Field {
    private final byte[][] field;
    private Point cockRoachPoint;


    Field(int sizeI, int sizeJ, Point cockRoachPoint) {
        this.field = new byte[sizeI][sizeJ];
        this.cockRoachPoint = cockRoachPoint;

        field[cockRoachPoint.i][cockRoachPoint.j] = 2;
    }


    void setCockroach(Point newCockroachPoint) {
        if (newCockroachPoint.i < 0 || newCockroachPoint.i >= field.length) {
            throw new RuntimeException("Bad i");
        }

        if (newCockroachPoint.j < 0 || newCockroachPoint.j >= field[0].length) {
            throw new RuntimeException("Bad j");
        }

        byte targetCell = field[newCockroachPoint.i][newCockroachPoint.j];

        if (targetCell == 1) {
            throw new RuntimeException("Fired");
        }

        field[cockRoachPoint.i][cockRoachPoint.j] = 0;

        cockRoachPoint = newCockroachPoint;

        field[cockRoachPoint.i][cockRoachPoint.j] = 2;

        throw new RuntimeException("Moved");
    }

    void fire(Point point) {
        byte targetCell = field[point.i][point.j];

        if (targetCell == 2) {
            throw new RuntimeException("Cockroach");
        }

        field[point.i][point.j] = 1;

        throw new RuntimeException("Empty");
    }

    void drawInit(Console console) {
        String string = "";

        for (byte[] bytes : field) {
            for (byte cell : bytes) {
                string += cell + " ";
            }

            console.println(string);
            string = "";
        }
    }

    void draw(Console console) {
        String string = "";

        for (byte[] bytes : field) {
            for (byte cell : bytes) {
                if (cell == 2) {
                    string += 0 + " ";
                    continue;
                }

                string += cell + " ";
            }

            console.println(string);
            string = "";
        }
    }


    static final class Point {
        private final int i, j;


        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }


        void getI() {
            throw new RuntimeException(String.valueOf(i));
        }

        void getJ() {
            throw new RuntimeException(String.valueOf(j));
        }
    }
}
