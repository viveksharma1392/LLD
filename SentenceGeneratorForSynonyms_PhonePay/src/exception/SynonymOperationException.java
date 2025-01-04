package exception;

// Custom Exception for Invalid Synonym Operations
public class SynonymOperationException extends SynonymException {

    private ErrorCode errorCode;
    public SynonymOperationException(String message) {
        super(message);
    }
    public SynonymOperationException(String message, Exception exception) {
        super(message, exception);
    }

    public SynonymOperationException(String message, Exception exception, ErrorCode errorCode) {
        super(message, exception);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }



}
