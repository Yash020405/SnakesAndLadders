public interface IPlayer {
    String getName();

    Position getCurrentPosition();

    void setPosition(Position position);

    boolean hasWon(int winningPosition);

    int getId();

    TurnResult takeTurn(Board board, Dice dice);
}
