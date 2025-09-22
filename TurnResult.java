public class TurnResult {
    private final IPlayer player;
    private final int diceRoll;
    private final Position startPosition;
    private final Position finalPosition;
    private final boolean hadSpecialMove;
    private final boolean extraTurn;
    private final String description;

    public TurnResult(int diceRoll, Position finalPosition, boolean hadSpecialMove) {
        this.player = null;
        this.diceRoll = diceRoll;
        this.startPosition = null;
        this.finalPosition = finalPosition;
        this.hadSpecialMove = hadSpecialMove;
        this.extraTurn = false;
        this.description = "";
    }

    public TurnResult(IPlayer player, int diceRoll, Position startPosition, Position finalPosition, boolean extraTurn,
            String description) {
        this.player = player;
        this.diceRoll = diceRoll;
        this.startPosition = startPosition;
        this.finalPosition = finalPosition;
        this.hadSpecialMove = !startPosition.equals(finalPosition);
        this.extraTurn = extraTurn;
        this.description = description;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public boolean hadSpecialMove() {
        return hadSpecialMove;
    }

    public boolean hasExtraTurn() {
        return extraTurn;
    }

    public String getDescription() {
        return description;
    }
}
