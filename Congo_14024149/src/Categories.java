/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Servlet used to produce a drop down menu that is dynamically
 *  populated.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Categories() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Cat = null;
		MusicDAO Tconnect = new MusicDAO();
		ArrayList<String> categories = Tconnect.findAllCategories();
		String docType = "<!DOCTYPE HTML>";
//		Output on the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"275\" height=\"200\"></a></div>");
		out.println("<div id=\"content\">");
		out.println("<form action = \"category\"  method = \"post\">"
				+ "<h4>Please Choose A Category</h4>"
				+ "<br><select name = \"category\" required>");
//		Populates the drop down list with all of the categories
		for (int x = 0; x < categories.size(); x++) {
			Cat = categories.get(x);
			out.println("<OPTION value = \"" + Cat + "\">" + Cat + "</OPTION>");
		}
		out.println("<INPUT class=\"myButton\" TYPE=\"SUBMIT\" NAME=\"submitButton\" Value=\"Take Me To The MUSIC!!!\">");
		out.println("<div id = \"servLinks\"><br><br><a href=\"pre_Checkout\">See What You've Ordered</a>");
		out.println("<br><br><a href = \"index.html\"> Let's Go Home</a></div>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Allows for either Get or Post methods of the forms.
		doGet(request, response);
	}

}
