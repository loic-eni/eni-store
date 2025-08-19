package tp.eni_store.service;

public class ServiceResponse <T>{
    public T data;
    public int code;

    public ServiceResponse(int code, T data){
        this.code = code;
        this.data = data;
    }
}
