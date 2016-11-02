package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Serhii_Mykhliuk
 */
public class SimpleURLHandlerMapping implements HandlerMapping, ApplicationContextAware {
    private ApplicationContext webContext;

//    public SimpleURLHandlerMapping(ConfigurableApplicationContext webContext) {
//        this.webContext = webContext;
//    }

    @Override
    public MyController getController(HttpServletRequest request) {
        // http://localhost:8081/s-pizza-service/servlet/hello
        String url = request.getRequestURI(); // отрежем конечную часть ControllerName /...
        String controllerName = getControllerName(url);
        System.out.println("controllerName: " + controllerName);
        //return  webContext.getBean(controllerName, MyController.class)
        return  (MyController) webContext.getBean(controllerName);

    }


    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.webContext = applicationContext;
    }
}
