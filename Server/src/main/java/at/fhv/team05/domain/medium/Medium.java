package at.fhv.team05.domain.medium;

public abstract class Medium {
    public abstract int getId();

    public abstract String getTitle();

    public String getType() {
        return getClass().getSimpleName();
    }

}
