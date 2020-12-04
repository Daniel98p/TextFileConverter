package TextConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUI implements ActionListener {
    /**
     * Klasa odpowiedzialna za stworzenie okna oraz wyświetlenie komunikatów błędu
     */
    private static JLabel pathLabel; // inicjalizacja elementów okna GUI
    private static JTextField path;
    private static JLabel codeLabel;
    private static JTextField code;
    private static JLabel finalPathLabel;
    private static JTextField finalPath;
    private static JLabel finalCodeLabel;
    private static JTextField finalCode;
    private static JButton submitButton;
    private static JLabel success;
    private static JLabel descriptionReadFile;
    private static JLabel descriptionTargetFile;
    private static JTextArea tenLinesOfReadFile;
    private static JTextArea tenLinesOfTargetFile;

    /**
     * Metoda odpowiedzialna za zapisanie 10 linii z pliku (potrzebne w GUI)
     *
     * @param path ścieżka do pliku, z którego chcemy zapisać 10 linii
     * @return ciąg znaków pobrany z pliku tekstowego
     */
    public static String saveTenLinesOfFile(String path) {
        int lineIndexer = 0;
        String result = "";
        try {
            File handler = new File(path);
            Scanner myReader = new Scanner(handler);
            while (myReader.hasNextLine()) {
                if (lineIndexer == 10) {
                    break;
                }
                String data = myReader.nextLine();
                result = result + data + "\n";
                lineIndexer += 1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
//            System.out.println("There is no file path on your disk");
//            e.printStackTrace();
        }
        return result;
    }

    /**
     * Metoda, w której odbywa się tworzenie elementów okna GUI, ustawienie położenia, rozmiarów itd...
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("File conversion data");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        finalPathLabel = new JLabel("Enter a path of the target file");
        finalPathLabel.setBounds(10, 20, 200, 25);
        panel.add(finalPathLabel);

        finalPath = new JTextField();
        finalPath.setBounds(180, 20, 165, 25);
        panel.add(finalPath);

        finalCodeLabel = new JLabel("Enter a target code");
        finalCodeLabel.setBounds(10, 60, 200, 25);
        panel.add(finalCodeLabel);

        finalCode = new JTextField();
        finalCode.setBounds(180, 60, 165, 25);
        panel.add(finalCode);

        pathLabel = new JLabel("Enter a path of the read file");
        pathLabel.setBounds(10, 100, 200, 25);
        panel.add(pathLabel);

        path = new JTextField();
        path.setBounds(180, 100, 165, 25);
        panel.add(path);

        codeLabel = new JLabel("Enter a read code");
        codeLabel.setBounds(10, 140, 200, 25);
        panel.add(codeLabel);

        code = new JTextField();
        code.setBounds(180, 140, 165, 25);
        panel.add(code);

        submitButton = new JButton("Submit");
        submitButton.setBounds(10, 180, 80, 25);
        submitButton.addActionListener(new GUI());
        panel.add(submitButton);

        success = new JLabel("");
        success.setBounds(10, 240, 300, 25);
        success.setForeground(Color.GREEN);
        panel.add(success);

        descriptionReadFile = new JLabel("READ FILE");
        descriptionReadFile.setBounds(10, 340, 100, 25);
        descriptionReadFile.setForeground(Color.RED);
        panel.add(descriptionReadFile);

        descriptionTargetFile = new JLabel("TARGET FILE");
        descriptionTargetFile.setBounds(410, 340, 100, 25);
        descriptionTargetFile.setForeground(Color.RED);
        panel.add(descriptionTargetFile);

        tenLinesOfReadFile = new JTextArea("");
        tenLinesOfReadFile.setBounds(10, 360, 350, 350);
        panel.add(tenLinesOfReadFile);

        tenLinesOfTargetFile = new JTextArea("");
        tenLinesOfTargetFile.setBounds(410, 360, 350, 350);
        panel.add(tenLinesOfTargetFile);

        frame.setVisible(true);

    }

    /**
     * Metoda, wywoływana po naciśnięciu przycisku, wyświetlenie komunikatów w przypadku błędów
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String finalPathText = finalPath.getText();
        String pathText = path.getText();
        File tmpDir = new File(pathText);
        String finalCodeText = finalCode.getText();
        String codeText = code.getText();
        if (finalPathText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Path of target file cannot be empty", "Empty path error", JOptionPane.ERROR_MESSAGE);
        } else if (pathText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Path cannot be empty", "Empty path error", JOptionPane.ERROR_MESSAGE);
        } else if (!tmpDir.exists() || tmpDir.isDirectory()) {
            JOptionPane.showMessageDialog(null, "There is no file on your disk", "No file error", JOptionPane.ERROR_MESSAGE);
        } else if (finalCodeText.length() > 5) {
            JOptionPane.showMessageDialog(null, "Code length can't be bigger than 5", "To long code", JOptionPane.ERROR_MESSAGE);
        } else if (codeText.length() > 5) {
            JOptionPane.showMessageDialog(null, "Code length can't be bigger than 5", "To long code", JOptionPane.ERROR_MESSAGE);
        } else if (codeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Code cannot be empty", "Empty code", JOptionPane.ERROR_MESSAGE);
        } else if (finalCodeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Final code cannot be empty", "Empty final code", JOptionPane.ERROR_MESSAGE);
        } else {
            Converter.convertFile(pathText, codeText, finalPathText, finalCodeText);
            success.setText("Congratulations you successfully convert a file!");
            String readFileData = saveTenLinesOfFile(path.getText());
            tenLinesOfReadFile.setText(readFileData);
            String readTargetFileData = saveTenLinesOfFile(finalPath.getText());
            tenLinesOfTargetFile.setText(readTargetFileData);
        }

    }
}

