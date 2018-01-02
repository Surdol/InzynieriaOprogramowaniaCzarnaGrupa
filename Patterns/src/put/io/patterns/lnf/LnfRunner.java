package put.io.patterns.lnf;

import put.io.patterns.CustomRunner;

/**
 * Runner for LnF demo.
 * 
 * Required command line parameters: lnf (Look And Feel) class name
 * 
 * Useful parameter values:
 *     com.sun.java.swing.plaf.gtk.GTKLookAndFeel
 *     com.sun.java.swing.plaf.windows.WindowsLookAndFeel
 *     javax.swing.plaf.metal.MetalLookAndFeel
 *     com.sun.java.swing.plaf.motif.MotifLookAndFeel
 *     
 * There are also Eclipse run configurations prepared for the above values.
 * 
 */
public class LnfRunner extends CustomRunner {
	public static void main(String[] args) {
		checkAndRun(args, 1, "lnfName", new LnfRunner());
	}

	@Override
	public void run(String[] params) {
		// setting a system property for LnF
		// this can also be done from the command line, as
		//
		//     java -Dswing.defaultlaf=<lnfClassName> ...
		String lnfName = params[0];
		System.setProperty("swing.defaultlaf", lnfName);

		// creating GUI -- LnF is picked up magically
		// from inside controls creation code!
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	// navigate to this method to see details
                LookAndFeelDemo.createAndShowGUI();
            }
        });
	}

}
