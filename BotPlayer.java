public class BotPlayer extends Player {

    public BotPlayer(String name, int id, Position startPosition) {
        super(name, id, startPosition);
    }

    @Override
    public String toString() {
        return "Bot Player: " + name + " (ID: " + id + ", Position: " + currentPosition.getValue() + ")";
    }
}
