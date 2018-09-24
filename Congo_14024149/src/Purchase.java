/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	17/12/2015
 *
 *  Servlet designed and used for the purposes of displaying the 
 *  album information of the album added to the order.
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

@WebServlet("/purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Purchase() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String rec_id_str = request.getParameter("rec_id");
		int rec_id = Integer.parseInt(rec_id_str);
		HttpSession session = request.getSession();
		ArrayList<String> albumArray;
		if (session.isNew()) {
			// new order(session) so create a new ArrayList
			albumArray = new ArrayList<String>();
			session.setAttribute("currentOrder", albumArray); // add array to
																// session
		} else {
			// already ordered something, get current order
			albumArray = (ArrayList<String>) session
					.getAttribute("currentOrder");
		}
		albumArray.add(rec_id_str);

		MusicDAO Cconnect = new MusicDAO();
		ArrayList<AlbumBean> addAlbum = Cconnect.AddToOrder(rec_id);

		String docType = "<!DOCTYPE HTML>";
//		Output on the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"275\" height=\"200\"></a></div>");
		out.println("<title>Album added</title>");
		out.println("<body>");
		out.println("<h5>The Following Album Has Been Added To Your Order<h5>");
		if (addAlbum.isEmpty()) {
			out.println("<h3>No albums added.....</h3>");
			out.println("<form action = \"pre_Checkout\" method \"get\">"
					+ "<input class=\"myButton\" type = \"submit\" value = \"Go To Order\">"
					+ "</form>");
			out.println("<div id = \"servLinks\"><br><br><a href = \"index.html\">Let's Go Home</a></div>");
		} else {
			out.println("<div class=\"Table\"><table><tr><td>Album Title</td><td>Artist Name</td><td>Price</td></tr>");
			for (AlbumBean p : addAlbum) {
				out.println("<tr><td> " + p.getAlbumTitle() + "</td>");
				out.println("<td> " + p.getArtistName() + "</td>");
				out.println("<td> \u00A3" + p.getPrice() + "</td></tr>");
				Pre_Checkout.runningCost += p.getPrice();
			}

		}
		out.println("</table></div>");
		out.println("<form action = \"pre_Checkout\" method \"get\">"
				+ "<input class=\"myButton\" type = \"submit\" value = \"Go To Order\">"
				+ "</form>");
		out.println("<div id = \"servLinks\"><br><br><a href=\"pre_Checkout\">See What You've Ordered</a>");
		out.println("<br><br><a href = \"index.html\">Let's Go Home</a></div>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Allows for either Get or Post methods of the forms.
		doGet(request, response);
	}

}
