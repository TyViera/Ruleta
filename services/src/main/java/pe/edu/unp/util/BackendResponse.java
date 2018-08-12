package pe.edu.unp.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BackendResponse {

    public static BackendResponse createFromInfo(Object info) {
        return new BackendResponse(Constants.SUCCESS, null, info);
    }

    public static BackendResponse createFromErrorMessage(String message) {
        return new BackendResponse(Constants.ERROR, message);
    }

    public static BackendResponse createFromSuccessMessage(String message) {
        return new BackendResponse(Constants.SUCCESS, message);
    }

    @JsonInclude(Include.NON_NULL)
    private String status;

    @JsonInclude(Include.NON_NULL)
    private String message;

    @JsonInclude(Include.NON_NULL)
    private Object info;

    public BackendResponse() {
        this(null, null);
    }

    public BackendResponse(String status, String message) {
        this(status, message, null);
    }

    public BackendResponse(String status, String message, Object info) {
        this.status = status;
        this.message = message;
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

}
