package cn.ac.panlab.backend.model;

public class Result {

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(String message, Object data) {
        Result res = new Result();
        res.setCode(200);
        res.setMessage(message);
        res.setData(data);
        return res;
    }
    public static Result success(String message) {
        Result res = new Result();
        res.setCode(200);
        res.setMessage(message);
        return res;
    }
    public static Result success() {
        Result res = new Result();
        res.setCode(200);
        return res;
    }

    public static Result failure(String message) {
        Result res = new Result();
        res.setCode(400);
        res.setMessage(message);
        return res;
    }

}
