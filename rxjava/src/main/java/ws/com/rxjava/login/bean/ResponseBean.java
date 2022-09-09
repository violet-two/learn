package ws.com.rxjava.login.bean;

public class ResponseBean {
    private SuccessBean data;
    private int code;
    private String message;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public SuccessBean getData() {
        return data;
    }

    public void setData(SuccessBean data) {
        this.data = data;
    }

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

    public ResponseBean() {
    }

    public ResponseBean(SuccessBean data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
