package scribble.kavzor.com.application.general.pojo.model;

import com.google.gson.annotations.SerializedName;

import scribble.kavzor.com.application.general.constant.NoteConstant;

/**
 * Created by root on 2017-03-09.
 */
public class Note {

    /**
     * Backend: Representated as a unique ID
     * Used by the backend models to identify the note
     */
    @SerializedName("noteId")
    private int noteId;


    /**
     * Frontend: Representated as a title
     * Used by the user to identify the note
     */
    @SerializedName("title")
    private String title;

    /**
     * The actual content of the note
     */
    @SerializedName("text")
    private String text;

    /**
     * The status of the note
     * NoteConstant: STATUS_DONE, STATUS_PENDING
     */
    @SerializedName("status")
    private NoteConstant status;

    public Note(String title, String text, NoteConstant status){
        this.title = title;
        this.text = text;
        this.status = status;
    }

    public Note(String title, String text, NoteConstant status, int noteId){
        this(title, text, status);
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NoteConstant getStatus() {
        return status;
    }

    public void setStatus(NoteConstant status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }
}
