package qmu_pack_v3_0;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Executor {



	public static void isConnected() {
		try {

			Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 www.quickmovieutility.esy.es");
			int returnVal = p1.waitFor();
			boolean reachable = (returnVal == 0);
			if (reachable == false) {
				int conf = JOptionPane.showConfirmDialog(null, "Unable to reach the server. Want to Retry?",
						"No internet connection.", JOptionPane.ERROR_MESSAGE);
				if (conf == 0) {
					isConnected();
				}
				else
				{
					System.exit(0);
				}
			}
		} catch (Exception e) {
			int conf = JOptionPane.showConfirmDialog(null, "Please connect to the internet. Want to Retry?",
					"No internet connection.", JOptionPane.ERROR_MESSAGE);
			if (conf == 0) {
				isConnected();
			}
			else
			{
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		//		//System.out.println("console");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				//			//System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Executor.isConnected();
		String ver = "";
		BufferedReader in = null;

		try {
			//			//System.out.print("Trying to fetch rating for :: " + name.replace("+", " "));

			URL url = new URL("http://quickmovieutility.esy.es/ver.php");
			URLConnection urlc;
			urlc = url.openConnection();
			urlc.addRequestProperty("user-agent", "Firefox");
			in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				ver = inputLine;
				//				System.out.println(ver);
			}
		} catch (IOException e) {

			//			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				//				dcl.LOGGER.severe(e.getMessage());
				//				e.printStackTrace();
			}
		}
		if(DataClass.version.equals(ver) | Float.parseFloat(DataClass.version) >= Float.parseFloat(ver))
		{
			QuickMovieUtility.init();
		}
		else
		{
			int conf = JOptionPane.showConfirmDialog(null,
					"Please download the latest version.", "New Version Available",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			if(conf==0)
			{
				URI uri;
				try {
					uri = new URI("http://quickmovieutility.zz.mu/download.html");

					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(uri);
						} catch (IOException e) { /* TODO: error handling */ }
					} else { /* TODO: error handling */ }
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}

}
