package murach.email;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.busoness.User;
import murach.data.UserDB;


public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");

        // evitar null
        if (action == null) {
            action = "join";
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";

        }else if(action.equals("eliminar")){
            
            String email = request.getParameter("email");
            
            request.setAttribute("email", email);
            
            url = "/confirmar-eliminacion.jsp";
        } else if(action.equals("aceptar-eliminacion")){
            String email = request.getParameter("email");
            
            int result = UserDB.delete(email);
            
            if(result > 0){
                List<User> users = UserDB.getAllUsers();
                
                request.setAttribute("users", users);
                
               url = "/listado-de-usuarios.jsp";
            }else{
                request.setAttribute("mensaje","Hubo un problema al querer eliminar el usuario de la base de datos");
                request.setAttribute("error",murach.data.Error.descripcion);
                url = "/error.jsp" ; 
            }
            
        }else if (action.equals("listado")) {
            List<User> users = UserDB.getAllUsers();

            request.setAttribute("users", users);

            url = "/listado-de-usuarios.jsp";

        } else if (action.equals("add")) {
            request.setCharacterEncoding("UTF-8");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            System.out.println(lastName);

            User user = new User(firstName, lastName, email);
            int result = UserDB.insert(user);

            if (result > 0) {
                request.setAttribute("mensaje", "El usuario fue dado de alta en la bd");
                request.setAttribute("user", user);
                url = "/thanks.jsp";
            } else {
                request.setAttribute("mensaje", "Hubo un problema al querer guardar la información en la base de datos");
                request.setAttribute("error", murach.data.Error.descripcion);
                url = "/error.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}