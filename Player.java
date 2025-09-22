public abstract class Player implements IPlayer {
    protected final String name;
    protected Position currentPosition;
    protected final int id;

    public Player(String name, int id, Position startPosition) {
        this.name = name;
        this.id = id;
        this.currentPosition = startPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    @Override
    public boolean hasWon(int winningPosition) {
        return currentPosition.getValue() >= winningPosition;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public TurnResult takeTurn(Board board, Dice dice) {
        int diceRoll = dice.roll();
        System.out.println(name + " rolled: " + diceRoll);

        Position newPosition = currentPosition.add(diceRoll);

        if (newPosition.getValue() > board.getTotalCells()) {
            System.out.println(name + " needs exact number to win. Staying at position " + currentPosition.getValue());
            return new TurnResult(diceRoll, currentPosition, false);
        }

        Position finalPosition = board.getFinalPosition(newPosition);
        boolean hadSpecialMove = !newPosition.equals(finalPosition);

        if (hadSpecialMove) {
            if (board.hasSnake(newPosition)) {
                System.out.println(name + " encountered a snake! Sliding down from " +
                        newPosition.getValue() + " to " + finalPosition.getValue());
            } else if (board.hasLadder(newPosition)) {
                System.out.println(name + " found a ladder! Climbing up from " +
                        newPosition.getValue() + " to " + finalPosition.getValue());
            }
        }

        this.currentPosition = finalPosition;
        return new TurnResult(diceRoll, finalPosition, hadSpecialMove);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Player player = (Player) obj;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
