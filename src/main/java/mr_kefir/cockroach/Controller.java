package mr_kefir.cockroach;

final class Controller {
    private final Console console;
    private final Cockroach cockroach;
    private final Field field;
    private int turnsUntilDefeat;


    Controller(Console console,
               Cockroach cockroach,
               Field field,
               int turnsUntilDefeat) {
        this.console = console;
        this.cockroach = cockroach;
        this.field = field;
        this.turnsUntilDefeat = turnsUntilDefeat;
    }


    void start() {
        welcomePart();

        while (true) {
            showCockroachCurrentPosition();

            try {
                console.read();
            } catch (RuntimeException exception) {
                // the input isn't important just wait until user has typed something
            }

            cockroach.move(field);
            showCurrentFieldAfterCockroachMoved();
            console.println("Now you can fire:");

            try {
                console.read();
            } catch (RuntimeException exception) {
                String userInput = exception.getMessage();

                try {
                    processInput(userInput);
                } catch (RuntimeException nextException) {
                    String message = nextException.getMessage();

                    if (message.equals("Continue")) {
                        continue;
                    }

                    if (message.equals("Cockroach")) {
                        console.println("Great! You've killed the cockroach!");
                        break;
                    }

                    console.println("Erh! You've missed!");
                    turnsUntilDefeat--;

                    if (turnsUntilDefeat == 0) {
                        console.println("You are loser! The cockroach has disappeared.");
                        break;
                    }
                }
            }
        }
    }

    private void welcomePart() {
        console.println("Welcome to the game!");
        console.println("Try to kill cockroach. Now you can see his initial position.");
        console.println("The cockroach can run in 4 directions: up, right, down, left.");
        console.println("Also he can stay at the same point. Note that you have only 7");
        console.println("turns until the cockroach will disappear and you will be never");
        console.println("able to kill him! (Cockroach can't step on fired cells)");
        console.println("Write I and J coordinates separated with single space to fire.");
        console.println("Note that coordinates start with zero!");
    }

    private void showCockroachCurrentPosition() {
        console.println("This is the current cockroach position.");
        field.drawInit(console);
        console.println("Press enter to continue.");
    }

    private void showCurrentFieldAfterCockroachMoved() {
        console.println("==============================================================");
        console.println("Where are you, cockroach? (He's moved or stayed)");
        console.println("Turns until cockroach disappears: " + turnsUntilDefeat);

        if (turnsUntilDefeat <= 2) {
            console.println("Fuck! Harry up!");
        }

        field.draw(console);
    }

    private void processInput(String userInput) {
        String[] subStrings = userInput.split(" +");

        int i, j;
        try {
            i = Integer.parseInt(subStrings[0]);
            j = Integer.parseInt(subStrings[1]);
        } catch (Throwable throwable) {
            console.println("Bad input! You time is going! Don't mess it up...");
            throw new RuntimeException("Continue");
        }

        Field.Point firedPoint = new Field.Point(i, j);

        field.fire(firedPoint);
    }
}
