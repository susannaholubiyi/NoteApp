package africa.semicolon.exceptions;

public class NoteNameAlreadyExistsException extends NoteAppException{
    public NoteNameAlreadyExistsException(String message){
        super(message);
    }
}
