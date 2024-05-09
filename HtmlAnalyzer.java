package trabalho;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        JFrame frame = new JFrame("HTML Tag Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 550);

        JPanel topPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(1, 20);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        JButton analyzeButton = new JButton("Analisar");

        topPanel.add(textScrollPane, BorderLayout.CENTER);
        topPanel.add(analyzeButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Tag", "Número de Ocorrências"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        bottomPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new BorderLayout());
        JTextArea largeTextArea = new JTextArea(5, 20);
        JScrollPane largeTextScrollPane = new JScrollPane(largeTextArea);

        middlePanel.add(largeTextScrollPane, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                largeTextArea.setText("");

                String text = textArea.getText();
                HTMLReader reader = new HTMLReader();
                try {
                    ListaEstatica lista = reader.validaArquivoHtml(text);
                    for (int i = 0; i < lista.getTamanho(); i++) {
                        Object[] rowData = {lista.getInfo()[i].getValue(), lista.getInfo()[i].getQuantidade()};
                        tableModel.addRow(rowData);
                    }
                    largeTextArea.append("O arquivo está bem formatado");
                } catch (Exception exception) {
                    largeTextArea.append(exception.getMessage());
                }
                table.revalidate();
                table.repaint();
            }
        });

        frame.setVisible(true);
    }
}
