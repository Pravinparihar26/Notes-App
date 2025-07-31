package com.example.notesapp.contoller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.notesapp.DTO.NoteDTO;
import com.example.notesapp.service.NotesService;

@RestController
@RequestMapping("/note")
class NoteController {

    @Autowired
    private NotesService serveNote;

    @PostMapping("/add")
    public String add(@RequestBody NoteDTO note){
        return serveNote.addNote(note.getUserName(),note.getTitle(),note.getContent());
    }

    @GetMapping("/display")
    public ArrayList<NoteDTO> display(@RequestParam String username){
        return serveNote.displayNote(username);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String username,@RequestParam int id){
        return serveNote.deleteNote(username,id);
    }

    @PutMapping("/update/{id}")
    public String edit(@PathVariable int id, @RequestBody NoteDTO note){
        return serveNote.editNote(id,note.getUserName(),note.getTitle(),note.getContent());
    }

    @GetMapping("/search")
    public ArrayList<NoteDTO> search(@RequestParam String username,@RequestParam String key){
        return serveNote.searchNote(username, key);
    }
}
