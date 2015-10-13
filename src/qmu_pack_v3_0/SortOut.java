package qmu_pack_v3_0;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class SortOut {

	DataClass dcl;
	JProgressBar pb;
	JLabel status;

	public SortOut(DataClass dc, JProgressBar jProgressBar, JLabel label){
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		dcl = dc;
		pb = jProgressBar;
		status = label;

		pb.setValue(0);
		pb.setMaximum(dcl.size-1);
	}

	public void entry()
	{
		status.setText("Creating folders...");
		pb.setValue(0);
		createFolder();
		status.setText("Sorting it...");
		pb.setValue(0);
		sortIt();
	}

	private void createFolder()
	{
		//		//System.out.println("\n\n\nSortOut\n\n");
		//		//System.out.println(dcl.newPath);
		//		//System.out.println("dcl.path+English :: " + dcl.path+"English");
		File f = new File(dcl.path+"English");
		if (!f.exists() && dcl.movieLang.contains("English")) {
			//			//System.out.println("Create ::  " + dcl.path+"/English");
			if (f.mkdir()) {
				//				//System.out.println("Directory is created!");
				dcl.LOGGER.info("Created::  " +dcl.path+"/English");
			}
			else
			{
				//				//System.out.println("Else");
				dcl.LOGGER.warning("Failed to Create::  " +dcl.path+"/English");
			}
		}
		pb.setValue(25);
		f = new File(dcl.path+"Hindi");
		if (!f.exists() && dcl.movieLang.contains("Hindi")) {
			//			//System.out.println("Create ::  " + dcl.path+"Hindi");
			if (f.mkdir()) {
				//				//System.out.println("Directory is created!");
				dcl.LOGGER.info("Created::  " +dcl.path+"/Hindi");
			}
			else
			{
				//				//System.out.println("Else");
				dcl.LOGGER.warning("Failed to Create::  " +dcl.path+"/Hindi");
			}
		}
		pb.setValue(50);
		f = new File(dcl.path+"Bengali");
		if (!f.exists() && dcl.movieLang.contains("Bengali")) {
			//			//System.out.println("Create ::  " + dcl.path+"Bengali");
			if (f.mkdir()) {
				//				//System.out.println("Directory is created!");
				dcl.LOGGER.info("Created::  " +dcl.path+"/Bengali");
			}
			else
			{
				//				//System.out.println("Else");
				dcl.LOGGER.warning("Failed to Create::  " +dcl.path+"/Bengali");
			}
		}
		pb.setValue(75);
		f = new File(dcl.path+"Others");
		if (!f.exists() && dcl.movieLang.contains("Others")) {
			//			//System.out.println("Create ::  " + dcl.path+"Others");
			if (f.mkdir()) {
				//				//System.out.println("Directory is created!");
				dcl.LOGGER.info("Created::  " +dcl.path+"/others");
			}
			else
			{
				//				//System.out.println("Else");
				dcl.LOGGER.warning("Failed to Create::  " +dcl.path+"/others");
			}
		}
		pb.setValue(90);

		for(int i = 0; i < dcl.size;i++)
		{
			if(dcl.result.get(2).equals("true"))
			{
//								newDirName = dcl.movieRating.get(i) + "   " + dcl.movieRefined.get(i); 
				//				//System.out.println("Movie :: " + dcl.movieRefined.get(i) + "\nLanguage :: " + dcl.movieLang.get(i));
			}
			else
			{
				//				newDirName = dcl.movieRating.get(i) + "   " + dcl.movieName.get(i);
				//				//System.out.println("Movie :: " + dcl.movieName.get(i) + "\nLanguage :: " + dcl.movieLang.get(i));
			}
			//			//System.out.println("Movie :: " + );
		}
		//		//System.out.println(f.exists());

		//		//System.out.println(f.isDirectory());
		pb.setValue(100);
	}

	private void moveIt(String path, int i)
	{
		dcl.LOGGER.info("moveIt()");
		//System.out.println("Path to movie to :: " + path);
		String target = "";
		String source = "";
		if(dcl.result.get(2).equals("true"))
		{
			source = dcl.path+(dcl.movieRating.get(i)) + "   " +dcl.movieRefined.get(i) + "  [" + dcl.movieYear.get(i) +"]";
			//System.out.println("Source :: " + source);
			target = path+dcl.movieRating.get(i) + "   " +dcl.movieRefined.get(i) + "  [" + dcl.movieYear.get(i) +"]";
		} else
		{
			source = dcl.path+(dcl.movieRating.get(i)) + "   " +dcl.movieName.get(i);
			//System.out.println("Source :: " + source);
			target = path+dcl.movieRating.get(i) + "   " +dcl.movieName.get(i);
		}
//		//System.out.println("source :: " + source);
//		//System.out.println("Target :: " + target);
		dcl.LOGGER.info("source :: " + source);
		dcl.LOGGER.info("Target :: " + target);
		try {
			Files.move(Paths.get(source), Paths.get(target), REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			dcl.LOGGER.severe(e.getMessage());
		}
	}
	private void sortIt()
	{
		//		String pathEng = dcl.path+"English";
		//		String pathHin = dcl.path+"Hindi";
		//		String pathBen = dcl.path+"Bengali";
		//		String pathOth = dcl.path+"Others";

		for(int i = 0; i < dcl.size; i++)
		{
			moveIt(dcl.path+dcl.movieLang.get(i)+"/", i);
			pb.setValue(i);
		}
	}
}
