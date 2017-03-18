package by.gsu.epamlab.enums;

public enum IndexPosition {
    ZERO(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8),
    NINTH(9),
    TENTH(10);
    private int index;

    IndexPosition(int index) {
        this.index = index;
    }
    public int getIndex(){
        return index;
    }
}
