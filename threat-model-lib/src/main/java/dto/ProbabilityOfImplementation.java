package dto;

public enum ProbabilityOfImplementation {

    FIRST("0"),

    SECOND("0.2"),
    THIRD("0.5"),
    FOURTH("1");

    public static final ProbabilityOfImplementation[] ALL = { FIRST, SECOND, THIRD, FOURTH };

    private final String name;

    ProbabilityOfImplementation(String name) {
        this.name = name;
    }
}
