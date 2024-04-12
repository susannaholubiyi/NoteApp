package africa.semicolon.exceptions;

public class UsernameAlreadyExistsException extends NoteAppException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
