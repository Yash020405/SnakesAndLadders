public interface GameRules {
    boolean shouldGetExtraTurn(int diceValue, IPlayer player);

    boolean shouldEndGame(int turnCount, int maxTurns);

    boolean isValidMove(Position from, Position to, int boardSize);

    boolean hasWon(Position position, int winningPosition);
}
