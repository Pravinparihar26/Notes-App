import java.util.InputMismatchException;
import java.util.Scanner;

class User {

    private Scanner sc = new Scanner(System.in);
    private NoteHandler handle = new NoteHandler();

    public void logedIn(String userName) {
        boolean close = true;
        int select;
        while (close) {
            try {
                System.out.println("1. add note");
                System.out.println("2. display note");
                System.out.println("3. delete note");
                System.out.println("4. edit note");
                System.out.println("5. search note");
                System.out.println("6. log out");
                System.out.print("Select Option : ");
                select = sc.nextInt();
                System.out.println();
                switch (select) {
                    case 1:
                        handle.addNote(userName);
                        break;
                    case 2:
                        handle.displayNote(userName);
                        break;
                    case 3:
                        handle.deleteNote(userName);
                        break;
                    case 4:
                        handle.editNote(userName);
                        break;
                    case 5:
                        handle.searchNote(userName);
                        break;
                    case 6:
                        close = false;
                        break;
                    default:
                        System.out.println("Enter valid option");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Error, Enter numeric value");
                System.out.println();
                sc.nextLine();
            }
            System.out.println();
        }
    }
}