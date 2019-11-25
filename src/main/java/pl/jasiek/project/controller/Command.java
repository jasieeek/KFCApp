package pl.jasiek.project.controller;

public interface Command {
    void execute();
    String getLabel();
}
