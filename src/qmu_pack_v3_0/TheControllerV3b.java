package qmu_pack_v3_0;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class TheControllerV3b {
	
	JLabel status;
	
	public void mainControl(DataClass dc, JProgressBar jProgressBar, JLabel jLabel) {
		{
			try {
				dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
				status = jLabel;
				dc.path = dc.result.get(0);
				dc.path = dc.path.replace("\\", "/") + "/";
				String pathcopy = dc.path;
				dc.LOGGER.info(pathcopy);
//				//System.out.println(dc.path);
				boolean reBreak = false;
				// progressBar.setValue(25);
				switch (dc.result.get(1)) {

				case "rerate":
					reBreak = true;

				case "derate":
					
					pathcopy = dc.path;
					dc.path = dc.result.get(0);
					dc.path = dc.path.replace("\\", "/") + "/";

//				//System.out.println("Entry rate :: " + dc.path);

					// //System.out.println("Path :: " + dc.path);
//				//System.out.println("Job : Rate");
					
					FileToFolder FTF = new FileToFolder(dc, jProgressBar, jLabel);
					FTF.entry();
					
					
					pathcopy = dc.path;
					dc.path = dc.result.get(0);
					dc.path = dc.path.replace("\\", "/") + "/";

//					//System.out.println("Entry derate :: " + dc.path);
//					//System.out.println("Job : deRate");
					
					status.setText("Getting movie names...");
					GetMovieName GMNde = new GetMovieName(dc, jProgressBar, jLabel);
					// List<String> movieNamede = new ArrayList<String>();
					GMNde.entry();
					
					status.setText("Removing ratings...");
					UnraterV3 ur = new UnraterV3(dc);
					ur.entry(Integer.parseInt(dc.result.get(5)));
//					//System.out.println("You are good to go.");
//					//System.out.println("Created by Soumyadeep Roy.");
					dc.path = pathcopy;
//					//System.out.println("Exit derate :: " + dc.path);
					dc.reset();
					if (reBreak)
						reBreak = false;
					else
						break;

				case "rate":
					pathcopy = dc.path;
					dc.path = dc.result.get(0);
					dc.path = dc.path.replace("\\", "/") + "/";

//					//System.out.println("Entry rate :: " + dc.path);

					// //System.out.println("Path :: " + dc.path);
//					//System.out.println("Job : Rate");
					status.setText("Moving files to folders...");
					FTF = new FileToFolder(dc,jProgressBar, status);
					FTF.entry();

					// LastMod LM = new LastMod(dc.path);
					// LM.entry();

					// //System.out.println("Name of the directories");
					status.setText("Generating list of movies...");
					GetMovieName GMN = new GetMovieName(dc, jProgressBar, status);
					// List<String> movieName = new ArrayList<String>();
					GMN.entry();

					
					status.setText("Fetching ratings...");
					GetRating GR = new GetRating(dc, jProgressBar, status);
					// List<String> movieRating;
					// progressBar.setValue(20);
					GR.entry();

					// Unrater ur = new Unrater(path, movieName, movieRating);
					// ur.unrate();

					if (dc.result.get(4).equals("true")) {
						PutSynopsis PS = new PutSynopsis(dc, jProgressBar, status);
						PS.entry();
						//System.out.println("Synop Complete");
					}
					PutRating PR = new PutRating(dc, jProgressBar, status);
					PR.entry();
//				for (int i = dc.movieName.size() + 1; i < dc.movieRating.size(); i++) {
//					dc.movieRating.remove(i);
//				}

//					//System.out.println("You are good to go.");
//					//System.out.println("\n\n\nCreated by \n\n" + DataClass.name + ".");
//					//System.out.println("\nThanks to http://www.omdbapi.com/");
					dc.path = pathcopy;
					//System.out.println("Exit rate :: " + dc.path);
//				dc.reset();

				}

				if (dc.result.get(3).equals("true")) {
//					//System.out.println("NewPath All :: " + dc.newPath);
					SortOut SO = new SortOut(dc, jProgressBar, status);
					SO.entry();
				}
				status.setText("Done. Wanna try again?");
				dc.reset();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				dc.LOGGER.severe(e.getMessage());
			}

		}
	}
}
