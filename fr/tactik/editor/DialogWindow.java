package fr.tactik.editor;

import java.awt.GridLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.*;

/**
 * This is the DialogWindow Class. This class manage all the DialogBoxes in the editor.
 * 
 * @author Juliette Belin, Alice Neichols, Denis Tribouillois 
 * @version 1.0
 */
public class DialogWindow extends JDialog{
		private static final long serialVersionUID = 1L;
		private static short levelWidth = -1;
		private static short levelHeight = -1;
		private static String levelBackground = null;
		private static String levelPath = null;
		private static boolean levelCreated = false;
		
		/**
		 * This function return the width of the level.
		 * @return 
		 * 			return the widht of the level.
		 */
		public static short getLevelWidth() {
			return levelWidth;
		}

		/**
		 * This function set the width of the level.
		 */
		public static void setLevelWidth(short mapWidth) {
			DialogWindow.levelWidth = mapWidth;
		}

		/**
		 * This function return the height of the level.
		 * @return 
		 * 			return the height of the level.
		 */
		public static short getLevelHeight() {
			return levelHeight;
		}

		/**
		 * This function set the height of the level.
		 * @param mapHeight
		 * 				Give the height of the map.
		 */
		public static void setLevelHeight(short mapHeight) {
			DialogWindow.levelHeight = mapHeight;
		}

		/**
		 * This function return the path of the level background.
		 * @return 
		 * 			The path of the background.
		 */
		public static String getLevelBackground() {
			return levelBackground;
		}

		/**
		 * This function set a path to the level background.
		 * @param levelBgPath 
		 * 					Set the path of the background.
		 */
		public static void setLevelBgPath(String levelBgPath) {
			DialogWindow.levelBackground = levelBgPath; 
		}
		
		/**
		 * This function set a path to the level background.
		 * @param Level
		 * 				Set the save of the level.
		 */		
		public static void setLevelPath(String level){
			DialogWindow.levelPath = level;
		}
		
		/**
		 * This function get a path to the level.
		 * @return 
		 * 			the path of the level.
		 */
		public static String getLevelPath(){
			return DialogWindow.levelPath;
		}

		/**
		 * This function create the dialog window.
		 */
		private DialogWindow(JFrame owner) {
			super(owner, "New Level settings",true);
		}

