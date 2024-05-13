package trabalho;

import java.util.Objects;

public class ListaEstatica {
    private Tag[] info;
    private int tamanho;

    public Tag[] getInfo() {
        return info;
    }

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
        int index = buscar(valor.getValue());
        if (index != -1) {
            Tag tag = obterElemento(index);
            tag.aumentaQuantidade();
            this.info[index] = tag;
        } else {
            this.info[getTamanho()] = valor;
            tamanho++;
        }
    }

    public int buscar(String valor) {
        for (int i = 0; i < getTamanho(); i++) {
            if (Objects.equals(this.info[i].getValue(), valor)) {
                return i;
            }
        }
        return -1;
    }

    public Tag obterElemento(int posicao) {
        if (posicao >= 0 && (posicao < getTamanho())) {
            return info[posicao];
        } else {
            throw new IndexOutOfBoundsException();
        }
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

    public void ordenarAlfabeticamente() {
        for (int i = 0; i < getTamanho() - 1; i++) {
            for (int j = 0; j < getTamanho() - i - 1; j++) {
                if (info[j].getValor().compareTo(info[j + 1].getValor()) > 0) {
                    Tag temp = info[j];
                    info[j] = info[j + 1];
                    info[j + 1] = temp;
                }
            }
        }
    }
}

