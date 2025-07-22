import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class NoteHandler {

    private Scanner sc = new Scanner(System.in);
    private Connection con = DBConnection.connectMySQL();

    public void addNote(String userName) {
        System.out.print("Enter Title : ");
        String title = sc.nextLine();
        System.out.print("Enter content : ");
        String content = sc.nextLine();
        System.out.println();
        try{
            PreparedStatement addNote = con.prepareStatement("INSERT INTO notes values(?,?,?)");
            addNote.setString(1,title);
            addNote.setString(2,content);
            addNote.setString(3,userName);
            addNote.executeUpdate();
            System.out.println("Note added");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void displayNote(String userName) {
        try{
            PreparedStatement displayNotes = con.prepareStatement("SELECT title,content FROM notes WHERE username=?");
            displayNotes.setString(1,userName);
            ResultSet notes = displayNotes.executeQuery();
            while(notes.next()){
                String title = notes.getString("title");
                String content = notes.getString("content");
                System.out.println(title);
                System.out.println(content);
                System.out.println();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteNote(String userName) {
        try{
            System.out.print("Enter title of the note to delete: ");
            String delete = sc.nextLine();
            PreparedStatement deleteNotes = con.prepareStatement("DELETE FROM notes WHERE title=? AND username=?");
            deleteNotes.setString(1,delete);
            deleteNotes.setString(2,userName);
            deleteNotes.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editNote(String userName) {
        try{
            System.out.print("Enter title of the note to edit: ");
            String edit = sc.nextLine();

            PreparedStatement searchNotes = con.prepareStatement("SELECT 1 FROM notes WHERE title=? AND username=?");
            searchNotes.setString(1,edit);
            searchNotes.setString(2,userName);
            ResultSet result = searchNotes.executeQuery();
            if (result.next()) {
                System.out.print("Enter new Title: ");
                String nTitle = sc.nextLine();
                System.out.print("Enter new Content: ");
                String nContent = sc.nextLine();
                PreparedStatement editNotes = con.prepareStatement("UPDATE notes SET title=?, content=? WHERE title=? AND username=?");
                editNotes.setString(1,nTitle);
                editNotes.setString(2,nContent);
                editNotes.setString(3,edit);
                editNotes.setString(4,userName);
                editNotes.executeUpdate();
                System.out.println("Note Edited");
            }
            else{
                System.out.println("Note not exist");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void searchNote(String userName) {
        try{
            System.out.print("Enter key to search: ");
            String key = sc.nextLine();

            PreparedStatement searchNotes = con.prepareStatement("SELECT title,content FROM notes WHERE username=? AND (title LIKE ? OR content LIKE ?)");
            searchNotes.setString(1,userName);
            searchNotes.setString(2, "%" + key + "%");
            searchNotes.setString(3, "%" + key + "%");
            ResultSet notes = searchNotes.executeQuery();

            while (notes.next()) {
                String title = notes.getString("title");
                String content = notes.getString("content");
                System.out.println(title);
                System.out.println(content);
                System.out.println();
            }
            else{
                System.out.println("Note not exist");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
