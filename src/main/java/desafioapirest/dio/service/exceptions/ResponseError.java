package desafioapirest.dio.service.exceptions;

import java.util.Date;

public class ResponseError {
    private Date timeStamp;
    private String status;
    private int statusCode;
    private String error;
    private String path;

    public ResponseError(String status, int statusCode, String error, String path) {
        this.timeStamp = new Date();
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
