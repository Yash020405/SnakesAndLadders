import java.util.HashMap;
import java.util.Map;

public class StandardRules implements GameRules {
    private static final int MAX_CONSECUTIVE_SIXES = 3;
    private final Map<String, Integer> consecutiveSixes = new HashMap<>();

    @Override
    public boolean shouldGetExtraTurn(int diceValue, IPlayer player) {
        String playerName = player.getName();

        if (diceValue == 6) {
            int currentCount = consecutiveSixes.getOrDefault(playerName, 0) + 1;
            consecutiveSixes.put(playerName, currentCount);

            if (currentCount >= MAX_CONSECUTIVE_SIXES) {
                // Reset player position to start and don't give extra turn
                player.setPosition(new Position(1));
                consecutiveSixes.put(playerName, 0);
                return false;
            }
            return true;
        } else {
            consecutiveSixes.put(playerName, 0);
            return false;
        }
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
