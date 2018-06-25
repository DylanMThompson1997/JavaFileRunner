import javax.swing.SwingUtilities;

public class RunCompiler implements Runnable {

	public void run() {
		new JCompiler();
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunCompiler());
	}

}
