package qmu_pack_v3_0;
import java.io.File;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PutRating {
	
//	String path;
//	List<String> movieName;
//	List<String> movieRating;
	DataClass dcl;
	JProgressBar pb;
	JLabel status;
	
	public PutRating(DataClass dc, JProgressBar jProgressBar, JLabel label)
	{
//		path = Path;
//		movieName = mName;
//		movieRating = mRating;
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		dcl = dc;
		pb = jProgressBar;
		status = label;
		
		pb.setValue(0);
		pb.setMaximum(dcl.size-1);
		
//		//System.out.println("\n\n\n======================================================");
//		//System.out.println("Publishing the ratings.");
//		//System.out.println("======================================================\n\n\n");
	}
	
	public void entry()
	{
		try {
			status.setText("Rating the movies...");
			setter();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setter() throws ParseException{
		
				

		for(int i = 0; i < dcl.movieName.size(); i++)
		{
			dcl.LOGGER.info("Movie :: " + dcl.movieName.get(i) + "   ||    Rating :: " + dcl.movieRating.get(i));
//			//System.out.println("Publishing for :: " + dcl.movieName.get(i) + "   ||    Rated :: " + dcl.movieRating.get(i));
			String dirPath = dcl.path+dcl.movieName.get(i);
//			dcl.newPath.add(i,dirPath+"/");
//			//System.out.println(dcl.newPath.get(i));
//			//System.out.println("Path :: " + dirPath + "\n\n");
			File dir = new File(dirPath);
			if (!dir.isDirectory()) {
				System.err.println("There is no directory @ given path");
				System.exit(0);
			}
//			Date newDate = sdf.parse(sdf.format(new Date()));
//			Date now = new Date();
//		    String strDate = sdf.format(now);
//		    //System.out.println(strDate);
			String newDirName;
			if(dcl.result.get(2).equals("true"))
			{
				
				newDirName = dcl.movieRating.get(i) + "   " + dcl.movieRefined.get(i) + "  [" + dcl.movieYear.get(i) +"]"; 
				dcl.LOGGER.info("newDirName [Refined] :: " + newDirName);
				
			}
			else
			{
				newDirName = dcl.movieRating.get(i) + "   " + dcl.movieName.get(i);
				dcl.LOGGER.info("newDirName :: " + newDirName);
			}
//			//System.out.println("Name new :: " + newDirName);
			File newDir = new File(dir.getParent() + "/" + newDirName);
			if(!(newDir.exists()))
			{
				dir.renameTo(newDir);	
				dcl.newPath.add(i,newDir.toString().replace("\\","/")+"/");
			}
			else
			{
				File newDirdup = new File(dir.getParent() + "/" + "[DUPLICATE] " +newDirName);
				dir.renameTo(newDirdup);	
				dcl.newPath.add(i,newDir.toString().replace("\\","/")+"/");
			}
//			//System.out.println("New Path :: " + dcl.newPath.get(i));
//			newDir.setLastModified(now.getTime());
//			//System.out.println(newDate.getTime());
//			//System.out.println("Done...\n\n");
			pb.setValue(i);
		}
	}
}
