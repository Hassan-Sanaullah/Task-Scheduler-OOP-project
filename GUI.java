import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class GUI {
    private JFrame frame, subFrame;
    private JButton RegularTask, ProjectTask, MeetingTask, ViewTasks;
    private JLabel mainTitle;
    TaskManager taskManager = new TaskManager();

    Scanner scanner = new Scanner(System.in);

    String name, details, date, time;
    
    public static void setButtonColor(JButton TempButton) {
        TempButton.setBackground(Color.decode("#845BB3"));
        TempButton.setForeground(Color.decode("#E5EAF5"));
    }
    public static void setLabelColor(JLabel TempLabel) {
        TempLabel.setBackground(Color.decode("#845BB3"));
        TempLabel.setForeground(Color.decode("#E5EAF5"));
    }

    
    
    public GUI() {
        frame = new JFrame("Task Scheduler");
        frame.setBounds(200, 100, 500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#121212"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        taskManager.loadTasksFromFile("data.txt");
        
        mainTitle = new JLabel("Task Scheduler");
        mainTitle.setBounds(90, 70, 500, 80);
        setLabelColor(mainTitle);
        mainTitle.setFont(new Font("Arial", Font.PLAIN, 45));

        RegularTask = new JButton("Add Regular Task");
        RegularTask.setBounds(150, 170, 200, 30);
        setButtonColor(RegularTask);
        RegularTask.addActionListener(e -> RegularTaskFunction());

        ProjectTask = new JButton("Add Project Task");
        ProjectTask.setBounds(150, 210, 200, 30);
        setButtonColor(ProjectTask);
        ProjectTask.addActionListener(e -> ProjectTaskFunction());

        MeetingTask = new JButton("Add Meeting Task");
        MeetingTask.setBounds(150, 250, 200, 30);
        setButtonColor(MeetingTask);
        MeetingTask.addActionListener(e -> MeetingTaskFunction());

        ViewTasks = new JButton("View Tasks");
        ViewTasks.setBounds(150, 290, 200, 30);
        setButtonColor(ViewTasks);
        ViewTasks.addActionListener(e -> viewTasksFunction());

        frame.add(mainTitle);
        frame.add(RegularTask);
        frame.add(ProjectTask);
        frame.add(MeetingTask);
        frame.add(ViewTasks);

    }

    public void addingRegularTask(JTextField TempTF1, JTextField TempTF2, JTextField TempTF3, JTextField TempTF4) {
        name = TempTF1.getText();
        details = TempTF2.getText();
        date = TempTF3.getText();
        time = TempTF4.getText();
        
        taskManager.addTask(new RegularTask(name, details, date, time));
        JOptionPane.showMessageDialog(null, "Task successfully added");

        taskManager.saveTasksToFile("data.txt");
        subFrame.dispose();
    }

    public void RegularTaskFunction() {
        subFrame = new JFrame("Regular Task");
        subFrame.setBounds(200, 100, 500, 500);
        subFrame.setLayout(null);
        subFrame.setVisible(true);
        subFrame.getContentPane().setBackground(Color.decode("#121212"));
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel L1 = new JLabel("Task name: ");
        L1.setBounds(150, 100, 400, 20);
        setLabelColor(L1);

        JTextField TF1 = new JTextField(10);
        TF1.setBounds(150, 130, 200, 30);

        JLabel L2 = new JLabel("Task details: ");
        L2.setBounds(150, 160, 400, 20);
        setLabelColor(L2);

        JTextField TF2 = new JTextField(10);
        TF2.setBounds(150, 190, 200, 30);

        JLabel L3 = new JLabel("Date: ");
        L3.setBounds(150, 220, 400, 20);
        setLabelColor(L3);

        JTextField TF3 = new JTextField(10);
        TF3.setBounds(150, 250, 200, 30);

        JLabel L4 = new JLabel("Time: ");
        L4.setBounds(150, 280, 400, 20);
        setLabelColor(L4);

        JTextField TF4 = new JTextField(10);
        TF4.setBounds(150, 310, 200, 30);

        JButton B1 = new JButton("Add Task");
        setButtonColor(B1);
        B1.setBounds(200, 410, 100, 20);
        B1.addActionListener(e -> addingRegularTask(TF1, TF2, TF3, TF4));

        subFrame.add(L1);
        subFrame.add(TF1);
        subFrame.add(L2);
        subFrame.add(TF2);
        subFrame.add(L3);
        subFrame.add(TF3);
        subFrame.add(L4);
        subFrame.add(TF4);
        subFrame.add(B1);

    }

    public void addingProjectTask(JTextField TempTF1, JTextField TempTF2, JTextField TempTF3, JTextField TempTF4, JTextField TempTF5) {
        name = TempTF1.getText();
        details = TempTF2.getText();
        
        String membersInput = TempTF3.getText();
        String[] membersArray = membersInput.split(",");
        date = TempTF4.getText();
        time = TempTF5.getText();

        taskManager.addTask(new ProjectTask(name, details, date, time, membersArray));
        JOptionPane.showMessageDialog(null, "Task successfully added");

        taskManager.saveTasksToFile("data.txt");
        subFrame.dispose();

    }

    public void ProjectTaskFunction() {
        subFrame = new JFrame("Project Task");
        subFrame.setBounds(200, 100, 500, 500);
        subFrame.setLayout(null);
        subFrame.setVisible(true);
        subFrame.getContentPane().setBackground(Color.decode("#121212"));
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel L1 = new JLabel("Project name: ");
        L1.setBounds(150, 100, 400, 20);
        setLabelColor(L1);

        JTextField TF1 = new JTextField(10);
        TF1.setBounds(150, 130, 200, 30);

        JLabel L2 = new JLabel("Project details: ");
        L2.setBounds(150, 160, 400, 20);
        setLabelColor(L2);

        JTextField TF2 = new JTextField(10);
        TF2.setBounds(150, 190, 200, 30);

        JLabel L3 = new JLabel("Project Members: ");
        L3.setBounds(150, 220, 400, 20);
        setLabelColor(L3);

        JTextField TF3 = new JTextField(10);
        TF3.setBounds(150, 250, 200, 30);

        JLabel L4 = new JLabel("Date: ");
        L4.setBounds(150, 280, 400, 20);
        setLabelColor(L4);

        JTextField TF4 = new JTextField(10);
        TF4.setBounds(150, 310, 200, 30);

        JLabel L5 = new JLabel("Time: ");
        L5.setBounds(150, 340, 400, 20);
        setLabelColor(L5);

        JTextField TF5 = new JTextField(10);
        TF5.setBounds(150, 370, 200, 30);

        JButton B1 = new JButton("Add Task");
        setButtonColor(B1);
        B1.setBounds(200, 410, 100, 20);
        B1.addActionListener(e -> addingProjectTask(TF1, TF2, TF3, TF4, TF5));

        subFrame.add(L1);
        subFrame.add(TF1);
        subFrame.add(L2);
        subFrame.add(TF2);
        subFrame.add(L3);
        subFrame.add(TF3);
        subFrame.add(L4);
        subFrame.add(TF4);
        subFrame.add(L5);
        subFrame.add(TF5);
        subFrame.add(B1);
    }

    public void addingMeetingTask(JTextField TempTF1, JTextField TempTF2, JTextField TempTF3,JTextField TempTF4, JTextField TempTF5, JTextField TempTF6){
        name = TempTF1.getText();
        details = TempTF2.getText();

        String membersInput = TempTF3.getText();
        String[] membersArray = membersInput.split(",");

        String location = TempTF4.getText();
        date = TempTF5.getText();
        time = TempTF6.getText();

        taskManager.addTask(new MeetingTask(name, details, date, time, location, membersArray));
       
        taskManager.saveTasksToFile("data.txt");
        JOptionPane.showMessageDialog(null, "Task successfully added"); 
        subFrame.dispose();

    }

    public void MeetingTaskFunction() {
        subFrame = new JFrame("Meeting Task");
        subFrame.setBounds(200, 100, 500, 550);
        subFrame.setLayout(null);
        subFrame.setVisible(true);
        subFrame.getContentPane().setBackground(Color.decode("#121212"));
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel L1 = new JLabel("Meeting name: ");
        L1.setBounds(150, 100, 400, 20);
        setLabelColor(L1);

        JTextField TF1 = new JTextField(10);
        TF1.setBounds(150, 130, 200, 30);

        JLabel L2 = new JLabel("Meeting details: ");
        L2.setBounds(150, 160, 400, 20);
        setLabelColor(L2);

        JTextField TF2 = new JTextField(10);
        TF2.setBounds(150, 190, 200, 30);

        JLabel L3 = new JLabel("Participants: ");
        L3.setBounds(150, 220, 400, 20);
        setLabelColor(L3);

        JTextField TF3 = new JTextField(10);
        TF3.setBounds(150, 250, 200, 30);

        JLabel L4 = new JLabel("Location: ");
        L4.setBounds(150, 280, 400, 20);
        setLabelColor(L4);

        JTextField TF4 = new JTextField(10);
        TF4.setBounds(150, 310, 200, 30);

        JLabel L5 = new JLabel("Date: ");
        L5.setBounds(150, 340, 400, 20);
        setLabelColor(L5);

        JTextField TF5 = new JTextField(10);
        TF5.setBounds(150, 370, 200, 30);

        JLabel L6 = new JLabel("Time: ");
        L6.setBounds(150, 400, 400, 20);
        setLabelColor(L6);

        JTextField TF6 = new JTextField(10);
        TF6.setBounds(150, 430, 200, 30);

        JButton B1 = new JButton("Add Task");
        setButtonColor(B1);
        B1.setBounds(200, 470, 100, 20);
        B1.addActionListener(e -> addingMeetingTask(TF1, TF2, TF3, TF4, TF5, TF6));

        subFrame.add(L1);
        subFrame.add(TF1);
        subFrame.add(L2);
        subFrame.add(TF2);
        subFrame.add(L3);
        subFrame.add(TF3);
        subFrame.add(L4);
        subFrame.add(TF4);
        subFrame.add(L5);
        subFrame.add(TF5);
        subFrame.add(L6);
        subFrame.add(TF6);
        subFrame.add(B1);

    }

    private JTable jt;
    private JTextArea rowDetailsTextArea;
    private String[][] data;
    //private JFrame subFrame;

    public void viewTasksFunction() {
        subFrame = new JFrame("View Tasks");
        
        taskManager.loadTasksFromFile("data.txt");

        //gets tasks data from taskManager
        // fullData = taskManager.getTaskTable();
        data = taskManager.getTaskTabledata();
        String column[] = { "Type", "Name", "Date", "Status" };

        jt = new JTable(data, column);
        jt.setCellSelectionEnabled(true);
        ListSelectionModel select = jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt.getSelectionModel().addListSelectionListener(e -> rowSelectionChanged());

        JLabel label = new JLabel("Tasks");
        label.setHorizontalAlignment(JLabel.CENTER);

        rowDetailsTextArea = new JTextArea();
        rowDetailsTextArea.setEditable(false);

        // select.addListSelectionListener(new ListSelectionListener() {
        //     public void valueChanged(ListSelectionEvent e) {
        //         rowSelectionChanged();
        //     }
        // });

        JScrollPane sp = new JScrollPane(jt);

        // JButton addButton = new JButton("Add");
        JButton statusButton = new JButton("Change Status");
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedTask());
        statusButton.addActionListener(e -> changeStatus());

        JPanel buttonPanel = new JPanel();
        // buttonPanel.add(addButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(deleteButton);

        subFrame.setLayout(new BorderLayout());
        subFrame.add(label, BorderLayout.NORTH);
        subFrame.add(sp, BorderLayout.CENTER);
        subFrame.add(buttonPanel, BorderLayout.SOUTH);
        subFrame.add(rowDetailsTextArea, BorderLayout.EAST);

        subFrame.setSize(800, 500);
        subFrame.setLocationRelativeTo(null);
        subFrame.setVisible(true);
    }
    
    public void deleteSelectedTask() {
        int selectedRow = jt.getSelectedRow();

        if (selectedRow != -1) {
           
            taskManager.removeTask(selectedRow);

            
            subFrame.dispose();
            taskManager.saveTasksToFile("data.txt");
            viewTasksFunction();

            
            rowDetailsTextArea.setText("Task Information:\n");
            
        } else {
            JOptionPane.showMessageDialog(null, "Please select a task to delete.");
        }
    }
    
    public void changeStatus() {
        int selectedRow = jt.getSelectedRow();

        if (selectedRow != -1) {

            taskManager.markTaskAsCompleted(selectedRow);


            subFrame.dispose();
            taskManager.saveTasksToFile("data.txt");
            viewTasksFunction();

            
            rowDetailsTextArea.setText("Task Information:\n");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a task to change status for.");
        }
    }

    public void rowSelectionChanged() {
        int selectedRow = jt.getSelectedRow();
        if (selectedRow != -1) {
            if (data[selectedRow][0].equals("Regular")) {
                rowDetailsTextArea.setText("Task Information:\n"
                        + "Type: " + jt.getValueAt(selectedRow, 0) + "\n"
                        + "Name: " + jt.getValueAt(selectedRow, 1) + "\n"
                        + "Date: " + jt.getValueAt(selectedRow, 2) + "\n"
                        + "Time: " + data[selectedRow][4] + "\n"
                        + "Details: " + data[selectedRow][5] + "\n");
            }
            else if (data[selectedRow][0].equals("Project")) {
                rowDetailsTextArea.setText("Task Information:\n"
                        + "Type: " + jt.getValueAt(selectedRow, 0) + "\n"
                        + "Name: " + jt.getValueAt(selectedRow, 1) + "\n"
                        + "Date: " + jt.getValueAt(selectedRow, 2) + "\n"
                        + "Time: " + data[selectedRow][4] + "\n"
                        + "Details: " + data[selectedRow][5] + "\n"
                        + "Members: " + "\n");

                for (int i = 6; i < data[selectedRow].length; i++) {
                    rowDetailsTextArea.append(data[selectedRow][i] + "\n");
                }
            }
            else if (data[selectedRow][0].equals("Meeting")) {
                rowDetailsTextArea.setText("Task Information:\n"
                        + "Type: " + jt.getValueAt(selectedRow, 0) + "\n"
                        + "Name: " + jt.getValueAt(selectedRow, 1) + "\n"
                        + "Date: " + jt.getValueAt(selectedRow, 2) + "\n"
                        + "Time: " + data[selectedRow][4] + "\n"
                        + "Details: " + data[selectedRow][5] + "\n"
                        + "Location: " + data[selectedRow][6] + "\n"
                        + "Participants: " + "\n");

                for (int i = 7; i < data[selectedRow].length; i++) {
                    rowDetailsTextArea.append(data[selectedRow][i] + "\n");
                }
            } 
        }
    }
    
    public static void main(String[] args) {
        new GUI();
    }
}
