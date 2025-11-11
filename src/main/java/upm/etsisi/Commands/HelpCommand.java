package upm.etsisi.Commands;

import java.util.Map;

public class HelpCommand implements IComando{
    private final Map<String, IComando> registeredCommands;

    public HelpCommand(Map<String, IComando> registeredCommands) {
        this.registeredCommands = registeredCommands;
    }

    @Override
    public String ejecutar(String[] args) {
        return null;
    }

    @Override
    public int getNumArgumentos() {
        return 0;
    }

    @Override
    public String getDescripcion(){
        return "Show information about de commands";
    }

    @Override
    public String getEjemploUso() {
        return "help";
    }
}
