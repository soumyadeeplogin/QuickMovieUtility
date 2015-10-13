package qmu_pack_v3_0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GetRating_JSON{
	
	JProgressBar pB;
	DataClass dcl;
	String response = "";
	JLabel status;
//	List<String> movieName;
//	List<String> movieRating;
//	List<String> movieRefined;
	public GetRating_JSON(DataClass dc, JProgressBar jProgressBar, JLabel label) {
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
//		movieName = moviename;
//		movieRating = new ArrayList<String>();
//		movieRefined = new ArrayList<String>();
		pB = jProgressBar;
		status = label;
		dcl = dc;
//		//System.out.println("\n\n\n================================================================================");
//		//System.out.println("Fetching the ratings. This may take sometime. **INTERNET CONNECTION REQUIRED**");
//		//System.out.println("================================================================================\n\n\n");
	}

	public void entry() {
//		pB = progressBar;
//		pB.setValue(50);
		pB.setMaximum(dcl.size);
		pB.setValue(0);
		processName();
//		for(int i = 0; i < movieName.size(); i++)
//		{
//			//System.out.println("Movie Name :: " + movieName.get(i));
//			//System.out.println("Movie Rating :: " + movieRating.get(i));
//		}
//		obj.movieRefine = movieRefined;
//		return movieRating;
	}
	
	private void fetchData(String name)
	{
		
		BufferedReader in = null;
		
		
			try {
				//			//System.out.print("Trying to fetch rating for :: " + name.replace("+", " "));
				dcl.LOGGER.info("fetchData(" + name + ")");
				URL url = new URL("http://www.omdbapi.com/?t=" + name + "&plot=full&r=json");
				URLConnection urlc;
				urlc = url.openConnection();
				urlc.addRequestProperty("user-agent", "Firefox");
				in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response = inputLine;
					//				//System.out.println(response);
				}
			} catch (IOException e) {
				dcl.LOGGER.severe(e.getMessage());
//				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					dcl.LOGGER.severe(e.getMessage());
//					e.printStackTrace();
				}
			}
		}

	private void processName() // List<String> movieName
	{
		
		dcl.LOGGER.info("processName");
		for (int i = 0; i < dcl.movieName.size(); i++) {
			
			String tempName = dcl.movieName.get(i).toLowerCase();
			
			tempName = new NameFilter().filter(tempName);	
			dcl.LOGGER.info("Trying for :: " + tempName.replace("+", " "));

//			//System.out.println("Movie :: " + tempName);			
//			//System.out.print("Trying to fetch rating for :: " + tempName.replace("+", " "));
			status.setText("Trying for :: " + tempName.replace("+", " "));
			boolean status = true;
			do
			{
				
				fetchData(tempName);
				getRating(i);

//				//System.out.println("pB updated with :: " + (i+1));
				
				if(dcl.movieRating.get(i).equals("N.A") && tempName.contains("+"))
				{
//					String refName = tempName;
					tempName = tempName.replace('+', ' ');
					if(tempName.length() > 0 & tempName.contains(" ")) 
					{
						tempName = tempName.substring(0, (tempName.lastIndexOf(' ')));
						tempName = tempName.replace(' ', '+');
//						//System.out.println("The hyper tweaked Named is :: " + tempName.replace("+", " "));
//						//System.out.print(".");
					}
				}
				else
				{
//					if(!tempName.contains(" "))
//					{
						status = false;
//						//System.out.println("From else if block" + movieRating.get(i).equals("N.A") + !tempName.contains(" ") );
//					}
//						//System.out.println("\nReceived rating ---> " + dcl.movieRating.get(i));
						if(!dcl.movieRating.equals("N.A") || !dcl.movieRating.equals("N/A"))
						{
							dcl.movieRefined.set(i, toTitleCase(tempName.replace('+', ' ')));
						}
//						else
//						{
//							dcl.movieRefined.set(i, dcl.movieName.get(i));
//						}
				}
			}
			while(status);
			
			pB.setValue(i+1);
		}
	}
	
//	private void updateProgressBar(final int i)
//	{
//		SwingUtilities.invokeLater(new Runnable() {
//		    public void run() {
//		    		pB.setValue(i+1);
//		        }
//		    }
//		);
//	}

	private void getLang(int i)
	{
		dcl.LOGGER.info("getLang()");
		String lang = "";
		String res = response.substring((response.indexOf("Language")+10),response.indexOf("Awards")-3);
//		//System.out.println("res :: "  + res);
		if(res.contains("Hindi") & res.contains("India"))
		{
			lang = "Hindi";
		}else if(res.contains("Bengali") & res.contains("India"))
		{
			lang = "Bengali";
		}else if(res.contains("English"))
		{
			lang = "English";
		}
		else {
			lang = "Others";
		}
		
		dcl.movieLang.add(i,lang);
	}
	private void getRating(int i) {
		dcl.LOGGER.info("getRating()");
		String rating = "N.A";
		int res = response.indexOf("Response");
		String check = response.substring(res, res + 15);
		dcl.LOGGER.info("Response :: " + response);
		if (check.equals("Response" + '"' + ':' + '"' + "True")) {
			
			// //System.out.println("Response :: TRUE");
			int index = response.indexOf("imdbRating");
//			//System.out.println(index);
			rating = response.substring((index + 13), (index + 16)).replace('/', '.');
			dcl.movieRating.add(i,rating);
			dcl.movieSynopsis.add(i,response.substring((response.indexOf("Plot")+7),(response.indexOf("Language")-3)));
			getLang(i);
			dcl.movieYear.add(i,response.substring((response.indexOf("Released")+11),((response.indexOf("Released")+22))));
			if(dcl.movieYear.get(i).contains("N/A"))
			{
				dcl.movieYear.add(i, "N.A");
			}
			
		}
		else{
			dcl.movieRating.add(i,"N.A");
			dcl.movieSynopsis.add(i,"N.A");
			dcl.movieLang.add(i,"N.A");
			dcl.movieYear.add(i,"N.A");
		}
		
//		String syn = "";
//		//System.out.println((response.indexOf("Plot")+7) + "      "+ (response.indexOf("Language")-3));

//		//System.out.println("\n\nSynopsis ::\n" +dcl.movieSynopsis +"\n\n" );
//		//System.out.println("Received rating ---> " + rating);
		
	}
	
	public static String toTitleCase(String input) {
	    String title = "";
	    title = title + input.charAt(0);
	    title = title.toUpperCase();
	    for(int i = 1; i < input.length(); i++)
	    {
	    	if(input.charAt(i-1) == ' ')
	    	{
	    		String temp = ("" + input.charAt(i)).toUpperCase();
	    		title+=temp;
	    	}
	    	else
	    	{
	    		title+=input.charAt(i);
	    	}
	    }
	    
	    return title;
	}
}
