import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.awt.font.TextAttribute;
import java.time.LocalDate;  

public class taskManager extends JFrame implements ActionListener {

    LocalDate currentDate = LocalDate.now();

    JComboBox<String> output;
    JComboBox<String> number;
    JPanel taskContainer;
    JPanel taskListPanel;
    JSpinner dateSpinner;

    public taskManager() {  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UniTask Manager");
        this.setSize(480, 720);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        this.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("UniTask Manager");
        title.setForeground(Color.GREEN);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title);

        JLabel description = new JLabel("Stay organized with UniTask Manager!");
        description.setForeground(Color.WHITE);
        description.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(description);

        // Container for dropdowns and date picker
        JPanel addContainer = new JPanel();
        addContainer.setBorder(new EmptyBorder(10, 42, 10, 42));
        addContainer.setLayout(new GridBagLayout());
        addContainer.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 10);

        JLabel addLabel = new JLabel("Add a Task");
        addLabel.setForeground(Color.WHITE);
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addContainer.add(addLabel, gbc);

        // First dropdown (Task Type)
        JLabel category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.PLAIN, 16));
        category.setForeground(Color.WHITE);
        output = new JComboBox<>(new String[]{"Activity", "Quiz", "Assignment"});
        output.addActionListener(this);
        Dimension comboBoxSize = new Dimension(130, 30);
        output.setPreferredSize(comboBoxSize);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(category, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(output, gbc);

        // Second dropdown (Task Number)
        JLabel numberOfCategory = new JLabel("Number of Category");
        numberOfCategory.setForeground(Color.WHITE);
        numberOfCategory.setFont(new Font("Arial", Font.PLAIN, 16));
        number = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        number.addActionListener(this);
        number.setPreferredSize(comboBoxSize);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(numberOfCategory, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(number, gbc);

        // Date picker
        JLabel deadlineTxt = new JLabel("Deadline");
        deadlineTxt.setForeground(Color.WHITE);
        deadlineTxt.setFont(new Font("Arial", Font.PLAIN, 16));
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setPreferredSize(comboBoxSize);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(deadlineTxt, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(dateSpinner, gbc);

        // Button to retrieve selected values
        JButton submitButton = new JButton("Add Task");
        submitButton.addActionListener(e -> addTask());

        submitButton.setBackground(Color.GREEN);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        addContainer.add(submitButton, gbc);

        //JPanel name = new JPanel();
        // Container for task list
        taskContainer = new JPanel();
        taskContainer.setBorder(new EmptyBorder(20, 20, 30, 20));
        taskContainer.setPreferredSize(new Dimension(380, 280));
        taskContainer.setBackground(Color.DARK_GRAY);
        taskContainer.setLayout(new BorderLayout());

        JLabel taskLabel = new JLabel("Task List");
        taskLabel.setForeground(Color.WHITE);
        taskLabel.setHorizontalAlignment(SwingConstants.CENTER);
        taskLabel.setBorder(new EmptyBorder(0, 0, 10, 10));
        taskContainer.add(taskLabel, BorderLayout.NORTH);

        // Panel to hold tasks (BoxLayout for vertical stacking)
        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(Color.DARK_GRAY);
        taskContainer.add(new JScrollPane(taskListPanel), BorderLayout.CENTER); // scrolling

        this.add(addContainer);
        this.add(taskContainer);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // method for adding task
    private void addTask() {
        String selectedOutput = (String) output.getSelectedItem();
        String selectedNumber = (String) number.getSelectedItem();
        String selectedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateSpinner.getValue());

        String FormattedCurrentDate = currentDate.toString();
       
        if(selectedDate.equals(FormattedCurrentDate)){
            JOptionPane.showMessageDialog( null,"Reminder: \n \n" + selectedOutput + " " + selectedNumber + " deadline is today");
        }

        // Panel to hold task label and buttons
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.X_AXIS));  // Align components in a row
        taskPanel.setBackground(Color.DARK_GRAY);
        taskPanel.setMaximumSize(new Dimension(320, 35)); // Keeps height consistent
        taskPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JLabel newTaskLabel = new JLabel(selectedOutput + " " + selectedNumber + " " + selectedDate);
        newTaskLabel.setForeground(Color.WHITE);
        newTaskLabel.setPreferredSize(new Dimension(180, 25));  // Fixed size for consistency

        JOptionPane.showMessageDialog(null,"Task Added successfully");

        JButton markDone = new JButton("Done");
        markDone.setBackground(Color.GREEN);
        markDone.setForeground(Color.WHITE);
        markDone.setPreferredSize(new Dimension(65, 25));   
        markDone.addActionListener(e -> doneAction(markDone, newTaskLabel, newTaskLabel)); // Pass the label

        JButton del = new JButton("Del");
        del.setBackground(Color.RED);
        del.setForeground(Color.WHITE);
        del.setPreferredSize(new Dimension(65, 25));
        del.addActionListener(e -> deleteAction(taskPanel));

        // Align label to the left and buttons to the right
        taskPanel.add(Box.createHorizontalGlue()); // Pushes components to the edges
        taskPanel.add(newTaskLabel);
        taskPanel.add(Box.createHorizontalGlue()); // Space between label and buttons
        taskPanel.add(markDone);
        taskPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        taskPanel.add(del);
        
        // Add task panel to task list panel
        taskListPanel.add(taskPanel);
        taskListPanel.revalidate(); // Refresh layout and display the task
        taskListPanel.repaint();    // Update UI visually
    }

    private void deleteAction(JPanel taskPanel){
        int confirmDelete = JOptionPane.YES_NO_OPTION;
        int yes_no_box = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this task?", "Delete Task", confirmDelete);

        if(yes_no_box == 0){
            taskListPanel.remove(taskPanel);  // Remove the entire task panel(tasklist and button)
            taskListPanel.revalidate();  // Refresh layout
            taskListPanel.repaint();  // Update UI visually
        }

    }

    private void doneAction(JButton markDone, JLabel taskLabel, JLabel newTaskLabel) {
        Font font = taskLabel.getFont();
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        taskLabel.setFont(font.deriveFont(attributes));
        markDone.setBackground(Color.GRAY);        
        newTaskLabel.setForeground(Color.gray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public static void main(String[] args) {
        new taskManager();
    }
}
