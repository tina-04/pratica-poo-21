package upm.etsisi.Commands;

public abstract  class Command {
    private String name;
    public Command(String name) {
        this.name=name;
    }
    public abstract boolean apply(String[] args);
}
