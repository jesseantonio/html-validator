package trabalho;

public class PilhaLista implements Pilha<Tag> {

    private ListaEncadeada lista = new ListaEncadeada();

    @Override
    public void push(Tag info) {
        lista.inserir(info);
    }

    @Override
    public Tag pop() {
        Tag valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public Tag peek() {
        if (lista.getPrimeiro() == null) {
            throw new PilhaVaziaException();
        }
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return lista.getPrimeiro() == null;
    }

    @Override
    public void liberar() {
        lista = new ListaEncadeada();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
