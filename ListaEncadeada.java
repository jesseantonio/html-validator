package trabalho;


public class ListaEncadeada {

    private NoLista primeiro;

    public ListaEncadeada() {
        this.primeiro = null;
    }

    public NoLista getPrimeiro() {
        return primeiro;
    }

    public void inserir(Tag info) {
        NoLista novoNo = new NoLista();
        novoNo.setInfo(info);
        novoNo.setProximo(primeiro);
        this.primeiro = novoNo;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public NoLista buscar(Tag info) {
        NoLista p = primeiro;

        while (p != null) {
            if (p.getInfo().equals(info)) {
                return p;
            }
            p = p.getProximo();
        }

        return null;
    }


    public void retirar(Tag info) {
        NoLista anterior = this.primeiro;
        NoLista p = this.primeiro;

        while (p != null && !p.getInfo().equals(info)) {
            anterior = p;
            p = p.getProximo();
        }

        if (p != null) {
            if (p == primeiro) {
                primeiro = primeiro.getProximo();
            } else {
                anterior.setProximo(p.getProximo());
            }
        }
    }

    @Override
    public String toString() {
        NoLista p = primeiro;
        String valorConcatenado = "";
        while (p != null) {
            if (p != primeiro) {
                valorConcatenado += ", ";
            }
            valorConcatenado += p.getInfo().toString();
            p = p.getProximo();
        }
        return valorConcatenado;
    }


}
