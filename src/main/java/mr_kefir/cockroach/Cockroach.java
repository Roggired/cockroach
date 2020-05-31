package mr_kefir.cockroach;

final class Cockroach {
    private Field.Point position;


    Cockroach(Field.Point position) {
        this.position = position;
    }


    /** Movement directions:
     * 0 - North
     * 1 - East
     * 2 - South
     * 3 - West
     *
     * Direction always is chosen randomly.
     * After 4 bad tries cockroach decides to stay at the same position.
     */
    void move(Field field) {
        int positionI = 0, positionJ = 0;

        try{
            position.getI();
        } catch (RuntimeException exception) {
            positionI = Integer.parseInt(exception.getMessage());
        }

        try {
            position.getJ();
        } catch (RuntimeException exception) {
            positionJ = Integer.parseInt(exception.getMessage());
        }

        Field.Point newPoint = null;

        int counter = 4;

        while (counter > 0) {
            counter--;

            int direction = (int) (Math.random() * 4);

            try {
                switch (direction) {
                    case 0:
                        newPoint = new Field.Point(positionI - 1, positionJ);
                        break;
                    case 1:
                        newPoint = new Field.Point(positionI, positionJ + 1);
                        break;
                    case 2:
                        newPoint = new Field.Point(positionI + 1, positionJ);
                        break;
                    default:
                        newPoint = new Field.Point(positionI, positionJ - 1);
                }

                field.setCockroach(newPoint);
            } catch (RuntimeException exception) {
                String message = exception.getMessage();

                if (message.equals("Moved")) {
                    break;
                }
            }
        }

        position = newPoint;
    }
}
