

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  HashMap<String, String> hmap = new HashMap<String, String>();

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("<form method = \"post\">\r\n" + 
				"  key:<br>\r\n" + 
				"  <input type=\"text\" name=\"key\"><br>\r\n" + 
				"  value:<br>\r\n" + 
				"  <input type=\"text\" name=\"value\">\r\n" + 
				 "<input type=\"submit\" value=\"Submit\">" +
				"</form>\r\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		hmap.put(request.getParameter("key"), request.getParameter("value"));
		for (HashMap.Entry<String, String> entry : hmap.entrySet()) {
			response.getWriter().println(entry.getKey() + ":" + entry.getValue() + "<br>");
		}
	}

}
