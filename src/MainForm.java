import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import util.*;


public class MainForm extends JFrame {   // в чем отличие JFrame от Jdialog
    private JPanel MainPanel;
    private JPanel OutputPanel;
    private JTable InputTable;
    private JButton GetFromFileButton;
    private JPanel InputButtonPanel;
    private JButton GetRandomNumbersButton;
    private JButton ExecuteButton;
    private JLabel Output;
    private JPanel ExecutePanel;
    private JScrollPane InputPanel;
    private JPanel SaveButtonPanel;
    private JButton SaveIntoFileButton;

    private JFileChooser fileChooserOpen;  // выбор директории
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;  // выбор меню, но он удален



    public MainForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);  // не работает нормально из-за "пружин"
        this.setTitle("MainForm");      // устанавливает название формы
        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // устанавливает правила при нажатии на крестик
        this.setContentPane(MainPanel);  // без понятия, что делает, но без этого не работает
        this.pack();  // без понятия, что делает

        /*
        Честно украденные настройки элементов у Соломатина, потому что вручную все это прописывать скучно и долго.
        Я сделать и вынести настройки отдельных элементов в отдельные методы (например метод для таблиц, метод для кнопок),
        которые бы запускались в MainForm, но что-то не получилось не получилось
         */

        JTableUtils.initJTableForArray(InputTable, 100, true, true, true, true);
        // JTableUtils.initJTableForArray(OutputTable, 40, true, true, true, true);
        InputTable.setRowHeight(25);  // устанавливает высоту ряда
        //  OutputTable.setRowHeight(25);

        fileChooserOpen = new JFileChooser();           // не сильно понимаю, что твориться следующие 20 строчек,
        fileChooserSave = new JFileChooser();           // но это вроде работа с файлами
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");



        JTableUtils.writeArrayToJTable(InputTable, new int[][]{
                {0, 2, 3,},
                {1, 4, 7},
                {5, 6, 8}
        });

        Output.setText("");

        GetFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    if (fileChooserOpen.showOpenDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = fileFunсtion.readArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(InputTable, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        GetRandomNumbersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ArrayUtils.createRandomIntMatrix(
                            InputTable.getRowCount(), InputTable.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(InputTable, matrix);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });  // добавить выбор границы случайных чисел

        ExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(InputTable);
                    Logic logic = new Logic();
                    Output.setText(logic.Operation(matrix));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        SaveIntoFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(InputTable);
                    if (fileChooserSave.showSaveDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        Logic logic = new Logic();
                        fileFunсtion.writeIntoFile(file,logic.Operation(matrix));
                       /* FileWriter writer = new FileWriter(file, false);
                        writer.write(Logic.Operation(matrix));
                        writer.close();
                        */
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }


}


// МУСОР
/*





/*  buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
      */

/*  menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);
        */


       /* menuLookAndFeel = new JMenu();  прикольно, но бесполезно
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        /*   JTableUtils.writeArrayToJTable(tableInput, new int[][]{  можно и без этого
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        });
     */

