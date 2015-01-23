package web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import database.DiskDAO;
/**
 * Class that initialize servlet context.
 * @author timokhin
 *
 */
public class ShopContextListener implements ServletContextListener{

	ServletContext context;
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}
	
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		context = contextEvent.getServletContext();
		try {
			DiskDAO dao = new DiskDAO();
			List<String> types = dao.getTypes();
			List<String> genres = dao.getGenres();
			context.setAttribute("types", types);
			context.setAttribute("genres", genres);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
