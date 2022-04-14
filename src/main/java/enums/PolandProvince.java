package enums;

public enum PolandProvince {

    LODZKIE("4");

    private String value;

    PolandProvince(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
