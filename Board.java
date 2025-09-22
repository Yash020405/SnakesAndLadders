import java.util.*;

public class Board {
    private final int size;
    private final int totalCells;
    private final Map<Position, BoardEntity> entities;

    public Board(int size, List<BoardEntity> boardEntities) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }

        this.size = size;
        this.totalCells = size * size;
        this.entities = new HashMap<>();

        for (BoardEntity entity : boardEntities) {
            validateEntity(entity);
            if (entities.containsKey(entity.getStart())) {
                throw new IllegalArgumentException("Position " + entity.getStart().getValue() + " already occupied");
            }
            entities.put(entity.getStart(), entity);
        }
    }

    private void validateEntity(BoardEntity entity) {
        if (!isValidPosition(entity.getStart()) || !isValidPosition(entity.getEnd())) {
            throw new IllegalArgumentException("Entity positions must be within board bounds");
        }

        if (entity.getStart().getValue() == totalCells || entity.getEnd().getValue() == totalCells) {
            throw new IllegalArgumentException("Entities cannot start or end at winning position");
        }
    }

    public Position getFinalPosition(Position position) {
        validatePosition(position);

        BoardEntity entity = entities.get(position);
        if (entity != null) {
            return entity.getDestination();
        }

        return position;
    }

    public boolean isValidPosition(Position position) {
        return position.getValue() >= 1 && position.getValue() <= totalCells;
    }

    public boolean isWinningPosition(Position position) {
        return position.getValue() == totalCells;
    }

    public boolean hasSnake(Position position) {
        BoardEntity entity = entities.get(position);
        return entity != null && entity.isSnake();
    }

    public boolean hasLadder(Position position) {
        BoardEntity entity = entities.get(position);
        return entity != null && entity.isLadder();
    }

    public int getSize() {
        return size;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public List<BoardEntity> getSnakes() {
        return entities.values().stream()
                .filter(BoardEntity::isSnake)
                .collect(ArrayList::new, (list, snake) -> list.add(snake), (list1, list2) -> list1.addAll(list2));
    }

    public List<BoardEntity> getLadders() {
        return entities.values().stream()
                .filter(BoardEntity::isLadder)
                .collect(ArrayList::new, (list, ladder) -> list.add(ladder), (list1, list2) -> list1.addAll(list2));
    }

    private void validatePosition(Position position) {
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Position " + position.getValue() + " is not valid on this board");
        }
    }
}
