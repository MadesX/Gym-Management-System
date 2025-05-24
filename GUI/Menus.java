package GUI;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;

import Gym.DataBase;

class Menus extends MenuBar 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menus() {
        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        MenuItem newItem = new MenuItem("New");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new HomePage();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DataBase.saveDataToFile();
            	System.exit(0);
            }
        });
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        MenuItem aboutItem = new MenuItem("About");  
        aboutItem.addActionListener(e -> new Notification("About: The Mandalore Warriors Gym Application"));
        MenuItem helpItem = new MenuItem("Help");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		Desktop desktop = Desktop.getDesktop();
            		URI url = new URI("https://chatgpt.com/");
					desktop.browse(url);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
        helpMenu.add(aboutItem);
        helpMenu.add(helpItem);

        add(fileMenu);
        add(editMenu);
        add(helpMenu);
        
        // Add keyboard shortcuts
        newItem.setShortcut(new MenuShortcut(KeyEvent.VK_N));
        exitItem.setShortcut(new MenuShortcut(KeyEvent.VK_E));
        cutItem.setShortcut(new MenuShortcut(KeyEvent.VK_X));
        copyItem.setShortcut(new MenuShortcut(KeyEvent.VK_C));
        pasteItem.setShortcut(new MenuShortcut(KeyEvent.VK_V));
        aboutItem.setShortcut(new MenuShortcut(KeyEvent.VK_A));
    }
}