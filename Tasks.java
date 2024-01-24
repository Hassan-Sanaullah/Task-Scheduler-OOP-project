
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

abstract class Task {
    protected String name;
    protected String details;
    protected String date;
    protected String time;
    protected String completed;

    public Task(String name, String details, String date, String time) {
        this.name = name;
        this.details = details;
        this.date = date;
        this.time = time;
        this.completed = "Incomplete";
    }

    public String getData(){
        String data = name + "," + date + "," + completed + "," + time + "," + details;
        return data;
    }

    public abstract String getType();
}

class RegularTask extends Task {
    public RegularTask(String name, String details, String date, String time) {
        super(name, details, date, time);
    }

    @Override
    public String getData() {
        String data = "Regular" + "," + name + "," + date + "," + completed + "," + time + "," + details;
        return data;
    }

    public String getType() {
        return "Regular";
    }
}

class ProjectTask extends Task {
    private ArrayList<String> membersList = new ArrayList<>();

    public ProjectTask(String name, String details, String date, String time, String[] members) {
        super(name, details, date, time);

        Collections.addAll(membersList, members);
    }

    @Override
    public String getData() {
        String data = "Project" + "," + name + "," + date + "," + completed + "," + time + "," + details + "," + membersList;
        return data;
    }

    @Override
    public String getType() {
        return "Project";
    }
}

class MeetingTask extends Task {
    private String location;
    private ArrayList<String> participantsList = new ArrayList<>();

    public MeetingTask(String name, String details, String date, String time, String location, String[] members) {
        super(name, details, date, time);
        this.location = location;

        Collections.addAll(participantsList, members);
    }

    @Override
    public String getData() {
        String data = "Meeting" + "," + name + "," + date + "," + completed + "," + time + "," + details + "," + location + ","
                + participantsList;
        return data;
    }

    @Override
    public String getType() {
        return "Meeting";
    }

}

class TaskManager {
    public ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        
        System.out.println("Task added");
    }

    public void markTaskAsCompleted(int index) {
        if (tasks.get(index).completed.equals("Completed")) {
            tasks.get(index).completed = "Incomplete";
        }
        else {            
            tasks.get(index).completed = "Completed";
        }

        
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Task removed");
        } else {
            System.out.println("Invalid task index");
        }
    }

    public String[][] getTaskTabledata() {

        int i = 0;
        String row;
        String data[][] = new String[50][6];

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return data;
        } else {

            for (Task temp : tasks) {

                row = temp.getData();
                data[i] = row.split(",");
                i++;
            }
        }
        return data;
    }
    

    public void saveTasksToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Task task : tasks) {
                writer.println(task.getData());
            }
            System.out.println("Tasks saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String fileName) {
        tasks.clear(); 

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskData = line.split(",");
                

                if (taskData.length >= 5) {
                    String type = taskData[0];
                    String name = taskData[1];
                    String date = taskData[2];
                    String completed = taskData[3];
                    String time = taskData[4];
                    String details = taskData.length > 5 ? taskData[5] : "";

                    Task task;
                    if (type.equals("Regular")) {
                        task = new RegularTask(name, details, date, time);
                    } else if (type.equals("Project")) {
                        // Extract members from the data array
                        String[] members = taskData.length > 6 ? taskData[6].split(",") : new String[0];
                        task = new ProjectTask(name, details, date, time, members);
                    } else if (type.equals("Meeting")) {
                        String location = taskData.length > 6 ? taskData[6] : "";
                        // Extract participants from the data array
                        String[] participants = taskData.length > 7 ? taskData[7].split(",") : new String[0];
                        task = new MeetingTask(name, details, date, time, location, participants);
                    } else {
                        continue; // Skip loading invalid data
                    }

                    task.completed = completed;
                    tasks.add(task);
                }
            }
            System.out.println("Tasks loaded from file: " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }

}

public class Tasks {
    public static void main(String[] args) {
    }
}
