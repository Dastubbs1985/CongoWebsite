/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	18/12/2015
 *
 *  Servlet designed and used for displaying the contents of the basket
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pre_Checkout")
public class Pre_Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static float runningCost;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		ArrayList<String> albumArray;
		if (session.isNew()) {
			albumArray = new ArrayList<String>();
			session.setAttribute("currentOrder", albumArray);
		} else {
			albumArray = (ArrayList<String>) session
					.getAttribute("currentOrder");
		}

		MusicDAO Cconnect = new MusicDAO();
		ArrayList<AlbumBean> addAlbum = Cconnect.CurrentOrder(albumArray);
		String albumTitle = null;

		String docType = "<!DOCTYPE HTML>";
//		Output on the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"275\" height=\"200\"></a></div>");
		if ((addAlbum != null) && (!addAlbum.isEmpty())) {
			out.println("<h5>Current Total = \u00A3");
			out.printf("%.2f", runningCost);
			out.println("</h5>");
			out.println("<div class=\"Table\"><table><tr><td>Album Title</td><td>Artist Name</td><td>Price</td><td>Remove From Order</td></tr>");
			// print out table rows one for each row returned in rs1
			for (AlbumBean p : addAlbum) {
				String artistName = p.getArtistName();
				albumTitle = p.getAlbumTitle();
				out.println("<tr><td> " + albumTitle + "</td>");
				out.println("<td> " + artistName + "</td>");
				out.println("<td> \u00A3" + p.getPrice() + "</td>");
				out.print("<td> <form action=\"remove\" method=\"get\">"
						+ "<input type=\"hidden\" name=\"name\" value=\""
						+ albumTitle
						+ "\">"
						+ "<input type=\"hidden\" name=\"artistName\" value=\""
						+ artistName
						+ "\">"
						+ "<input class=\"myButton\" type=\"submit\" value=\"Remove\" >"
						+ "</form></td>");

			}

			out.println("</table></div>");

			out.print("<td> <form action=\"checkout.html\" method=\"get\">"
					+ "<input class=\"myButton\" type=\"submit\" value=\"Proceed To Checkout\" >"
					+ "</form></td>"
					+ "<br><br><INPUT class=\"myButton\" Type=\"button\" VALUE=\"Go Back To Albums\" onClick=\"history.go(-3);return true;\">");
			out.println("<div id = \"servLinks\"><br><br><a href = \"index.html\">Let's Go Home</a>");

			out.println("</body></html>");

		} else {
//			Output if the current order has no albums in it
			out.println("<h5>Current Total = \u00A3" + runningCost + "</h5>");
			out.println("<h3>Basket is empty......</h3>");
			out.println("<div id = \"servLinks\"><br><br><a href = \"index.html\">Let's Go Home</a></div>");

			out.println("</body></html>");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Allows for either Get or Post methods of the forms.
		doGet(request, response);
	}

}
