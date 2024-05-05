package DTOs;

public class Response {
    private ResponseStatus status;
    private String Error;


    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setResponseStatus(ResponseStatus status) {
        this.status = status;
    }
}
