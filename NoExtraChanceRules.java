public class NoExtraChanceRules implements GameRules {

    @Override
    public boolean shouldGetExtraTurn(int diceValue, IPlayer player) {
        return false; // No extra turns regardless of dice value
    }

    @Override
    public boolean shouldEndGame(int turnCount, int maxTurns) {
        return turnCount >= maxTurns;
    }

    @Override
    public boolean isValidMove(Position from, Position to, int boardSize) {
        int totalCells = boardSize * boardSize;
        return to.getValue() <= totalCells;
    }

    @Override
    public boolean hasWon(Position position, int winningPosition) {
        return position.getValue() == winningPosition;
    }
}
