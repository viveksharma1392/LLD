package exception;

import java.time.LocalDateTime;

public class SynonymException extends RuntimeException{

    public static final String signature="My Synonym app";
    public String exceptionString;
    public SynonymException(String exceptionString){
        super(exceptionString);
        exceptionString = signature+"\n"+exceptionString;
        LocalDateTime localDateTime = LocalDateTime.now();
        exceptionString+="\n"+"Timestamp:: "+localDateTime.toString();
        this.exceptionString = exceptionString;
    }
    public SynonymException(String exceptionString, Exception e){
        super(exceptionString,e);
        exceptionString = signature+"\n"+exceptionString;
        LocalDateTime localDateTime = LocalDateTime.now();
        exceptionString+="\n"+"Timestamp:: "+localDateTime.toString();
        this.exceptionString = exceptionString;
    }
}
