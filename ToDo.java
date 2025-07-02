import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


class task {
    String description;
    String time;
    boolean isDone;

    public task(String description, String time) {
        this.description = description;
        this.time = time;
        this.isDone = false;
    }
}


public class ToDo{
   public static Scanner scan = new Scanner(System.in);
   public static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
   public static LinkedHashMap<Integer, task> to_do = new LinkedHashMap<>();
   public static void main(String[] args) {
      System.out.println("Welcome to The To-Do app");
      welcome();
   }


   //complete
   static void welcome(){
      // navigation description
      System.out.println("Select your comand choice: \n 1. Add a todo \n 2. View list \n 3.  Exit");
      int choice = scan.nextInt();
      scan.nextLine();

      //navigation logic
      int x = 1;
      while(true){
         if(choice == 1){
            AddToDo();
            break;
         }else if(choice == 2){
            viewlist();
            break;
         }else if(choice == 3){
            System.out.println("Goodbye!");
            break;
         }else{
            System.out.println("invalid command");
         }
         choice = scan.nextInt();
      }
   }

   static void AddToDo(){

      //accepting to-do details
      System.out.println("Enter your description");
      String desc = scan.nextLine();

      //date of succession of to-do
      System.out.println("Enter time due: Please use HH:mm (e.g., 09:45)");
      String due = scan.nextLine();
      try{
         LocalTime time = LocalTime.parse(due, format);
         System.out.println("You entered time: " + time);
      }catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:mm (e.g., 09:45)");
      }

      //storing values
      int index = to_do.size(); // assign a unique index
      to_do.put(index, new task(desc, due));
      welcome();
   }

   static void viewlist(){
      // list the to-do's available
      System.out.println("\n");
      System.out.println("Available list");
      for (Map.Entry<Integer, task> entry : to_do.entrySet()) {
        int index = entry.getKey();
        task task = entry.getValue();
        System.out.println((index + 1) + ". " + task.description + (task.isDone ? " [Done]" : ""));
    }

      // allows the selection of a to-do
      System.out.println("Select the list interested: ");
      int selection = scan.nextInt();
      selection--;
      scan.nextLine(); // consume newline

      //excepting actions to the selected to-do
      task selectedTask = to_do.get(selection);
      System.out.println("Description: " + selectedTask.description);
      System.out.println("Time: " + selectedTask.time);
      System.out.println("Is your task complete? (yes/no)");

      //selecting interested action
      System.out.println("Is your task complete ?");
      String action = scan.nextLine();
      boolean yes = action.contains("es");

      //condition to check if task complete
      if (yes) {
         selectedTask.isDone = true;
         System.out.println("Task marked as complete.");
      }
      
   }

   
}