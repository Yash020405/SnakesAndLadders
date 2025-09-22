public class Ladder extends BoardEntity {
    
    public Ladder(Position bottom, Position top) {
        super(bottom, top, EntityType.LADDER);
    }
    
    @Override
    protected void validatePositions() {
        if (start.getValue() >= end.getValue()) {
            throw new IllegalArgumentException("Ladder bottom must be at lower position than top");
        }
    }
    
    public Position getBottom() {
        return start;
    }
    
    public Position getTop() {
        return end;
    }
}
