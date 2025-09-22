public class HumanPlayer extends Player {

    public HumanPlayer(String name, int id, Position startPosition) {
        super(name, id, startPosition);
    }

    @Override
    public String toString() {
        return "Human Player: " + name + " (ID: " + id + ", Position: " + currentPosition.getValue() + ")";
    }
}
