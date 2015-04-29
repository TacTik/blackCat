package fr.tactik.editor;

import java.awt.GridLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class DialogWindow extends JDialog{
		private static final long serialVersionUID = 1L;
		private static short levelWidth = -1;
		private static short levelHeight = -1;
		private static String levelBackground = null;
		private static boolean levelCreated = false;
		
		public static short getLevelWidth() {
			return levelWidth;
		}

		public static void setLevelWidth(short mapWidth) {
			DialogWindow.levelWidth = mapWidth;
		}

		public static short getLevelHeight() {
			return levelHeight;
		}

		public static void setLevelHeight(short mapHeight) {
			DialogWindow.levelHeight = mapHeight;
		}

		public static String getLevelBackground() {
			return levelBackground;
		}

		public static void setLevelBgPath(String levelBgPath) {
			DialogWindow.levelBackground = levelBgPath; 
		}

		private DialogWindow(JFrame owner) {
			super(owner, "New Level settings",true);
		}

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
						setLevelBgPath("default");
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
						setLevelWidth(Short.parseShort(widthField.getText()));
						setLevelHeight(Short.parseShort(heightField.getText()));
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

		public static boolean isLevelCreated() {
			return levelCreated;
		}

		public static void setLevelCreated(boolean mapIsCreated) {
			DialogWindow.levelCreated = mapIsCreated;
		}
}
