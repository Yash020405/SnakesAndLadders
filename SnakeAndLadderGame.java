import java.util.*;

public class SnakeAndLadderGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Snake and Ladder Game!");

        // Get board size
        System.out.print("Enter board size (n for nxn board, min 4): ");
        int boardSize = scanner.nextInt();
        if (boardSize < 4) {
            System.out.println("Board size must be at least 4. Setting to 4.");
            boardSize = 4;
        }

        // Get number of players
        System.out.print("Enter number of players (min 1): ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 1) {
            System.out.println("At least 1 player is required. Setting to 1.");
            numPlayers = 1;
        }
        scanner.nextLine(); // consume newline

        // Choose game rules
        System.out.println("Choose game rules:");
        System.out.println("1. Standard Rules (Extra turn on 6, max 3 consecutive 6s)");
        System.out.println("2. No Extra Chance Rules (No extra turns)");
        System.out.print("Enter choice (1 or 2): ");
        int ruleChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        GameRules gameRules = (ruleChoice == 2) ? new NoExtraChanceRules() : new StandardRules();

        // Create players
        List<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Is " + name + " a bot? (y/n): ");
            String botChoice = scanner.nextLine().toLowerCase();

            Position startPosition = new Position(1);
            if (botChoice.equals("y")) {
                players.add(new BotPlayer(name, i + 1, startPosition));
            } else {
                players.add(new HumanPlayer(name, i + 1, startPosition));
            }
        }

        // Generate random board
        Board board = generateRandomBoard(boardSize);

        // Create and play game
        Game game = new Game(board, players, gameRules);
        playGame(game);

        scanner.close();
    }

    private static void playGame(Game game) {
        System.out.println("\nSnake and Ladder Game Started!");
        System.out.println("Board Size: " + game.getBoard().getSize() + "x" + game.getBoard().getSize() +
                " (Total: " + game.getBoard().getTotalCells() + " cells)");
        printBoard(game.getBoard());

        int turnCount = 0;
        while (!game.isGameOver() && turnCount < 1000) {
            IPlayer currentPlayer = game.getCurrentPlayer();

            System.out.println("\n--- " + currentPlayer.getName() + "'s Turn ---");
            System.out.println("Current Position: " + currentPlayer.getCurrentPosition().getValue());

            TurnResult result = game.playTurn();
            System.out.println(result.getDescription());

            if (result.hasExtraTurn()) {
                System.out.println(currentPlayer.getName() + " gets another turn!");
            }

            turnCount++;
        }

        if (game.isGameOver()) {
            System.out.println("\n" + game.getWinner().getName() + " WINS!");
        } else {
            System.out.println("\nGame reached maximum turns limit!");
        }
    }

    private static void printBoard(Board board) {
        System.out.println("\nBoard Setup:");
        List<BoardEntity> snakes = board.getSnakes();
        List<BoardEntity> ladders = board.getLadders();

        System.out.println("Snakes: " + snakes.size());
        snakes.forEach(snake -> System.out
                .println("  Snake from " + snake.getStart().getValue() + " to " + snake.getEnd().getValue()));

        System.out.println("Ladders: " + ladders.size());
        ladders.forEach(ladder -> System.out
                .println("  Ladder from " + ladder.getStart().getValue() + " to " + ladder.getEnd().getValue()));
    }

    private static Board generateRandomBoard(int size) {
        Random random = new Random();
        int totalCells = size * size;
        Set<Integer> usedPositions = new HashSet<>();

        List<BoardEntity> entities = new ArrayList<>();

        // Generate snakes (10% of board)
        int numSnakes = Math.max(1, totalCells / 10);
        for (int i = 0; i < numSnakes; i++) {
            int head, tail;
            do {
                head = random.nextInt(totalCells - 10) + 11; // From position 11 to totalCells
                tail = random.nextInt(head - 1) + 1; // Below head position
            } while (usedPositions.contains(head) || head == totalCells);

            entities.add(BoardEntity.createSnake(new Position(head), new Position(tail)));
            usedPositions.add(head);
        }

        // Generate ladders (8% of board)
        int numLadders = Math.max(1, (totalCells * 8) / 100);
        for (int i = 0; i < numLadders; i++) {
            int bottom, top;
            do {
                bottom = random.nextInt(totalCells - 10) + 2; // From position 2 to totalCells-10
                top = random.nextInt(totalCells - bottom) + bottom + 1; // Above bottom position
            } while (usedPositions.contains(bottom) || top >= totalCells);

            entities.add(BoardEntity.createLadder(new Position(bottom), new Position(top)));
            usedPositions.add(bottom);
        }

        System.out.println("\nBoard " + size + "x" + size + " created with " +
                numSnakes + " snakes and " + numLadders + " ladders");

        return new Board(size, entities);
    }
}