		/**
		 * This function create a level with width, height and a background.	
		 */
		static boolean createDialog(final JFrame owner) {
			final DialogWindow nDialog = new DialogWindow(owner);
			GridLayout grid = new GridLayout (4,2);
			nDialog.setLocationRelativeTo(owner);
			nDialog.setLocation(owner.getWidth()/2, owner.getHeight()/2);
			nDialog.setResizable(true);
			nDialog.setLayout(grid);
			JLabel width = new JLabel ("width (tile number) :");
			JLabel height = new JLabel ("Height (tile number):");
			JLabel bgColor = new JLabel ("Background path :");
			
			JButton validation = new JButton("OK");
			final JTextField widthField = new JTextField("50");
			final JTextField heightField = new JTextField("10");
			final JButton bgPathField = new JButton("Browse ...");
			
			Vector<String> backgroundList = new Vector<String>();
			final String path = "images/game/backgrounds/";
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();

			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			        backgroundList.add(listOfFiles[i].getName());
			      }
			    }

		    JComboBox<String> backgroundListBox = new JComboBox<String>(backgroundList);
		    backgroundListBox.setSelectedIndex(1);
		    setLevelBgPath((String) path + backgroundListBox.getSelectedItem());
		    backgroundListBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					setLevelBgPath((String) path + cb.getSelectedItem());
				}
			});

			nDialog.add(width);
			nDialog.add(widthField);
			nDialog.add(height);
			nDialog.add(heightField);
			

			nDialog.add(bgColor);
			nDialog.add(backgroundListBox);
			
			JButton cancel = new JButton("Cancel");


			//events

			validation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{

						short h = (11 > Short.parseShort(heightField.getText())) ? 11 : Short.parseShort(heightField.getText());
						short w = (20 > Short.parseShort(widthField.getText())) ? 20 : Short.parseShort(widthField.getText());
						
						setLevelWidth(w);
						setLevelHeight(h);
						setLevelCreated(true);
						nDialog.dispose();
					}catch(NumberFormatException exept){
						JOptionPane.showMessageDialog(owner,
							    "Incorrect format",
							    "Format error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setLevelCreated(false);
					nDialog.dispose();
				}
			});
			
			nDialog.add(validation);
			nDialog.add(cancel);

			nDialog.setSize(500,150);
			nDialog.setResizable(false);
			nDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			nDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			nDialog.pack();
			nDialog.setVisible(true);
			return isLevelCreated();
		}
		
		/**
		 * This function allows to change background.
		 */
		static boolean createBgDialog(final JFrame owner) {
					
			final DialogWindow nDialog = new DialogWindow(owner);
			GridLayout grid = new GridLayout (2,1);
			nDialog.setLocationRelativeTo(owner);
			nDialog.setLocation(owner.getWidth()/2, owner.getHeight()/2);
			nDialog.setResizable(true);
			nDialog.setLayout(grid);
			JLabel bgPath = new JLabel ("Choose a background :");
			
			JButton validation = new JButton("OK");


			Vector<String> backgroundList = new Vector<String>();
			final String path = "images/game/backgrounds/";
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();

			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			        backgroundList.add(listOfFiles[i].getName());
			      }
			    }

		    JComboBox<String> backgroundListBox = new JComboBox<String>(backgroundList);
		    backgroundListBox.setSelectedIndex(1);
		    setLevelBgPath((String) path + backgroundListBox.getSelectedItem());
		    backgroundListBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					setLevelBgPath((String) path + cb.getSelectedItem());
				}
			});
		    
			JButton cancel = new JButton("Cancel");


			//events
			
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nDialog.dispose();
				}
			});
			
			validation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nDialog.dispose();
				}
			});
			
			nDialog.add(bgPath);
			nDialog.add(backgroundListBox);
			
			nDialog.add(validation);
			nDialog.add(cancel);
			
			nDialog.setSize(500,200);
			nDialog.setResizable(false);
			nDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			nDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			nDialog.pack();
			nDialog.setVisible(true);
			return isLevelCreated();
		}
		
		static boolean createOpenExistingLevelDialog(final JFrame owner) {
			System.out.println("CREATION");
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./levels"));
			chooser.setDialogTitle("Open Level");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt", "TXT");
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
				String filePath = chooser.getSelectedFile().getAbsolutePath();
				setLevelPath(filePath);
				setLevelWidth((short)getWidthFromFile(filePath));
				setLevelHeight((short)getHeightFromFile(filePath));
				levelCreated = true;
				return true;
			}
			levelCreated = false;
			return false;
			
		}
		
		/**
		 * This function create a save for a level.
		 */
		static boolean createSaveLevelDialog(final JFrame owner) {
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./levels"));
			chooser.setDialogTitle("Save Level");
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showSaveDialog(owner) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("txt")) {
				    // filename is OK as-is
				} else {
				    file = new File(file.toString() + ".txt");
				    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".txt");
				}
				
				setLevelPath(chooser.getSelectedFile().getAbsolutePath());
				return true;
			}
			return false;
			
		}

		/**
		 * This function indicate if a level is created or not.
		 * @return levelCreated
		 * 				If it is true, the level was created.
		 */
		public static boolean isLevelCreated() {
			return levelCreated;
		}
		
		/**
		 * This function set a level which is created.
		 * @param mapIsCreated 
		 * 				If it is true, the map was created.
		 */
		public static void setLevelCreated(boolean mapIsCreated) {
			DialogWindow.levelCreated = mapIsCreated;
		}
		
		public static int getWidthFromFile(String levelPath){
			try{
				
				FileInputStream fstream = new FileInputStream(levelPath);
				DataInputStream in = new DataInputStream(fstream);
	    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    		String strLine;
	    		
	    		strLine = br.readLine();  
				String[] tokens = strLine.split(" ");
				br.close();
				return Integer.parseInt(tokens[1]);
	    	}
	    	catch (Exception e){
	    	     System.err.println("Error: " + e.getMessage());
	    	}
			return 0;
		}
		
		public static int getHeightFromFile(String levelPath){
			try{
				
				FileInputStream fstream = new FileInputStream(levelPath);
				DataInputStream in = new DataInputStream(fstream);
	    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    		String strLine;
	    		strLine = br.readLine();  
				String[] tokens = strLine.split(" ");
				br.close();
				return Integer.parseInt(tokens[0]);
	    	}
	    	catch (Exception e){
	    	     System.err.println("Error: " + e.getMessage());
	    	}
			return 0;
		}
}
