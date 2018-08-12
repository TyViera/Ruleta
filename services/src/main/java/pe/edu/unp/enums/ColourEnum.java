package pe.edu.unp.enums;

public enum ColourEnum {

    GREEN("G"),
    RED("R"),
    BLACK("B");

    private final String value;

    private ColourEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
