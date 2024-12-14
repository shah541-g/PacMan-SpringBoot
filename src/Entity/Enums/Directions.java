package Entity.Enums;

public enum Directions {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    private final char symbol;

    Directions(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
