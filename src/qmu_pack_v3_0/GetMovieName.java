package qmu_pack_v3_0;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GetMovieName {
	
//	String path;
	JProgressBar pb;
	JLabel status;
	DataClass dcl;
	public GetMovieName(DataClass dc, JProgressBar jProgressBar, JLabel jlabel)
	{
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		dcl = dc;
		pb = jProgressBar;
		status = jlabel;
		pb.setValue(0);
		status.setText("Retriving the Movie Names.");
//		//System.out.println("\n\n\n======================================================");
//		//System.out.println("Retriving the Movie Names.");
//		//System.out.println("======================================================");
	}

	public void entry() {
		
		getName();
//		getYear();
		//System.out.println("GetMovieName Exit");
		pb.setValue(0);
	}
	
//	private void getYear(){
//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		//System.out.println("Year :: " + year);
//		for(int i = 0; i < dcl.size; i++)
//		{
//			for(int j = 1900; j <= year; j++)
//			{
//				if(dcl.movieName.get(i).contains(String.valueOf(j)))
//				{
//					dcl.movieYear.set(i, String.valueOf(j));
//					break;
//				}
//				else
//				{
//					dcl.movieYear.set(i, String.valueOf(0000));
//				}
//			}
//		}
//		
//	}
	private void getName() {
		dcl.LOGGER.info("getName()");
//		List<String> movieName = new ArrayList<String>();

		File directory = new File(dcl.path);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
//				//System.out.println(file.getName());
				dcl.movieName.add(file.getName());
			}
		}
//		//System.out.println("Movie Name :: " + dcl.movieName);
//		status = dcl.movieName;
		dcl.LOGGER.info(dcl.movieName.toString());
		dcl.movieRefined.addAll(dcl.movieName);
//		return movieName;
	}
}
