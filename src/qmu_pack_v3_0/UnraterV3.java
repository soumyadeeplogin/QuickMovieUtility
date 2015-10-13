package qmu_pack_v3_0;
import java.io.File;

import javax.swing.JOptionPane;

public class UnraterV3 {

	// List<String> movieName;
	// List<String> movieRating;
	// String path;
	DataClass dcl;

	public UnraterV3(DataClass dc) {
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		// path = Path;
		// movieName = mName;
		// movieRating = mRating;
		dcl = dc;
		////System.out.println("Removing the ratings.");
	}

	public void entry(int len) {
		unrate(len);
	}

	private void unrate(int len) {
		
		////System.out.println(dcl.size);
		// for (int i = 0; i < dcl.movieName.size(); i++) {
		for (int i = 0; i < dcl.size; i++) {
			String dirPath = dcl.path + dcl.movieName.get(i);
//			////System.out.println("Path :: " + dirPath);
			File dir = new File(dirPath);
			if (!dir.isDirectory()) {
				dcl.LOGGER.severe("Directory not found @ " + dir);
//				System.err.println("There is no directory @ given path");
				System.exit(0);
			}

			String newDirName = "";

			if (dcl.movieName.get(i).length() > len && checkRated(dcl.movieName.get(i))) {

				newDirName = dcl.movieName.get(i).substring(len, dcl.movieName.get(i).length());

			}
			else
			{
				newDirName = dcl.movieName.get(i);
			}

			File newDir = new File(dir.getParent() + "/" + newDirName);
			dir.renameTo(newDir);

//			////System.out.println("Done");
		}

	}

	private boolean checkRated(String name) {

		////System.out.println("checkRated :: " + name);
		if (name.substring(0, 3).equals("N.A") && name.substring(3, 6).equals("   ")) {
//			////System.out.println("If");
			return true;
		} else {
			////System.out.println("If Else");
			if (name.substring(3, 6).equals("   ")) {
//				////System.out.println("If Else If");
				try {

//					////System.out.println("If Else If try");
					float rating = Float.parseFloat(name.substring(0, 3));
//					////System.out.println("rating val :: " + rating);
					if (rating <= (float) 10 && rating >= (float) 0) {
						return true;
					}
					else
					{
						int conf = JOptionPane.showConfirmDialog(null,
								"The source of the ratings for " + name + " is unknown. \nDo you want to proceed at your own risk?",
								"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//						////System.out.println("Confirm :: " + conf);
						if (conf == 0)
							return true;
						else
							return false;
					}
				} catch (NumberFormatException e) {
					////System.out.println("If Else If catch");

					int conf = JOptionPane.showConfirmDialog(null,
							"The source of the ratings for " + name + " is unknown. \nDo you want to proceed at your own risk?",
							"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//					////System.out.println("Confirm :: " + conf);
					if (conf == 0)
						return true;
					else
						return false;
				}
			} else {
				////System.out.println("If Else Else");


				int conf = JOptionPane.showConfirmDialog(null,
						"The source of the ratings for " + name + " is unknown. \nDo you want to proceed at your own risk?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//				////System.out.println("Confirm :: " + conf);
				if (conf == 0)
					return true;
				else
					return false;
			}
		}
	}
}
