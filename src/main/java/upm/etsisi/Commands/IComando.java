package upm.etsisi.Commands;

public interface IComando {

    String ejecutar(String[] args);
    int getNumArgumentos();
    String getDescripcion();
    String getEjemploUso();
}
