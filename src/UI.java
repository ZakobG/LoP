import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class UI {

    JFrame frame = new JFrame("Pjůpl");
    JPanel tPanel = new JPanel();
    JPanel cPanel = new JPanel();
    JScrollPane scroll;
    JTable dTable;
    JTextField nField = new JTextField();
    JTextField aField = new JTextField();
    JTextField filter = new JTextField();
    JButton addRow = new JButton("Přidat řádek");
    JButton removeRow = new JButton("Odebrat řádek");
    JButton filterRows = new JButton("Filtrovat řádky");

    String[] columns = {"ID", "Jméno", "Věk"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);

    void components() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        filterRows.addActionListener(e -> filterRows(filter.getText()));
        addRow.addActionListener(e -> {
            if (!nField.getText().equals("") && !aField.getText().equals("")) {
                addRow(generateID(), nField.getText(), aField.getText());
                nField.setText("");
                aField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Zadej jméno a věk!");
            }
        });

        removeRow.addActionListener(e -> {
            if (dTable.getSelectedRow() != -1) {
                model.removeRow(dTable.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(null, "Vyber řádek, ty susáku!");
            }
        });
    }

    void loadPanels() {
        scroll = new JScrollPane(tPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(0, 0, 500, 764);
        frame.add(scroll);

        tPanel.setBackground(Color.gray);
        tPanel.setLayout(new BorderLayout());
        tPanel.setBounds(0, 0, 500, 800);
        tPanel.setPreferredSize(new Dimension(500, 800));

        cPanel.setBackground(Color.lightGray);
        cPanel.setLayout(null);
        cPanel.setBounds(500, 0, 300, 800);
        frame.add(cPanel);
    }

    void loadTable() {
        dTable = new JTable(model);
        dTable.setBounds(0, 0, 600, 800);
        dTable.setFillsViewportHeight(true);
        dTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tPanel.add(dTable, BorderLayout.CENTER);
        tPanel.add(dTable.getTableHeader(), BorderLayout.NORTH);
    }

    void loadSidePanel() {
        filter.setBounds(60, 150, 160, 20);
        filterRows.setBounds(60, 180, 160, 20);
        nField.setBounds(60, 410, 160, 20);
        aField.setBounds(60, 440, 160, 20);
        addRow.setBounds(60, 470, 160, 20);
        removeRow.setBounds(60, 500, 160, 20);
        cPanel.add(filter);
        cPanel.add(filterRows);
        cPanel.add(nField);
        cPanel.add(aField);
        cPanel.add(addRow);
        cPanel.add(removeRow);
    }

    void filterRows(String filterPhrase) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) dTable.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(filterPhrase));
        dTable.setRowSorter(sorter);
        refresh();
    }

    void addRow(Integer id, String data1, String data2) {
        model.addRow(new Object[]{id, data1, data2});
    }

    void refresh() {
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }

    int generateID() {
        int id = 1;
        while (!checkID(id)) {
            System.out.println("ID : " + id);
            id++;
        }
        return id;
    }

    boolean checkID(Integer id) {
        boolean match = false;
        for (int i = 0; i < dTable.getRowCount(); i++) {
            if (id == dTable.getValueAt(i, 0)) {
                match = true;
                System.out.println("Shodné id : " + id + " hodnota na řádku " + i + " : " + dTable.getValueAt(i, 0));
            }
        }
        if (match) {
            return false;
        } else {
            return true;
        }
    }

}