package trabalho;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HTMLReader {

    public ListaEstatica validaArquivoHtml(String url) throws IOException {
        ListaEstatica listaEstatica = new ListaEstatica();
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        PilhaLista pilhaLista = new PilhaLista();
        while ((line = br.readLine()) != null) {
            HTMLValidator.readHtmlLine(line, listaEstatica, pilhaLista);
        }
        if (!pilhaLista.estaVazia()) {
            throw new FaltaTagFinalException("Falta tag final para alguma tag ai... Tags faltando: " + formataTagsFinais(pilhaLista) );
        }
        listaEstatica.ordenarAlfabeticamente();

        return listaEstatica;
    }

    private String formataTagsFinais(PilhaLista pilha) {
        // pegar cada item da lista, transformar em tag final, concatenar na lista

        String formatado = new String();
        boolean primeiro = true;

        while (!pilha.estaVazia()) {
            // buscar o último item e converter essa tag pra final
            Tag tag = pilha.pop();

            if (primeiro) {
                primeiro = false;
            } else {
                formatado += ", ";
            }
            formatado += tag.getTagFinal();

        }
        return formatado;
    }
}
