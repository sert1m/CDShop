package web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CDDisk;
import database.DiskDAO;
/**
 * Servlet that saves bought items in the session
 * @author timokhin
 *
 */
@SuppressWarnings("serial")
public class BuyServlet extends HttpServlet{
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setHeader("Content-Type", "text/xml; charset=UTF8");
			DiskDAO dao = new DiskDAO();
			//1. Get selected items
			String select[] = req.getParameterValues("disksToBuy");
			//2. If there is no selected attributes, ignore
			if (select != null && select.length != 0) {
				//3. Get elements that have been already saved
				HttpSession session = req.getSession();
				@SuppressWarnings("unchecked")
				List<CDDisk> inCart = (List<CDDisk>) session.getAttribute("inCart");
				if (inCart == null)
					inCart = new LinkedList<CDDisk>();
				//4. Add elements id in the list
				for (String id : select) {
					inCart.add(dao.getDisk(Integer.valueOf(id)));
				}
				//5. Put the list in the cart
				session.setAttribute("inCart", inCart);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
