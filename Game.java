import java.util.*;

public class Game {
    private final Board board;
    private final List<IPlayer> players;
    private final Dice dice;
    private final GameRules gameRules;
    private int currentPlayerIndex;
    private boolean gameOver;
    private IPlayer winner;

    public Game(Board board, List<IPlayer> players, GameRules gameRules) {
        validateInputs(board, players, gameRules);

        this.board = board;
        this.players = new ArrayList<>(players);
        this.dice = Dice.getInstance();
        this.gameRules = gameRules;
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.winner = null;
    }

    private void validateInputs(Board board, List<IPlayer> players, GameRules gameRules) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("At least one player is required");
        }
        if (gameRules == null) {
            throw new IllegalArgumentException("Game rules cannot be null");
        }

        Set<String> playerNames = new HashSet<>();
        for (IPlayer player : players) {
            if (player == null) {
                throw new IllegalArgumentException("Player cannot be null");
            }
            if (!playerNames.add(player.getName())) {
                throw new IllegalArgumentException("Player names must be unique");
            }
        }
    }

    public TurnResult playTurn() {
        if (gameOver) {
            return new TurnResult(winner, 0, winner.getCurrentPosition(), winner.getCurrentPosition(), false,
                    "Game is already over");
        }

        IPlayer currentPlayer = players.get(currentPlayerIndex);
        int diceValue = dice.roll();

        Position oldPosition = currentPlayer.getCurrentPosition();
        Position newPosition = calculateNewPosition(currentPlayer, diceValue);
        Position finalPosition = board.getFinalPosition(newPosition);

        currentPlayer.setPosition(finalPosition);

        if (board.isWinningPosition(finalPosition)) {
            gameOver = true;
            winner = currentPlayer;
        }

        String moveDescription = createMoveDescription(currentPlayer, diceValue, oldPosition, finalPosition);

        boolean getExtraTurn = !gameOver && gameRules.shouldGetExtraTurn(diceValue, currentPlayer);

        if (!getExtraTurn) {
            moveToNextPlayer();
        }

        return new TurnResult(currentPlayer, diceValue, oldPosition, finalPosition, getExtraTurn, moveDescription);
    }

    private Position calculateNewPosition(IPlayer player, int diceValue) {
        int newValue = player.getCurrentPosition().getValue() + diceValue;

        if (newValue > board.getTotalCells()) {
            return player.getCurrentPosition(); // Stay at current position if overshooting
        }

        return new Position(newValue);
    }

    private String createMoveDescription(IPlayer player, int diceValue, Position oldPosition, Position finalPosition) {
        StringBuilder description = new StringBuilder();
        description.append(player.getName())
                .append(" rolled ")
                .append(diceValue)
                .append(", moved from ")
                .append(oldPosition.getValue())
                .append(" to ")
                .append(finalPosition.getValue());

        if (board.hasSnake(new Position(oldPosition.getValue() + diceValue))) {
            description.append(" (down a snake)");
        } else if (board.hasLadder(new Position(oldPosition.getValue() + diceValue))) {
            description.append(" (up a ladder)");
        }

        if (board.isWinningPosition(finalPosition)) {
            description.append(" and won the game!");
        }

        return description.toString();
    }

    private void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public IPlayer getWinner() {
        return winner;
    }

    public IPlayer getCurrentPlayer() {
        if (gameOver) {
            return winner;
        }
        return players.get(currentPlayerIndex);
    }

    public List<IPlayer> getPlayers() {
        return new ArrayList<>(players);
    }

    public Board getBoard() {
        return board;
    }

    public GameRules getGameRules() {
        return gameRules;
    }
}
