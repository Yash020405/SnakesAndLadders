import java.util.Random;

public class Dice {
    private static Dice instance;
    private final Random random;
    private static final int SIDES = 6;

    private Dice() {
        this.random = new Random();
    }

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public int roll() {
        return random.nextInt(SIDES) + 1;
    }

    public int getSides() {
        return SIDES;
    }
}
