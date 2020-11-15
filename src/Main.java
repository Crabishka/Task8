
import util.SwingUtils;
import util.ArrayUtils;


import java.util.Locale;


public class Main {

    public static void main(String[] args) throws Exception {
       /* int[][] matrix = new int[][]{
                {0, 2, 3,},
                {1, 4, 7},
                {5, 6, 8}
        };
        System.out.println(Logic.Operation(matrix));
       */
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

         // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });

    }
}
