import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NoteHandler {

    private Scanner sc = new Scanner(System.in);
    private HashMap<String, ArrayList<Note>> notes = new HashMap<>();

    public void addNote(String userName) {
        System.out.print("Enter Title : ");
        String title = sc.nextLine();
        System.out.print("Enter content : ");
        String content = sc.nextLine();
        System.out.println();
        notes.putIfAbsent(userName, new ArrayList<>());
        ArrayList<Note> singleUserNotes = notes.get(userName);
        Note note = new Note();
        note.setNote(title, content);
        singleUserNotes.add(note);
    }

    public void displayNote(String userName) {

        notes.putIfAbsent(userName, new ArrayList<>());
        ArrayList<Note> userNotes = notes.get(userName);

        if (userNotes.isEmpty()) {
            System.out.println("You Don't have any Note");
        }
        else {
            for (Note note : userNotes) {
                System.out.println("Title : " + note.getTitle() + "\n" + "Content : " + note.getContent());
                System.out.println();
            }
        }
    }

    public void deleteNote(String userName){
        notes.putIfAbsent(userName, new ArrayList<>());
        ArrayList<Note> userNotes = notes.get(userName);
        byte flag = 0;
        System.out.print("Enter title of the note to delete: ");
        String delete = sc.nextLine();
        for(Note note : userNotes){
            if(note.getTitle().equals(delete)){
                userNotes.remove(note);
                System.out.println("note deleted");
                flag=1;
                break;
            }
        }
        if(flag == 0){
            System.out.println("note not found");
        }
    }

    public void editNote(String userName){
        notes.putIfAbsent(userName, new ArrayList<>());
        ArrayList<Note> userNotes = notes.get(userName);
        byte flag = 0;
        System.out.print("Enter title of the note to edit: ");
        String edit = sc.nextLine();
        for(Note note : userNotes){
            if(note.getTitle().equals(edit)){
                System.out.print("Enter new Title: ");
                String nTitle = sc.nextLine();
                System.out.print("Enter new Content: ");
                String ncontent = sc.nextLine();
                note.setNote(nTitle,ncontent);
                System.out.println("note edited");
                flag=1;
                break;
            }
        }
        if(flag == 0){
            System.out.println("note not found");
        }
    }

    public void searchNote(String userName){
        if(notes.containsKey(userName)){
            ArrayList<Note> userNotes = notes.get(userName);
            byte flag = 0;
            System.out.print("Enter key to search: ");
            String key = sc.nextLine();
            for(Note note : userNotes){
                if(note.getTitle().contains(key) || note.getContent().contains(key)){
                    System.out.println("Title : " + note.getTitle() + "\n" + "Content : " + note.getContent());
                    flag=1;
                }
            }
            if(flag == 0){
                System.out.println("note not found");
            }
        }
        else{
            System.out.println("yet you haven't created any note");
        }
    }
}

