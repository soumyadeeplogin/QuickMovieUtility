package qmu_pack_v3_0;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataClass {
	
	
	static String name  = "                                                                                                                                    dddddddd                                                                                                                               \n   SSSSSSSSSSSSSSS                                                                                                                   d::::::d                                                                 RRRRRRRRRRRRRRRRR                                             \n SS:::::::::::::::S                                                                                                                  d::::::d                                                                 R::::::::::::::::R                                            \nS:::::SSSSSS::::::S                                                                                                                  d::::::d                                                                 R::::::RRRRRR:::::R                                           \nS:::::S     SSSSSSS                                                                                                                  d:::::d                                                                  RR:::::R     R:::::R                                          \nS:::::S               ooooooooooo   uuuuuu    uuuuuu     mmmmmmm    mmmmmmm   yyyyyyy           yyyyyyy  aaaaaaaaaaaaa       ddddddddd:::::d     eeeeeeeeeeee        eeeeeeeeeeee    ppppp   ppppppppp          R::::R     R:::::R   ooooooooooo   yyyyyyy           yyyyyyy\nS:::::S             oo:::::::::::oo u::::u    u::::u   mm:::::::m  m:::::::mm  y:::::y         y:::::y   a::::::::::::a    dd::::::::::::::d   ee::::::::::::ee    ee::::::::::::ee  p::::ppp:::::::::p         R::::R     R:::::R oo:::::::::::oo  y:::::y         y:::::y \n S::::SSSS         o:::::::::::::::ou::::u    u::::u  m::::::::::mm::::::::::m  y:::::y       y:::::y    aaaaaaaaa:::::a  d::::::::::::::::d  e::::::eeeee:::::ee e::::::eeeee:::::eep:::::::::::::::::p        R::::RRRRRR:::::R o:::::::::::::::o  y:::::y       y:::::y  \n  SS::::::SSSSS    o:::::ooooo:::::ou::::u    u::::u  m::::::::::::::::::::::m   y:::::y     y:::::y              a::::a d:::::::ddddd:::::d e::::::e     e:::::ee::::::e     e:::::epp::::::ppppp::::::p       R:::::::::::::RR  o:::::ooooo:::::o   y:::::y     y:::::y   \n    SSS::::::::SS  o::::o     o::::ou::::u    u::::u  m:::::mmm::::::mmm:::::m    y:::::y   y:::::y        aaaaaaa:::::a d::::::d    d:::::d e:::::::eeeee::::::ee:::::::eeeee::::::e p:::::p     p:::::p       R::::RRRRRR:::::R o::::o     o::::o    y:::::y   y:::::y    \n       SSSSSS::::S o::::o     o::::ou::::u    u::::u  m::::m   m::::m   m::::m     y:::::y y:::::y       aa::::::::::::a d:::::d     d:::::d e:::::::::::::::::e e:::::::::::::::::e  p:::::p     p:::::p       R::::R     R:::::Ro::::o     o::::o     y:::::y y:::::y     \n            S:::::So::::o     o::::ou::::u    u::::u  m::::m   m::::m   m::::m      y:::::y:::::y       a::::aaaa::::::a d:::::d     d:::::d e::::::eeeeeeeeeee  e::::::eeeeeeeeeee   p:::::p     p:::::p       R::::R     R:::::Ro::::o     o::::o      y:::::y:::::y      \n            S:::::So::::o     o::::ou:::::uuuu:::::u  m::::m   m::::m   m::::m       y:::::::::y       a::::a    a:::::a d:::::d     d:::::d e:::::::e           e:::::::e            p:::::p    p::::::p       R::::R     R:::::Ro::::o     o::::o       y:::::::::y       \nSSSSSSS     S:::::So:::::ooooo:::::ou:::::::::::::::uum::::m   m::::m   m::::m        y:::::::y        a::::a    a:::::a d::::::ddddd::::::dde::::::::e          e::::::::e           p:::::ppppp:::::::p     RR:::::R     R:::::Ro:::::ooooo:::::o        y:::::::y        \nS::::::SSSSSS:::::So:::::::::::::::o u:::::::::::::::um::::m   m::::m   m::::m         y:::::y         a:::::aaaa::::::a  d:::::::::::::::::d e::::::::eeeeeeee   e::::::::eeeeeeee   p::::::::::::::::p      R::::::R     R:::::Ro:::::::::::::::o         y:::::y         \nS:::::::::::::::SS  oo:::::::::::oo   uu::::::::uu:::um::::m   m::::m   m::::m        y:::::y           a::::::::::aa:::a  d:::::::::ddd::::d  ee:::::::::::::e    ee:::::::::::::e   p::::::::::::::pp       R::::::R     R:::::R oo:::::::::::oo         y:::::y          \n SSSSSSSSSSSSSSS      ooooooooooo       uuuuuuuu  uuuummmmmm   mmmmmm   mmmmmm       y:::::y             aaaaaaaaaa  aaaa   ddddddddd   ddddd    eeeeeeeeeeeeee      eeeeeeeeeeeeee   p::::::pppppppp         RRRRRRRR     RRRRRRR   ooooooooooo          y:::::y           \n                                                                                    y:::::y                                                                                           p:::::p                                                            y:::::y            \n                                                                                   y:::::y                                                                                            p:::::p                                                           y:::::y             \n                                                                                  y:::::y                                                                                            p:::::::p                                                         y:::::y              \n                                                                                 y:::::y                                                                                             p:::::::p                                                        y:::::y               \n                                                                                yyyyyyy                                                                                              p:::::::p                                                       yyyyyyy                \n                                                                                                                                                                                     ppppppppp                                                                             \n";
	static String version = "3.5"; 
	
	String path;
	List<Path> sourcePath;
	List<Path> targetPath;
	List<String> movieName;
	List<String> fileName;
	List<String> movieRating;
	List<String> movieSynopsis;
	List<String> movieRefined;
	List<String> movieLang;
	List<String> movieYear;
	List<String> result;
	List<String> newPath;
	Logger LOGGER;
	FileHandler fh;
	
	int size;
	
	public DataClass()
	{
		LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		try {
			fh = new FileHandler("quickmovieutility.xml");
			LOGGER.addHandler(fh);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		

		path = "";
		sourcePath = new ArrayList<Path>(1000);
		targetPath = new ArrayList<Path>(1000);
		 movieName = new ArrayList<String>(1000);
		 movieYear = new ArrayList<String>(1000);
		 fileName = new ArrayList<String>(1000);
		 movieRating = new ArrayList<String>(1000);
		 movieSynopsis = new ArrayList<String>(1000);
		 movieRefined = new ArrayList<String>(1000);
		 result = new ArrayList<String>(1000);
		 newPath = new ArrayList<String>(1000);
		 movieLang = new ArrayList<String>(1000);
		 size = 0;	
		 
	}
	
	public void reset()
	{
		LOGGER.info("reset() :: " + this.getClass().getName() + ".");
//		String path = "";
		sourcePath = new ArrayList<Path>(1000);
		targetPath = new ArrayList<Path>(1000);
		 movieName = new ArrayList<String>(1000);
		 movieYear = new ArrayList<String>(1000);
		 fileName = new ArrayList<String>(1000);
		 movieRating = new ArrayList<String>(1000);
		 movieRefined = new ArrayList<String>(1000);
//		 result = new ArrayList<String>(1000);
		 newPath = new ArrayList<String>(1000);
	}
	
	public void resetall()
	{
		LOGGER.info("resetall() :: " + this.getClass().getName() + ".");
//		String path = "";
		sourcePath = new ArrayList<Path>(1000);
		targetPath = new ArrayList<Path>(1000);
		 movieName = new ArrayList<String>(1000);
		 movieYear = new ArrayList<String>(1000);
		 fileName = new ArrayList<String>(1000);
		 movieRating = new ArrayList<String>(1000);
		 movieRefined = new ArrayList<String>(1000);
//		 result = new ArrayList<String>(1000);
		 newPath = new ArrayList<String>(1000);
		 movieSynopsis = new ArrayList<String>(1000);
		 movieLang = new ArrayList<String>(1000);
	}
	
}
