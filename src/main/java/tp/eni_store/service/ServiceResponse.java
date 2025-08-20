package tp.eni_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;

import java.util.Locale;

public class ServiceResponse <T>{
    public int code;
    public String message;
    public T data;


    public ServiceResponse(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
