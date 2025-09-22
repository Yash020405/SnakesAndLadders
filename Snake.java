public class Snake extends BoardEntity {
    
    public Snake(Position head, Position tail) {
        super(head, tail, EntityType.SNAKE);
    }
    
    @Override
    protected void validatePositions() {
        if (start.getValue() <= end.getValue()) {
            throw new IllegalArgumentException("Snake head must be at higher position than tail");
        }
    }
    
    public Position getHead() {
        return start;
    }
    
    public Position getTail() {
        return end;
    }
}
