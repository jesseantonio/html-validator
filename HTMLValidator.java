package trabalho;

public class HTMLValidator {

    private static final String[] SINGLETON_TAGS = {
            "meta", "base", "br", "col", "command", "embed", "hr",
            "img", "input", "link", "param", "source", "!doctype"
    };

    public static void readHtmlLine(String line, ListaEstatica listaEstatica, PilhaLista pilhaLista) {
        // Ignora as linhas em branco
        if (isLineEmpty(line)) return;
        String[] parts = line.split("<"); // TODO: 04/05/2024 testar com mais cuidado esse cenário, pode ser que haja algum texto com "<" por exemplo.
        // Divide a linha em partes
        for (String part : parts) {
            if (!part.contains(">")) {
                continue;
            }
            // Pega somente a tag em si, Ex: <div>
            String value = "<" + part.substring(0, part.indexOf(">") + 1);
            Tag tag = new Tag(value);
            // Se não conter / significa que é uma tag de abertura
            if (!value.contains("/")) {
                listaEstatica.inserir(tag);
                if (isSingletonTag(tag.getValue())) {
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
                    // TODO: Fazer mensagens customizadas para cada tag com erro.
                    // TODO: Validar se caso não está igual a tag
                    throw new TagFinalInesperadaException("Tag final inexperada! Era experado a tag final para a tag " + tag.getValue() + ", porém foi encontrada a tag " + lastTag.getValue() + ".");
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
    private static boolean isLineEmpty(String line) {
        return line.trim().isEmpty();
    }


    /**
     * Verifica se uma tag é uma singleton tag
     *
     * @param tagName O nome da tag a ser verificada
     * @return true se for uma singleton tag, false caso contrário
     */
    private static boolean isSingletonTag(String tagName) {
        String lowerCaseTag = tagName.toLowerCase();
        for (String tag : SINGLETON_TAGS) {
            if (tag.equals(lowerCaseTag)) {
                return true;
            }
        }
        return false;
    }
}
