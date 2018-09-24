/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	17/12/2015
 *
 *  Servlet created for the purpose of printing the album information
 *  to the screen for the user.
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

/**
 * Servlet implementation class categories
 */
@WebServlet("/albumInfo")
public class AlbumInfoListing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlbumInfoListing() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		MusicDAO Cconnect = new MusicDAO();
		String albumTitle = request.getParameter("name");
		String artistName = request.getParameter("artistName");
		int rec_id = Cconnect.getRec_ID(albumTitle, artistName);
//		Every track is given a number starting from one
		int trackNumber = 1;
		ArrayList<AlbumInfoBean> albumInfo = Cconnect.getAlbumInfo(rec_id);
		String docType = "<!DOCTYPE HTML>";
//		Output on the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"275\" height=\"200\"></a></div>");
		if (albumInfo.isEmpty()) {
			out.println("<h3>No info for that album.....</h3>");
			out.println("<div id = \"servLinks\"><br><br><a href = \"index.html\"> let's go home</a></div>");
		} else {
			out.print("<h5>Artist = " + artistName + "<br>");
			out.println("	Album = " + albumTitle + "</h5>");
			out.println("<div class=\"Table\"><table><tr><td>Track Number</td><td>Track Title</td><td>Duration</td></tr>");
			for (AlbumInfoBean p : albumInfo) {
				out.println("<tr><td> " + trackNumber + "</td>");
				out.println("<td> " + p.getAlbumTitle() + "</td>");
				out.println("<td> " + p.getDuration() + "</td>");

				out.println("</tr>");
				trackNumber++;
			}
			out.println("</table></div>");
			out.print("<form action=\"purchase\" method=\"get\">"
					+ "<input type=\"hidden\" name=\"rec_id\" value=\""
					+ rec_id
					+ "\">"
					+ "<input type=\"hidden\" name=\"name\" value=\""
					+ albumTitle
					+ "\">"
					+ "<input type=\"hidden\" name=\"artistName\" value=\""
					+ artistName
					+ "\">"
					+ "<input class=\"myButton\" type=\"submit\" value=\"Buy Now\" >"
					+ "</form>");
			out.println("<div id = \"servLinks\"><br><br><a href=\"pre_Checkout\">See What You've Ordered</a>");
			out.println("<br><br><a href = \"index.html\"> Let's Go Home</a></div>");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Allows for either Get or Post methods of the forms.
		doGet(request, response);
	}
}
