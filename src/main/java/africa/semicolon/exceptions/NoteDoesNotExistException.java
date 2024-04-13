package africa.semicolon.exceptions;

public class NoteDoesNotExistException extends NoteAppException {
    public NoteDoesNotExistException(String message){
        super(message);
    }
}
