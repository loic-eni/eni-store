package tp.eni_store.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleHelper {
    @Autowired
    MessageSource messageSource;

    public String i18n(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
