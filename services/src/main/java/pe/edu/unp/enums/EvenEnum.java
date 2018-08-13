package pe.edu.unp.enums;

public enum EvenEnum {

    EVEN("even"),
    ODD("odd");

    private final String value;

    private EvenEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
