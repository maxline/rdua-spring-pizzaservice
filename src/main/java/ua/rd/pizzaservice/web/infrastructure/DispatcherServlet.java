package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Serhii_Mykhliuk
 */
@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private ConfigurableApplicationContext webContext;
    ConfigurableApplicationContext[] applicationContexts;

    @Override
    public void init() {
        //вернет строку контекстов через пробел
        String contextsLocations = getServletContext().getInitParameter("contextConfigLocation");
        String[] contexts = contextsLocations.split(" ");
        //хардкод
        //хотим чтобы наследовались, чтобы закрывать
        applicationContexts = new ConfigurableApplicationContext[contexts.length];

        for (int i = 0; i < applicationContexts.length; i++) {
            ConfigurableApplicationContext context;
            if (i == 0) {
                context = new ClassPathXmlApplicationContext(contexts[i]);

            } else {
                context = new ClassPathXmlApplicationContext(
                        new String[]{contexts[i]},
                        applicationContexts[i - 1]);
            }
            applicationContexts[i] = context;
            System.out.println("context[i]: " + context);
        }

        String webContextConfigLocation = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(
                new String[]{webContextConfigLocation},
                applicationContexts[applicationContexts.length - 1]);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // http://localhost:8081/s-pizza-service/servlet/hello
        String url = request.getRequestURI(); // отрежем конечную часть ControllerName /...
        String controllerName = getControllerName(url);
        System.out.println("controllerName: " + controllerName);

        MyController controller = (MyController) webContext.getBean(controllerName); //getController(controllerName);

        if (controller != null) {
            controller.handleRequest(request, response);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("Hello from servlet!" + controllerName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void destroy() {
        webContext.close();
        for (int i = applicationContexts.length - 1; i >= 0; i--) {
            applicationContexts[i].close();
        }
    }

}
