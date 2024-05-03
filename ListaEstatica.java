package trabalho;

import java.util.Objects;

public class ListaEstatica {

    public Tag[] getInfo() {
        return info;
    }

    private Tag[] info;
    private int tamanho;

    public ListaEstatica() {
        this.info = new Tag[10];
        this.tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    private void redimensionar() {
        Tag[] novoVetor = new Tag[getTamanho() + 10];
        for (int i = 0; i < getTamanho(); i++) {
            novoVetor[i] = this.info[i];
        }
        this.info = novoVetor;
    }

    public void inserir(Tag valor) {
        if (info.length == getTamanho()) {
            redimensionar();
        }
        int index = buscar(valor);
        if (index != -1) {
            Tag tag = obterElemento(index);
            tag.aumentaQuantidade();
            this.info[index] = tag;
        } else  {
            this.info[getTamanho()] = valor;
            tamanho++;
        }
    }

    public int buscar(Tag valor) {
        for (int i = 0; i < getTamanho(); i++) {
            if (Objects.equals(this.info[i].getTag(), valor.getTag())) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(Tag valor) {
        int valorIdx = buscar(valor);
        for (int i = valorIdx + 1; i < tamanho; i++) {
            info[i - 1] = info[i];
        }
        tamanho--;
    }

    public Tag obterElemento(int posicao) {
        if (posicao >= 0 && (posicao < getTamanho())) {
            return info[posicao];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean estaVazia() {
        return getTamanho() == 0;
    }

    public void liberar() {
        this.info = new Tag[10];
        this.tamanho = 0;
    }

    @Override
    public String toString() {
        String valorConcatenado = "";
        for (int i = 0; i < getTamanho(); i++) {
            if (i > 0) {
                valorConcatenado += ", ";
            }
            valorConcatenado += info[i].getTag();
        }

        return valorConcatenado;
    }

}
