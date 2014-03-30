package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import api.CatalogAPI;
import api.GeoAPI;

public class EventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String latitude = request.getParameter("lat");
		String longitude = request.getParameter("lon");
		String eventType = request.getParameter("event_type");
		
		JSONArray events = getResponse(latitude, longitude, eventType);
		
		response.setContentType("application/json");
		response.getWriter().println(events);
		response.getWriter().close();
	}
	
	/** Determines city from <code>latitude</code> & <code>longitude</code> and returns list of events of type <code>eventType</code> for that location.
	 * @param latitude Latitude of location
	 * @param longitude Longitude of location
	 * @param eventType Type of Event
	 * @return List of events filtered by <code>eventType</code>
	 */
	private static JSONArray getResponse(String latitude, String longitude, String eventType) {
		String city = GeoAPI.getCity(latitude, longitude);		
		return CatalogAPI.getEventList(city, eventType);
	}
	
	/*
	 * Sample Test 
	 */
	public static void main(String[] args) {
		System.out.println(getResponse("40.761075", "-73.985756", "Theater tickets and Arts tickets"));
    }

}
