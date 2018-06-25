import java.awt.*;
import java.io.*;
import javax.swing.*;

public class JCompiler extends JFrame {
    
	private static final long serialVersionUID = 4815206756903932385L;
	
	public JCompiler() {
    	super("Java Compiler");
    	setLayout(new FlowLayout());
    	setSize(100,100);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setupMenu();
    	setVisible(true);		
	}
	
	private void setupMenu()  {
    		JFileChooser fileChooser = new JFileChooser(".");
    		int retval = fileChooser.showOpenDialog(JCompiler.this);
    		if (retval == JFileChooser.APPROVE_OPTION) {
    			File f = fileChooser.getSelectedFile();
    			String file = f.getAbsolutePath();
			if (file.toLowerCase().endsWith(".java")) {
				try {
					String filename = getJavaFilename(file);
					String command1 = String.format("javac %s", filename);
					Runtime.getRuntime().exec(command1, null, f.getParentFile());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.exit(1);
						e.printStackTrace();
					}
					String classname = changeFileNameToClassName(file);
					String command2 = String.format("cmd /c start cmd.exe /K java -cp . %s -role hub", classname);
					Runtime.getRuntime().exec(command2, null, f.getParentFile());
				} catch (IOException e) {
					System.err.println("Input error occurred.");
					e.printStackTrace();
				}
			System.exit(0);
			} else {
				System.out.println("File not valid");
				System.exit(0);
			}
    		} else {
			System.exit(0);
		}
	}
	
	public String getJavaFilename(String name) {
	     if (name == null)
	     {
	        throw new IllegalArgumentException("File Name is null");
	     }
	     String className = null;
	     if (name.toLowerCase().endsWith(".java"))
	     {
	        int startIndex = name.lastIndexOf("\\");
	        className = name.substring(startIndex+1, name.length());
	     }
	     return className;
	}
	
	public String changeFileNameToClassName(String name) {
	     if (name == null)
	     {
	        throw new IllegalArgumentException("File Name is null");
	     }
	     String className = null;
	     if (name.toLowerCase().endsWith(".java"))
	     {
	        int startIndex = name.lastIndexOf("\\");
	        className = name.substring(startIndex+1, name.length() - 5);
	     }
	     return className;
	}
}