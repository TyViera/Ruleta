package pe.edu.unp.enums;

public enum BetTypeEnum {

    NUMBER("N"),
    COLOUR("C"),
    EVEN_ODD("E"),
    HALF("H");

    private final String value;

    private BetTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
