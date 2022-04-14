package enums;

public enum Country {

    POLAND("pl");

    private String value;

    Country(String value){
        this.value = value;
    }

    public String getValue (){
        return value;
    }
}
