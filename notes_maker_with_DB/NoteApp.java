import java.util.Scanner;
import java.util.InputMismatchException;

public class NoteApp{
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        UserHandler handle = new UserHandler();
        boolean close = true;
        int select;

        while(close){
            try{
                System.out.println("1. login");
                System.out.println("2. register");
                System.out.println("3. Exit");
                System.out.print("Select Option : ");
                select = sc.nextInt();
                System.out.println();
                switch(select){
                    case 1 :
                        handle.login();
                        break;
                    case 2 :
                        handle.register();
                        break;
                    case 3 :
                        close = false;
                        break;
                    default :
                        System.out.println("Enter valid option");
                }
                System.out.println();
            }
            catch(InputMismatchException e){
                System.out.println("Error, Enter numeric value");
                System.out.println();
                sc.nextLine();
            }
        }
        sc.close();
    }
}