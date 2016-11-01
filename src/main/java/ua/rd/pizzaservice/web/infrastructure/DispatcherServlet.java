package ua.rd.pizzaservice.web.infrastructure;

import ua.rd.pizzaservice.web.HelloController;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI(); // отрежем конечную часть ControllerName /...
        String controllerName = getControllerName(url);

        MyController controller = new HelloController(); //getController(controllerName);

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


}
