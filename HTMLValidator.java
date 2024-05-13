package trabalho;

public class HTMLValidator {

    private static final String[] SINGLETON_TAGS = {
            "meta", "base", "br", "col", "command", "embed", "hr",
            "img", "input", "link", "param", "source", "!doctype"
    };

    public static void readHtmlLine(String line, ListaEstatica listaEstatica, PilhaLista pilhaLista) {
        // Ignora as linhas em branco
        if (verificaLinhaVazia(line)) return;
        String[] partes = line.split("<");
        // Divide a linha em partes
        for (String parte : partes) {
            if (!parte.contains(">")) {
                continue;
            }
            // Pega somente a tag em si, Ex: <div>
            String valorTag = "<" + parte.substring(0, parte.indexOf(">") + 1);
            Tag tag = new Tag(valorTag);
            // Se não conter / significa que é uma tag de abertura
            if (!valorTag.contains("/")) {
                listaEstatica.inserir(tag);
                if (verificaSingletonTag(tag.getValue())) {
                    continue;
                }
                pilhaLista.push(tag);
            } else {
                /* Caso conter / significa que é uma tag de fechamento e deve fechar com o último item da pilha.
                Se não for o caso, não é um arquivo válido.
                * */
                Tag lastTag = pilhaLista.peek();
                if (tag.getValue().equals(lastTag.getValue())) {
                    pilhaLista.pop();
                } else {
                    throw new TagFinalInesperadaException("Tag final inesperada! Era esperado a tag final para a tag " + tag.getValue() + ", porém foi encontrada a tag " + lastTag.getValue() + ".");
                }
            }
        }
    }

    /**
     * Verifica se a linha esta vazia.
     *
     * @param line - Linha
     * @return true se estiver vaiza, false caso contrário.
     */
    private static boolean verificaLinhaVazia(String line) {
        return line.trim().isEmpty();
    }


    /**
     * Verifica se uma tag é uma singleton tag
     *
     * @param tagName O nome da tag a ser verificada
     * @return true se for uma singleton tag, false caso contrário
     */
    private static boolean verificaSingletonTag(String tagName) {
        String lowerCaseTag = tagName.toLowerCase();
        for (String tag : SINGLETON_TAGS) {
            if (tag.equals(lowerCaseTag)) {
                return true;
            }
        }
        return false;
    }
}
