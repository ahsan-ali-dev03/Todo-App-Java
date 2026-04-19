import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TodoGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("To-Do App");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<JCheckBox> model = new DefaultListModel<>();
        JList<JCheckBox> list = new JList<>(model);
        list.setCellRenderer((list1, value, index, isSelected, cellHasFocus) -> value);

        JTextField field = new JTextField(15);
        JButton addBtn = new JButton("Add Task");
        JButton deleteBtn = new JButton("Delete Task");

        // 📂 Load tasks from file
        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                model.addElement(new JCheckBox(line));
            }
        } catch (Exception e) {
            // file may not exist first time
        }

        // ➕ Add task
        addBtn.addActionListener(e -> {
            String task = field.getText();
            if (!task.isEmpty()) {
                model.addElement(new JCheckBox(task));
                field.setText("");
            }
        });

        // ❌ Delete task
        deleteBtn.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index != -1) {
                model.remove(index);
            }
        });

        // ✔️ Toggle checkbox
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                JCheckBox checkbox = model.getElementAt(index);
                checkbox.setSelected(!checkbox.isSelected());
                list.repaint();
            }
        });

        // 💾 Save tasks on close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"))) {
                    for (int i = 0; i < model.size(); i++) {
                        bw.write(model.get(i).getText());
                        bw.newLine();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(field);
        panel.add(addBtn);
        panel.add(deleteBtn);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);

        frame.setVisible(true);
    }
}