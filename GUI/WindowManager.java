package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Gym.DataBase;

public class WindowManager 
{
    public static void applyDefaultCloseAction(Window window) {
        WindowUtil.setCloseAction(window, () -> {
            System.out.println("Global close action executed for: " + window.getName());
        });
    }
    
    public static void resetSystem() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames)
            frame.dispose();
        new HomePage();
    }
    
    public class WindowUtil 
    {
        public static void setCloseAction(Window window, Runnable action) {
            if (window instanceof Frame) {
                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                    	DataBase.saveDataToFile();
                    	System.exit(0);
                    }
                });
            } else
                throw new IllegalArgumentException("Unsupported window type: " + window.getClass().getName());
        }
    }
}