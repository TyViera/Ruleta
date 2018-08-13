package pe.edu.unp.enums;

public enum HalfEnum {

    HIGH("high"),
    LOW("low");

    private final String value;

    private HalfEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
