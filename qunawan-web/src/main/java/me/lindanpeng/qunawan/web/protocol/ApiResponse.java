package me.lindanpeng.qunawan.web.protocol;

public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

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

    public void setCodeMsg(CodeMsg codeMsg) {
        this.code=codeMsg.getCode();
        this.message=codeMsg.getMessage();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static<T> ApiResponse<T> SUCCESS(T data){
        ApiResponse<T> apiResponse=new ApiResponse<T>();
        apiResponse.setCodeMsg(CodeMsg.SUCCESS);
        apiResponse.setData(data);
        return apiResponse;
    }
    public static<T> ApiResponse<T> ERROR(CodeMsg cm) {
        ApiResponse<T> apiResponse=new ApiResponse<T>();
        apiResponse.setCodeMsg(cm);
        return apiResponse;
    }
}
