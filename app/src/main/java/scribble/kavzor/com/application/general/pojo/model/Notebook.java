package scribble.kavzor.com.application.general.pojo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import scribble.kavzor.com.application.general.exception.NoteNotFoundException;

/**
 * Created by root on 2017-03-08.
 */

public class Notebook {

    @SerializedName("notes")
    private ArrayList<Note> notes = new ArrayList<>();


    /*public void setNotes(ArrayList<Note> notes) throws AccessLevelException{
        this.notes = notes;
    }*/


    public ArrayList<Note> getNotes(){
        return this.notes;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNote(int index){
        notes.remove(index);
    }

    public void updateNoteContent(Note note, int index){
        notes.get(index).setTitle(note.getTitle());
        notes.get(index).setText(note.getText());
        notes.get(index).setStatus(note.getStatus());
    }

    public void updateNoteTitle(String title, int noteId){
        try{
            notes.get(getIndex(noteId)).setTitle(title);
        }
        catch (NoteNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public Note getNote(int index){
        return notes.get(index);
    }


    private int getIndex(int noteId) throws NoteNotFoundException{
        return getIndex0(noteId, 0);
    }

    private int getIndex0(int noteId, int index) throws NoteNotFoundException{
        if(notes.get(index).getNoteId() == noteId){
            return index;
        }
        else if(notes.size() < index){
            throw new NoteNotFoundException("Note with id " + noteId + " was not found");
        }
        else {
            return getIndex0(noteId, ++index);
        }
    }
}
