//package qmu_pack_v3_0;
//
//import java.awt.PageAttributes.MediaType;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//
//import org.glassfish.jersey.client.ClientConfig;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.DefaultClientConfig;
//
//import javax.ws.rs.core.UriBuilder;
//import org.apache.http.client.ClientProtocolException;
//import org.glassfish.jersey.*;
//
//public class JSON_parser {
//
//	static String response = "";
//
//	public static void main(String args[]) {
//		JSONParser parser = new JSONParser();
//		JSONArray array = new JSONArray();
//		
//		BufferedReader in = null;
//		try {
//			
//			DefaultClientConfig config = new DefaultClientConfig();
//			  Client client = Client.create(config);
//			  WebResource service = client.resource(UriBuilder.fromUri("http://www.omdbapi.com/?t=Don+2&plot=full&r=json").build());
//			  // getting XML data
//			  System.out.println(service.accept().get(String.class));
//			  // getting JSON data
////			  System.out.println(service. path('restPath').path('resourcePath').accept(MediaType.APPLICATION_XML).get(String.class));
//			  
//			  
//
////			URL url = new URL("http://www.omdbapi.com/?t=Don+2&plot=full&r=json");
////			URLConnection urlc;
////			urlc = url.openConnection();
////			urlc.addRequestProperty("user-agent", "Firefox");
////			
////			
////					
////			in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
////			String inputLine;
////			while ((inputLine = in.readLine()) != null) {
////				response = inputLine;
////				System.out.println(response);
//			}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		
//		} //catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
////	finally {
////			try {
////				in.close();
////			} catch (IOException e) {
////				// dcl.LOGGER.severe(e.getMessage());
////				e.printStackTrace();
////			}
////		}
//	}
//
//}
