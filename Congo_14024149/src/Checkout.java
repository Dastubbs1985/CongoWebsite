/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Servlet created and used for the purpose of creating a new user,
 *  adding the user to the correct database table, adding the order 
 *  to the correct database table and then printing the information 
 *  on screen.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
//		New user is created
		CustomerBean newUser = new CustomerBean();
		newUser.setPrefix(request.getParameter("prefix"));
		newUser.setFirst_Name(request.getParameter("first_name"));
		newUser.setLast_Name(request.getParameter("surname"));
		newUser.setHouse_number(Integer.parseInt(request
				.getParameter("house_number")));
		newUser.setAddress1(request.getParameter("address1"));
		newUser.setAddress2(request.getParameter("address2"));
		newUser.setAddress3(request.getParameter("address3"));
		newUser.setTown(request.getParameter("town"));
		newUser.setCounty(request.getParameter("county"));
		newUser.setPostCode(request.getParameter("p_code"));

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

		ArrayList<AlbumBean> buyAlbum = Cconnect.CurrentOrder(albumArray);
//		New customer information is sent to the database with the auto-generated userID being returned
		int customerId = Cconnect.addUser(newUser);
//		This section is used to create random order numbers
		Random rand = new Random();
		int orderNumber = rand.nextInt(100000) + 1;
		String docType = "<!DOCTYPE HTML>";
//		New user data is printed to the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"300\" height=\"200\"></a></div>");
		out.println("<H2>Delivery Address</H2>");
		out.println("<p>");
		out.println("<br> You User ID is: " + customerId + "<br>");
		out.println("Your details have been stored as follows:<br><br>");
		out.println(newUser.getPrefix() + " ");
		out.println(newUser.getFirst_Name() + " ");
		out.println(newUser.getLast_Name() + "<br>");
		out.println(newUser.getHouse_number() + " ");
		out.println(newUser.getAddress1() + "<br>");
		out.println(newUser.getAddress2() + "<br>");
		out.println(newUser.getAddress3() + "<br>");
		out.println(newUser.getTown() + "<br>");
		out.println(newUser.getCounty() + "<br>");
		out.println(newUser.getPostCode() + "<br>");

		out.println("</p>");
//		If there is something within the album array then print out the table
		if ((buyAlbum != null) && (!buyAlbum.isEmpty())) {
			out.println("<H2>Order Details</H2>");
			float totalCost = Pre_Checkout.runningCost;
			out.print("<p>Order Total = \u00A3");
//			To ensure a two decimal place format I have used the printf function.
			out.printf("%.2f",totalCost);
			out.println("</p>");
			out.println("<div class=\"Table\"><table><tr><td>Album Title</td><td>Artist Name</td><td>Price</td></tr>");
			// print out table rows one for each row returned in rs1
			for (AlbumBean p : buyAlbum) {
				out.println("<tr><td> " + p.getAlbumTitle() + "</td>");
				out.println("<td> " + p.getArtistName() + "</td>");
				out.println("<td> \u00A3" + p.getPrice() + "</td></tr>");
			}
			

			out.println("</table></div>");
//			Running cost is reset to allow for the next order to be processed
			Pre_Checkout.runningCost = 0;
//			Order is added to the database along with the unique UserID, unique order number and total cost of the order.
			Cconnect.addOrder(albumArray, customerId, orderNumber, totalCost);
//			Current order array list is reset ready for the next order to process.
			albumArray.clear();
			session.setAttribute("currentOrder", albumArray);
			out.println("<div id = \"servLinks\"><br><br><a href = \"index.html\">Let's Go Home</a>");

			out.println("</body></html>");

		} else {
//			Otherwise print the following statement
			out.println("<h3>Nothing in order......</h3>");
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