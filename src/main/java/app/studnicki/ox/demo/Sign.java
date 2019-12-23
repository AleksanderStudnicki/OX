package app.studnicki.ox.demo;

public enum Sign {
    NAUGHT("O"), CROSS("X"), EMPTY(" ");

    private final String value;


    Sign(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
