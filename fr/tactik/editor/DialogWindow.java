package fr.tactik.editor;

import java.awt.GridLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
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
		 * @param LevelSave
		 * 				Set the save of the level.
		 */
		public static void setLevelPath(String levelSave){
			DialogWindow.levelPath = levelSave;
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
			
			bgPathField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(false == createBgDialog(owner)){
						setLevelBgPath(null);
					}
				}
			});

			nDialog.add(width);
			nDialog.add(widthField);
			nDialog.add(height);
			nDialog.add(heightField);
			

			nDialog.add(bgColor);
			nDialog.add(bgPathField);
			
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
			final JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Load background");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "png", "jpg", "JPEG", "PNG");
			    chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(false);
			if(chooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
				System.out.println(chooser.getSelectedFile().getAbsolutePath());
				setLevelBgPath(chooser.getSelectedFile().getAbsolutePath());
				return true;
			}
			return false;
			
		}
		
		/**
		 * This function create a save for a level.
		 */
		static boolean createSaveLevelDialog(final JFrame owner) {
			final JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save Leve");
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
}
