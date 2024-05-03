package trabalho;

import pilha.PilhaLista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HTMLReader {

    public ListaEstatica validateHtmlFile(String url) {
        ListaEstatica listaEstatica = new ListaEstatica();
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            String line;
            PilhaLista<Tag> pilhaLista = new PilhaLista<>();
            while ((line = br.readLine()) != null) {
                HTMLValidator.readHtmlLine(line, listaEstatica, pilhaLista);
            }
            // TODO: Rever essa parte, deixei assim pois se a pilha não está vazia significa que o arquivo não é válido e não mostra nada.
            if (!pilhaLista.estaVazia()) {
                listaEstatica.liberar();
            }
            System.out.println(pilhaLista.estaVazia());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaEstatica;
    }
}
