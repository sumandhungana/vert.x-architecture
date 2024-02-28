package demo.architecture.application;

import io.vertx.core.json.JsonObject;

/**
 * @author suman dhungana
 */
public class RestResponse <T> {
    private final int status;
    private final String message;
    private final T data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public RestResponse(int code, String message, T data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }

    public static  <T> JsonObject ok(){
        return JsonObject.mapFrom(new RestResponse<>(0,"SUCCESS",null));
    }

    static <T> JsonObject ok(int code,String message){
        return JsonObject.mapFrom (new RestResponse<>(0,"SUCCESS",null));
    }

    public static  <T> JsonObject ok(T data){
        return JsonObject.mapFrom(new RestResponse<T>(0,"SUCCESS",data));

    }
    // Error
    static JsonObject error(){
        return JsonObject.mapFrom(new RestResponse<>(-1,"FAILED",null));
    }

    static JsonObject error(int code,String message){
        return JsonObject.mapFrom(new RestResponse<>(-1,message,null));
    }

    public static  JsonObject error(String message){
        return JsonObject.mapFrom(new RestResponse<>(-1,message,null));
    }

    static <T> JsonObject error(T data){
        return JsonObject.mapFrom(new RestResponse<>(-1,"FAILED",data));
    }
}
