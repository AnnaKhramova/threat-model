package dto;

public enum Danger {

    FIRST("0.2"),
    SECOND("0.6"),
    THIRD("1");

    public static final Danger[] ALL = { FIRST, SECOND, THIRD };

    private final String name;

    Danger(String name) {
        this.name = name;
    }

}
