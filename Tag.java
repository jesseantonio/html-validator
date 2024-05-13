package trabalho;

public class Tag {

    public int getQuantidade() {
        return quantidade;
    }

    private int quantidade;

    private String valor;

    private String tag;

    public String getTag() {
        return tag;
    }

    public Tag(String tag) {
        this.tag = tag;
        quantidade = 1;
        this.valor = setTagValue(tag);
    }

    public void aumentaQuantidade() {
        this.quantidade++;
    }


    public static String setTagValue(String tag) {
        String startValue;
        if (tag.contains("/")) {
            startValue = "/";
        } else {
            startValue = "<";
        }
        int start = tag.indexOf(startValue) + 1;
        int end = tag.indexOf(">");

        String tagValue = tag.substring(start, end).trim().toLowerCase();
        String space = " ";
        if (tagValue.contains(space)) {
            return tagValue.split(space)[0];
        }

        return tagValue;

    }

    public String getValor() {
        return valor;
    }

    public String getTagFinal() {
        return "</" + this.valor + ">";
    }

    @Override
    public String toString() {
        return this.tag;
    }
}
