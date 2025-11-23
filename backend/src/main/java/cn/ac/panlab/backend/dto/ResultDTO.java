package cn.ac.panlab.backend.dto;

public class ResultDTO {

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

    public static ResultDTO success(String message, Object data) {
        ResultDTO res = new ResultDTO();
        res.setCode(200);
        res.setMessage(message);
        res.setData(data);
        return res;
    }
    public static ResultDTO success(String message) {
        ResultDTO res = new ResultDTO();
        res.setCode(200);
        res.setMessage(message);
        return res;
    }
    public static ResultDTO success() {
        ResultDTO res = new ResultDTO();
        res.setCode(200);
        res.setMessage("Operation Success");
        return res;
    }

    public static ResultDTO failure(String message) {
        ResultDTO res = new ResultDTO();
        res.setCode(400);
        res.setMessage(message);
        return res;
    }

}
