/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	17/12/2015
 *
 *  Servlet created and used to display the full list of albums
 *  available on the database. Albums with a stock count of 
 *  less than 1 have been skipped.
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

@WebServlet("/albums")
public class MusicListing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MusicListing() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		MusicDAO Tconnect = new MusicDAO();
		ArrayList<AlbumBean> albums = Tconnect.findAllAlbums();

		String albumTitle = null;
		String docType = "<!DOCTYPE HTML>";
//		Output on the screen
		out.println(docType);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CongoCSS.css\">");
		out.println("<div id = \"header\"><a href = \"index.html\"><img src = \"images/CongoLogo.png\" width=\"275\" height=\"200\"></a></div>");
		out.println("<div class=\"Table\"><table><tr><td>Artist Name</td><td>Album Title</td><td>Category</td><td>Image Name</td><td>Number Of Tracks</td><td>Price</td></tr>");
		for (AlbumBean p : albums) {
			String artistName = p.getArtistName();
			albumTitle = p.getAlbumTitle();
			out.println("<tr><td> " + artistName + "</td>");
			out.println("<td><div id = \"tableLinks\"> <a href = \"albumInfo?name="
					+ albumTitle
					+ "&artistName="
					+ artistName
					+ "\">"
					+ albumTitle + "</a></div></td>");
			out.println("<td> " + p.getCategory() + "</td>");
			out.println("<td> " + p.getImage_name() + "</td>");
			out.println("<td> " + p.getNum_tracks() + "</td>");
//			\u00A3 used to produce the '£' on the print out on a Mac and Windows based computer.
			out.println("<td> \u00A3" + p.getPrice() + "</td></tr>");

		}
		out.println("</table></div>");
		out.println("<div id = \"servLinks\"><br><br><a href=\"pre_Checkout\">See What You've Ordered</a>");
		out.println("<br><br><a href = \"index.html\"> Let's Go Home</a></div>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Allows for either Get or Post methods of the forms.
		doGet(request, response);
	}
}
