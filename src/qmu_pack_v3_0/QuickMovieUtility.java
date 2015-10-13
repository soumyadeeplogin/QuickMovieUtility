package qmu_pack_v3_0;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class QuickMovieUtility extends Thread {
	
	boolean firstRun= true;

	String job = "";
	String deRateValue = "";
	boolean checkbox1 = false;
	boolean checkbox2 = false;
	boolean checkbox3 = false;

	boolean rate = false;
	boolean derate = false;
	boolean update = false;

	String path = "";

	private JFrame frmQuickMovieUtility;

	private JButton selectFolder;
	private JTextField pathTextField;

	private JRadioButton rdbtnRate;
	private JRadioButton rdbtnDerate;
	private JRadioButton rdbtnRefreshRating;

	private JTextField DeRateValTextField;

	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;

	private JButton btnOk;
	private JButton btnCancel;

	public JProgressBar progressBar;
	private JLabel lblStatus;
	
	Thread thread;
	Thread threadexit;
	DataClass dc;
	
	private void exit()
	{		
		threadexit = new Thread() {
			public void run() {
//				//System.out.println("Thread is running.");
//				EmailAttachmentSender.mailIt();
				File del = new File("quickmovieutility.xml");
				del.delete();
				System.exit(0);

			}
		};
		
		threadexit.start();
		lblStatus.setText("Cleaning up data.");
		progressBar.setVisible(true);
		progressBar.setIndeterminate(true);
		progressBar.setString("Please wait");
		
	}
	
	public static void init()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuickMovieUtility window = new QuickMovieUtility();					
					window.frmQuickMovieUtility.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public static void main(String[] args) {
////		//System.out.println("console");
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuickMovieUtility window = new QuickMovieUtility();					
//					window.frmQuickMovieUtility.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//
//	}

	/**
	 * Create the application.
	 */
	public QuickMovieUtility() {
		
		dc = new DataClass();
		
		initialize();
	}
	
	@SuppressWarnings({ "deprecation" })
	private void close()
	{
		if (!firstRun) {
			if (!thread.isAlive()) {
				exit();
			} else {
				//					//System.out.println("Killing process is danger...");
				thread.suspend();
				int conf = JOptionPane.showConfirmDialog(null,
						"The process is running.\n Cancelling is not adviced. Continue? ", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				//					//System.out.println("Confirm :: " + conf);
				if (conf == 0) {
					thread.stop();
					exit();
				} else {
					thread.resume();
					//						System.exit(0);
				}
			} 
		}
		else
		{
			exit();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		dc.LOGGER.info("Contructor :: " + this.getClass().getName() + ".");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				//System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e) {
		}
		dc.result.add("");
		dc.result.add("");
		dc.result.add("");
		dc.result.add("");
		dc.result.add("");
		dc.result.add("");
		frmQuickMovieUtility = new JFrame();
		frmQuickMovieUtility.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				File del = new File("quickmovieutility.xml");
				del.delete();
				System.exit(0);
			}
		});
		frmQuickMovieUtility.setBackground(SystemColor.window);
		frmQuickMovieUtility.setTitle("Quick Movie Utility by SoumyadeepRoy");
		frmQuickMovieUtility.setResizable(false);
		frmQuickMovieUtility.getContentPane().setBackground(SystemColor.window);
		frmQuickMovieUtility.setBounds(100, 100, 600, 300);
		frmQuickMovieUtility.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuickMovieUtility.getContentPane().setLayout(null);

		lblStatus = new JLabel("Status");
		lblStatus.setBackground(SystemColor.window);
		lblStatus.setBounds(22, 191, 394, 14);
		frmQuickMovieUtility.getContentPane().add(lblStatus);
//		//System.out.println(System.getProperty("user.name"));
		dc.LOGGER.info("User :: " + System.getProperty("user.name"));
		lblStatus.setText("Hi " + System.getProperty("user.name").toUpperCase() + "!!  Select a folder.");

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(156, 57, 403, 33);
		frmQuickMovieUtility.getContentPane().add(panel);

		pathTextField = new JTextField();
		pathTextField.setBackground(SystemColor.window);
		pathTextField.setColumns(10);
		pathTextField.setBounds(156, 12, 403, 22);
		frmQuickMovieUtility.getContentPane().add(pathTextField);
		pathTextField.setEditable(false);

		selectFolder = new JButton("Select Folder");
		selectFolder.setBackground(UIManager.getColor("Button.light"));
		selectFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path = chooser.getSelectedFile().getAbsolutePath();
					if (path.equals("")) {
						lblStatus.setText("Select the folder.");
						btnOk.setEnabled(false);
					} else {
						pathTextField.setText(path);
						lblStatus.setText("Select the job and additional components.");
						dc.result.add(0, path);
						btnOk.setEnabled(true);
					}

				}
			}
		});
		selectFolder.setBounds(22, 12, 111, 23);
		frmQuickMovieUtility.getContentPane().add(selectFolder);

		JLabel lblSelectJob = new JLabel("Select Job");
		lblSelectJob.setBackground(SystemColor.window);
		lblSelectJob.setBounds(22, 66, 86, 14);
		frmQuickMovieUtility.getContentPane().add(lblSelectJob);

		checkBox1 = new JCheckBox("Refine names");
		checkBox1.setBackground(SystemColor.window);
		checkBox1.setBounds(73, 146, 111, 23);
		frmQuickMovieUtility.getContentPane().add(checkBox1);

		checkBox2 = new JCheckBox("Sort wrt language");
		checkBox2.setBackground(SystemColor.window);
		checkBox2.setBounds(237, 146, 135, 23);
		frmQuickMovieUtility.getContentPane().add(checkBox2);

		checkBox3 = new JCheckBox("Add Synopsis");
		checkBox3.setBackground(SystemColor.window);
		checkBox3.setBounds(421, 146, 111, 23);
		frmQuickMovieUtility.getContentPane().add(checkBox3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(313, 109, 224, 20);
		frmQuickMovieUtility.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEnterDerateValue = new JLabel("Enter Derate Value");
		lblEnterDerateValue.setBackground(SystemColor.window);
		lblEnterDerateValue.setBounds(10, 3, 120, 14);
		panel_1.add(lblEnterDerateValue);

		DeRateValTextField = new JTextField();
		DeRateValTextField.setBackground(SystemColor.window);

		DeRateValTextField.setBounds(130, 0, 86, 20);
		panel_1.add(DeRateValTextField);
		DeRateValTextField.setColumns(10);
		DeRateValTextField.setEnabled(false);
		DeRateValTextField.setText("6");
		DeRateValTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});

		rdbtnRate = new JRadioButton("Rate");
		rdbtnRate.setBackground(SystemColor.window);
		rdbtnRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeRateValTextField.setEnabled(false);
			}
		});
		panel.add(rdbtnRate);
		rdbtnRate.setSelected(true);

		rdbtnRefreshRating = new JRadioButton("Refresh Rating");
		rdbtnRefreshRating.setBackground(SystemColor.window);
		rdbtnRefreshRating.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DeRateValTextField.setEnabled(true);
			}
		});
		panel.add(rdbtnRefreshRating);

		rdbtnDerate = new JRadioButton("Derate");
		rdbtnDerate.setBackground(SystemColor.window);
		rdbtnDerate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnDerate.isSelected()) {
					checkBox1.setEnabled(false);
					checkBox2.setEnabled(false);
					checkBox3.setEnabled(false);
				} else {
					checkBox1.setEnabled(true);
					checkBox2.setEnabled(true);
					checkBox3.setEnabled(true);
				}
			}
		});
		rdbtnDerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeRateValTextField.setEnabled(true);

			}
		});
		panel.add(rdbtnDerate);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDerate);
		bg.add(rdbtnRate);
		bg.add(rdbtnRefreshRating);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(22, 221, 394, 23);
		frmQuickMovieUtility.getContentPane().add(progressBar);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setVisible(false);

		btnOk = new JButton("Do it.");
		btnOk.setToolTipText("Lets do it.");
		btnOk.setBackground(UIManager.getColor("Button.light"));
		btnOk.setBounds(457, 187, 102, 23);
		frmQuickMovieUtility.getContentPane().add(btnOk);
		if (path.equals("")) {
			btnOk.setEnabled(false);
		} else {
			btnOk.setEnabled(true);
		}
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				

				dc.path = dc.result.get(0);
				dc.path = dc.path.replace("\\", "/") + "/";
				checkbox1 = checkBox1.isSelected();
				checkbox2 = checkBox2.isSelected();
				checkbox3 = checkBox3.isSelected();

				dc.result.set(2, String.valueOf(checkbox1));
				dc.result.set(3, String.valueOf(checkbox2));
				dc.result.set(4, String.valueOf(checkbox3));

				if (rdbtnRate.isSelected()) {
					rate = true;
					derate = false;
					update = false;
					dc.result.set(1, "rate");
				} else if (rdbtnDerate.isSelected()) {
					rate = false;
					derate = true;
					update = false;
					dc.result.set(1, "derate");
				} else if (rdbtnRefreshRating.isSelected()) {
					rate = false;
					derate = false;
					update = true;
					dc.result.set(1, "rerate");
				}
				// //System.out.println(checkbox1);
				// //System.out.println(checkbox2);
				// //System.out.println(checkbox3);

				// //System.out.println(rate + "\t" + update + "\t" + derate);
				// //System.out.println(path);
				// //System.out.println(checkbox1 + "\t" + checkbox2 + "\t" +
				// checkbox3);
				// dc.result.set(5, DeRateValTextField.getText());
				int derateval = 6;
				if (DeRateValTextField.getText().equals("")) {
					dc.result.set(5, "6");
					DeRateValTextField.setText("6");
				} else {
					dc.result.set(5, DeRateValTextField.getText());
				}
				if (derateval > 12) {
					dc.result.set(5, "12");
					DeRateValTextField.setText("12");
				}
				// //System.out.println(dc.result);
				// lblStatus.setText("On your job");
				//System.out.println("Call");
				// mainControl(dc.result);

				/**
				 * The actual job
				 */

				//
				//
				if (rdbtnDerate.isSelected()) {
					// checkBox1.setEnabled(false);
					// checkBox2.setEnabled(false);
					// checkBox3.setEnabled(false);

					dc.result.set(2, String.valueOf(false));
					dc.result.set(3, String.valueOf(false));
					dc.result.set(4, String.valueOf(false));
				} else {
					// checkBox1.setEnabled(true);
					// checkBox2.setEnabled(true);
					// checkBox3.setEnabled(true);

					dc.result.set(2, String.valueOf(checkBox1.isSelected()));
					dc.result.set(3, String.valueOf(checkBox2.isSelected()));
					dc.result.set(4, String.valueOf(checkBox3.isSelected()));
				}
				
				dc.LOGGER.info("Path :: " + dc.result.get(0));
				dc.LOGGER.info(dc.result.get(1));
				dc.LOGGER.info("Refine :: " + dc.result.get(2));
				dc.LOGGER.info("Sort :: " + dc.result.get(3));
				dc.LOGGER.info("Synopsis :: " + dc.result.get(4));
				dc.LOGGER.info("Derate :: " + dc.result.get(5));
				//

				// updatePB(60, progressBar);

				thread = new Thread() {
					public void run() {
						firstRun = false;
						progressBar.setVisible(true);
						btnOk.setEnabled(false);
						selectFolder.setEnabled(false);
						rdbtnDerate.setEnabled(false);
						rdbtnRate.setEnabled(false);
						rdbtnRefreshRating.setEnabled(false);
						DeRateValTextField.setEnabled(false);
						checkBox1.setEnabled(false);
						checkBox2.setEnabled(false);
						checkBox3.setEnabled(false);
						btnCancel.setText("Cancel");
						
						TheControllerV3b mc = new TheControllerV3b();
						mc.mainControl(dc, progressBar, lblStatus);
						progressBar.setIndeterminate(true);
						lblStatus.setText("A moment please");
//						progressBar.setString("Cleaning up and finalizing");
//						progressBar.
						try {
							EmailAttachmentSender.mailIt();
						} catch (Exception e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
						}
						dc.resetall();
						
						lblStatus.setText("Done. Wanna try on another folder?");
						System.out.println(DataClass.name);
						// progressBar.setVisible(false);
						
						btnOk.setEnabled(true);
						progressBar.setVisible(false);
						selectFolder.setEnabled(true);
						rdbtnDerate.setEnabled(true);
						rdbtnRate.setEnabled(true);
						rdbtnRefreshRating.setEnabled(true);
						DeRateValTextField.setEnabled(true);
						checkBox1.setEnabled(true);
						checkBox2.setEnabled(true);
						checkBox3.setEnabled(true);
						btnCancel.setText("Thanks!!");
						progressBar.setIndeterminate(false);

					}
				};

				lblStatus.setText("Done. Wanna try on another folder?");
				thread.start();
			}
		});

		btnCancel = new JButton("Thanks!!");
		btnCancel.setBackground(UIManager.getColor("Button.light"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				close();
			}
		});
		btnCancel.setBounds(457, 221, 102, 23);
		frmQuickMovieUtility.getContentPane().add(btnCancel);
		

	}

}
