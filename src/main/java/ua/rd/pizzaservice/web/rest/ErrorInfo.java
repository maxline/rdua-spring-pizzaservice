package ua.rd.pizzaservice.web.rest;

/**
 * @author Serhii_Mykhliuk
 */
public class ErrorInfo {

    public final String url;
    public final String ex;
    public final String msg;


    public ErrorInfo(String url, Exception ex, String msg) {
        //сколько хотим полей с сообщениями столько и вставляем
        this.url = url;
        this.ex = ex.getLocalizedMessage();
        this.msg = msg;
    }
}
