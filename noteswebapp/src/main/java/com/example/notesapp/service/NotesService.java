package com.example.notesapp.service;

import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import com.example.notesapp.DBConnection;
import com.example.notesapp.DTO.NoteDTO;

@Service
public class NotesService {

    Connection con = DBConnection.connectMySQL();

    public String addNote(String userName, String title, String content) {
        try {
            PreparedStatement addNote = con.prepareStatement("INSERT INTO notes (title,content,username) values(?,?,?)");
            addNote.setString(1, title);
            addNote.setString(2, content);
            addNote.setString(3, userName);
            addNote.executeUpdate();
            return "Note added";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add note " + e.getMessage();
        }
    }

    public ArrayList<NoteDTO> displayNote(String userName) {
        ArrayList<NoteDTO> noteList = new ArrayList<>();
        try {
            PreparedStatement displayNotes = con.prepareStatement("SELECT ID,title,content FROM notes WHERE username=?");
            displayNotes.setString(1, userName);
            ResultSet notes = displayNotes.executeQuery();
            while (notes.next()) {
                int id = notes.getInt("ID");
                String title = notes.getString("title");
                String content = notes.getString("content");
                NoteDTO note = new NoteDTO();
                note.setid(id);
                note.setTitle(title);
                note.setContent(content);
                noteList.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noteList;
    }

    public String deleteNote(String userName, int delete) {
        try {
            PreparedStatement deleteNotes = con.prepareStatement("DELETE FROM notes WHERE ID=? AND username=?");
            deleteNotes.setInt(1, delete);
            deleteNotes.setString(2, userName);
            deleteNotes.executeUpdate();
            return "Note deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete note " + e.getMessage();
        }
    }

    public String editNote(int edit, String userName, String nTitle, String nContent) {
        try {
            PreparedStatement searchNotes = con.prepareStatement("SELECT 1 FROM notes WHERE ID=? AND username=?");
            searchNotes.setInt(1, edit);
            searchNotes.setString(2, userName);
            ResultSet result = searchNotes.executeQuery();
            if (result.next()) {
                PreparedStatement editNotes = con.prepareStatement("UPDATE notes SET title=?, content=? WHERE ID=? AND username=?");
                editNotes.setString(1, nTitle);
                editNotes.setString(2, nContent);
                editNotes.setInt(3, edit);
                editNotes.setString(4, userName);
                editNotes.executeUpdate();
                return "Note Edited";
            } else {
                return "Note not exist";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to edit note " + e.getMessage();
        }
    }

    public ArrayList<NoteDTO> searchNote(String userName, String key) {
        ArrayList<NoteDTO> noteList = new ArrayList<>();
        try {
            PreparedStatement searchNotes = con.prepareStatement("SELECT ID,title,content FROM notes WHERE username=? AND (title LIKE ? OR content LIKE ?)");
            searchNotes.setString(1, userName);
            searchNotes.setString(2, "%" + key + "%");
            searchNotes.setString(3, "%" + key + "%");
            ResultSet notes = searchNotes.executeQuery();

            while (notes.next()) {
                int id = notes.getInt("ID");
                String title = notes.getString("title");
                String content = notes.getString("content");
                NoteDTO note = new NoteDTO();
                note.setid(id);
                note.setTitle(title);
                note.setContent(content);
                noteList.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noteList;
    }
}
