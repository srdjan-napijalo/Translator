package service;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Translator { 

    @WebMethod
    public abstract String addWord(String eng, String srb, String ger);
    
    
    @WebMethod
    public  abstract String translate(String toTrans, String srcLang, String destLang);   
}
