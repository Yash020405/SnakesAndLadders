public class Position {
    private final int value;

    public Position(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Position cannot be negative");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Position add(int steps) {
        return new Position(value + steps);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position position = (Position) obj;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return "Position{" + value + "}";
    }
}
