public class BoardEntity {
    private final Position start;
    private final Position end;
    private final EntityType type;

    public enum EntityType {
        SNAKE, LADDER
    }

    public BoardEntity(Position start, Position end, EntityType type) {
        this.start = start;
        this.end = end;
        this.type = type;
        validatePositions();
    }

    public static BoardEntity createSnake(Position head, Position tail) {
        return new BoardEntity(head, tail, EntityType.SNAKE);
    }

    public static BoardEntity createLadder(Position bottom, Position top) {
        return new BoardEntity(bottom, top, EntityType.LADDER);
    }

    private void validatePositions() {
        if (type == EntityType.SNAKE && start.getValue() <= end.getValue()) {
            throw new IllegalArgumentException("Snake head must be at higher position than tail");
        }
        if (type == EntityType.LADDER && start.getValue() >= end.getValue()) {
            throw new IllegalArgumentException("Ladder bottom must be at lower position than top");
        }
    }

    public boolean isTriggeredBy(Position position) {
        return start.equals(position);
    }

    public Position getDestination() {
        return end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public EntityType getType() {
        return type;
    }

    public boolean isSnake() {
        return type == EntityType.SNAKE;
    }

    public boolean isLadder() {
        return type == EntityType.LADDER;
    }

    // Convenience methods for Snake
    public Position getHead() {
        if (type != EntityType.SNAKE) {
            throw new IllegalStateException("Only snakes have head");
        }
        return start;
    }

    public Position getTail() {
        if (type != EntityType.SNAKE) {
            throw new IllegalStateException("Only snakes have tail");
        }
        return end;
    }

    // Convenience methods for Ladder
    public Position getBottom() {
        if (type != EntityType.LADDER) {
            throw new IllegalStateException("Only ladders have bottom");
        }
        return start;
    }

    public Position getTop() {
        if (type != EntityType.LADDER) {
            throw new IllegalStateException("Only ladders have top");
        }
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BoardEntity entity = (BoardEntity) obj;
        return start.equals(entity.start) && end.equals(entity.end) && type == entity.type;
    }

    @Override
    public int hashCode() {
        return start.hashCode() + end.hashCode() + type.hashCode();
    }

    @Override
    public String toString() {
        return type.name().toLowerCase() + "{" + start.getValue() + " -> " + end.getValue() + "}";
    }
}
