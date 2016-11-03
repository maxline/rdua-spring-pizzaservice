package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.beans.BeansException;
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

    private ConfigurableApplicationContext[] rootSpringContexts;
    private ConfigurableApplicationContext webContext;

    private String[] getRootContextsNames() {
        String contexts = getServletContext().getInitParameter("contextConfigLocation");
        String[] contextsNames = contexts.split(" ");
        return contextsNames;
    }

    private ConfigurableApplicationContext[] createRootSpringContexts(String[] contexts) throws BeansException {
        ConfigurableApplicationContext[] applicationContexts
                = new ConfigurableApplicationContext[contexts.length];

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
        return applicationContexts;
    }


    private ConfigurableApplicationContext createWebSpringContext(ConfigurableApplicationContext[] rootContexts) throws BeansException {
        String webContextConfigLocation = getInitParameter("contextConfigLocation");
        if (rootContexts.length == 0) {
            return new ClassPathXmlApplicationContext(webContextConfigLocation);
        } else {
            return new ClassPathXmlApplicationContext(
                    new String[]{webContextConfigLocation},
                    rootContexts[rootContexts.length - 1]
            );
        }
    }

    @Override
    public void init() throws ServletException {
        String[] rootContexts = getRootContextsNames();
        rootSpringContexts = createRootSpringContexts(rootContexts);
        webContext = createWebSpringContext(rootSpringContexts);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //out.println("Hello from servlet!" + controllerName);
        out.println("Hello from servlet!");

        //SimpleURLHandlerMapping синглтон, спринг заинжектить не может (не управляет), вопрос как его сюда засунуть
        HandlerMapping handlerMapping = webContext.getBean("handlerMappingStrategy", HandlerMapping.class);
        //new SimpleURLHandlerMapping(webContext);

        MyController controller = handlerMapping.getController(request);
        //(MyController) webContext.getBean(controllerName); //getController(controllerName);

        if (controller != null) {
            controller.handleRequest(request, response);
        }
    }

    @Override
    public void destroy() {
        webContext.close();
        for (int i = rootSpringContexts.length - 1; i >= 0; i--) {
            rootSpringContexts[i].close();
        }
    }
}
