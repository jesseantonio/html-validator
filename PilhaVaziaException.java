package trabalho;

public class PilhaVaziaException extends RuntimeException {

    public PilhaVaziaException() {
        super("A pilha está vazia");
    }
}
