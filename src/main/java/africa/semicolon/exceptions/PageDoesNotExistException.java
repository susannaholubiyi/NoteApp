package africa.semicolon.exceptions;

public class PageDoesNotExistException extends NoteAppException{
    public PageDoesNotExistException(String message){
        super(message);
    }
}
