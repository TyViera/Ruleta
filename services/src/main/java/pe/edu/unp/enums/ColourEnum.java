package pe.edu.unp.enums;

public enum ColourEnum {

    GREEN("green"),
    RED("red"),
    BLACK("black");

    private final String value;

    private ColourEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
