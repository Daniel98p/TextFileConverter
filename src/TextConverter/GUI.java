package TextConverter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUI implements ActionListener {
    private static JLabel pathLabel;
    private static JTextField path;
    private static JLabel codeLabel;
    private static JTextField code;
    private static JLabel finalPathLabel;
    private static JTextField finalPath;
    private static JLabel finalCodeLabel;
    private static JTextField finalCode;
    private static JButton submitButton;
    private static JLabel success;


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("File conversion data");
        frame.setSize(500, 500);
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
        panel.add(success);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String finalPathText = finalPath.getText();
        if (finalPathText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Path cannot be empty", "Empty path error", JOptionPane.ERROR_MESSAGE);
        }
        String pathText = path.getText();
        if (pathText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Path cannot be empty", "Empty path error", JOptionPane.ERROR_MESSAGE);
        }
        File tmpDir = new File(pathText);
        if (!tmpDir.exists() || tmpDir.isDirectory()){
            JOptionPane.showMessageDialog(null, "There is no file on your disk", "No file error", JOptionPane.ERROR_MESSAGE);
        }
        String finalCodeText = finalCode.getText();
        if (finalCodeText.length() > 5){
            JOptionPane.showMessageDialog(null, "Code length can't be bigger than 5", "To long code", JOptionPane.ERROR_MESSAGE);
        }
        String codeText = code.getText();
        if (codeText.length() > 5){
            JOptionPane.showMessageDialog(null, "Code length can't be bigger than 5", "To long code", JOptionPane.ERROR_MESSAGE);
        }
        Converter.convertFile(pathText, codeText, finalPathText, finalCodeText);
    }
}

//        String path = "example.txt";
//        String code = "df";
//        Scanner scan= new Scanner(System.in);
//        String finalPath = scan.next();
//        String finalCode = scan.next();
//        Converter.convertFile(path, code, finalPath, finalCode);

