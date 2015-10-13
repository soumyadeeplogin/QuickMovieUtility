package qmu_pack_v3_0;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastMod {
	
	List<String> folder = new ArrayList<String>();
	List<String> folderPath = new ArrayList<String>();
	String path;
	
	
	public LastMod(String Path)
	{
		
		path = Path;
		
	}
	public void entry()
	{
		try {
			modify();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void modify() throws ParseException
	{

		File directory = new File(path);
		//System.out.println(path);
		File[] fList = directory.listFiles();
		
		
		for (File file : fList) {
			if (file.isDirectory()) {
//				//System.out.println(file.getName());
				String getNames = file.getName();
				folder.add(getNames);
				folderPath.add(path+"/"+getNames);
			}
		}
		
//		//System.out.println(folder);
//		//System.out.println(folderPath);
		
		
		
//		//System.out.println(sdf.format(date));
		
		
		for(int i = 0; i < folderPath.size(); i++)
		{
//			Date date = new Date();
//			Date newDate = sdf.parse(sdf.format(date));
			
			
			Date now = new Date();
//			newDir.setLastModified(now.getTime());
			
			File directory2 = new File(folderPath.get(i));
			directory2.setLastModified(now.getTime());
		}
		
	}

}
