package qmu_pack_v3_0;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PutSynopsis {

	DataClass dcl;
	JProgressBar pb;
	JLabel status;

	public PutSynopsis(DataClass dc, JProgressBar jProgressBar, JLabel jLabel) {
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		dcl = dc;
		pb = jProgressBar;
		status = jLabel;
		pb.setValue(0);		
		pb.setMaximum(dcl.size);
	}

	public void entry() {
		status.setText("Adding synopsis...");
		putIt();
		pb.setValue(0);	
		
	}

	private void putIt() {
		dcl.LOGGER.info("putIt()");
		for (int i = 0; i < dcl.movieName.size(); i++) {
			// //System.out.println("Publishing for :: " + dcl.movieName.get(i) +
			// " || Rated :: " + dcl.movieRating.get(i));
			String dirPath = dcl.path + dcl.movieName.get(i) + "/";
//			//System.out.println("Synop Path :: " + dirPath + "\n\n");
			dcl.LOGGER.info("Synopsis Path :: " + dirPath);
			File dir = new File(dirPath);
			if (!dir.isDirectory()) {
//				System.err.println("There is no directory @ given path");
				dcl.LOGGER.info("Dir Not Found at PutSynopsis :: " + dirPath);
				System.exit(0);
			}
			// Date newDate = sdf.parse(sdf.format(new Date()));
			// Date now = new Date();
			// String strDate = sdf.format(now);
			// //System.out.println(strDate);
			
			File f = new File(dirPath);
			if (f.exists() && f.isDirectory()) {
				PrintWriter writer;
				try {
					writer = new PrintWriter(dirPath+"Synopsis.txt", "UTF-8");
					writer.println(dcl.movieSynopsis.get(i));
					writer.println("-Created by Quick Movie Utility by Soumyadeep Roy");
					writer.close();
//					//System.out.println("Done...\n\n");
					dcl.LOGGER.info("Sysnopsis added @ " + dirPath);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					dcl.LOGGER.info(e.getMessage());
				}
			}
			
			pb.setValue(i);	

		}
	}

}
