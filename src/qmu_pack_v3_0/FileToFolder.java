package qmu_pack_v3_0;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class FileToFolder {
	
	JProgressBar pb;
	JLabel status;

	/**
	 * Read the file names Create folder with the same file name Put the file in
	 * the folder created Delete the source file
	 */
//	String path;
//	List<String> fileName = new ArrayList<String>();
//	List<Path> sourcePath = new ArrayList<Path>();
//	List<Path> targetPath = new ArrayList<Path>();
	DataClass dcl;
	public FileToFolder(DataClass dc, JProgressBar jProgressBar, JLabel label) {
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		//path = Path;
		dcl = dc;
		pb = jProgressBar;
		status = label;
		status.setText("Trying to move files to folders...");
		//System.out.println("\n\n======================================================");
		//System.out.println("Moving all files to folder. This will take sometime.");
		//System.out.println("======================================================\n\n\n");
	}

	public void entry() {
		
		pb.setValue(0);
		status.setText("Fetching files...");
		fetchFileNames();
		pb.setValue(33);
		status.setText("Creating paths...");
		createPaths();
		pb.setValue(66);
		status.setText("Moving files...");
		moveFileToFolder();
		pb.setValue(99);
		File file = new File(dcl.path);
		File[] files = file.listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File f) {
		        return f.isDirectory();
		    }
		});
		dcl.size = files.length;
		pb.setValue(100);
		//System.out.println(dcl.size);
		pb.setMaximum(dcl.size-1);
		pb.setValue(0);
	}

	private void fetchFileNames() {
		dcl.LOGGER.info("fetchFileNames()");
		File[] files = new File(dcl.path).listFiles();
		//System.out.println(dcl.path + "  From fetchFileNames()");
		for (File file : files) {
			if (file.isFile()) {
//				//System.out.println(dcl.path + "  From fetchFileNames()");
				dcl.fileName.add(file.getName());
				dcl.sourcePath.add(Paths.get(file.getAbsolutePath()));
			}
		}
		dcl.LOGGER.info(dcl.fileName.toString());
		dcl.LOGGER.info(dcl.sourcePath.toString());

	}

	private void createPaths() {
		dcl.LOGGER.info("createPaths()");
		for (int i = 0; i < dcl.fileName.size(); i++) {

			String newFolder = (dcl.path + dcl.fileName.get(i)).substring(0, (dcl.path + dcl.fileName.get(i)).lastIndexOf('.')).trim();

			dcl.targetPath.add(Paths.get((newFolder + "\\" + dcl.fileName.get(i)).replaceAll("//", "\\")));

//			printPaths(i);
			createFolder(newFolder);
//			pb.setValue(i);
		}
		
		dcl.LOGGER.info(dcl.targetPath.toString());
	}

//	private void printPaths(int index) {
//		//System.out.println("File Name :: " + fileName.get(index));
//		//System.out.println("Source Name :: " + sourcePath.get(index));
//		//System.out.println("Target Name :: " + targetPath.get(index));
//	}

	private void createFolder(String name) {
		dcl.LOGGER.info("createFolder(String " + name +")");
		File file = new File(name);

		if (file.mkdir()) {
			dcl.LOGGER.info("Created.");
//			//System.out.println("Created Directory ::" + name);
		} else {
			dcl.LOGGER.warning("Failed.");
//			//System.out.println("Failed to create ::" + name);
		}

	}

	private void moveFileToFolder() {
		dcl.LOGGER.info("movieFileToFolder");
		for (int i = 0; i < dcl.fileName.size(); i++) {
			
			
			
			try {
				Files.move(dcl.sourcePath.get(i), dcl.targetPath.get(i) , REPLACE_EXISTING);
				dcl.LOGGER.info("Moved.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dcl.LOGGER.severe(e.toString());
			}
//			pb.setValue(i);

		}

	}

}
