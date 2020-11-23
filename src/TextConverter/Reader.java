package TextConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<Integer> getLineIndexWithCode(String path, String code) {
        List<Integer> indexesOfLineWithCode = new ArrayList();
        int lineIndexer = 0;
        try {
            File handler = new File(path);
            Scanner myReader = new Scanner(handler);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(code)) {
                    indexesOfLineWithCode.add(lineIndexer);
                }
                lineIndexer += 1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no file path");
            e.printStackTrace();
        }
        return indexesOfLineWithCode;
    }

    public static List<Integer> findFirstIndexOfCodeInLine(String line, String code) {
        int foundIndex = line.indexOf(code);
        int cutIndexes = 0;
        List<Integer> indexesOfFirstCharInCode = new ArrayList();
        if (foundIndex == -1) {
            indexesOfFirstCharInCode.add(-1);
            return indexesOfFirstCharInCode;
        }
        do {
            if (indexesOfFirstCharInCode.size() == 0) {
                indexesOfFirstCharInCode.add(foundIndex);
            } else {
                indexesOfFirstCharInCode.add(foundIndex + cutIndexes);
            }
            cutIndexes += foundIndex + code.length();
            line = line.substring(foundIndex + code.length());
            foundIndex = line.indexOf(code);
//            System.out.println("ddddfg");
        } while (foundIndex != -1);
        return indexesOfFirstCharInCode;
    }

    public static void main(String[] args) {
        String path = "example.txt";
        String code = "df";
        Scanner scan= new Scanner(System.in);
        String finalPath = scan.next();
        String finalCode = scan.next();
        Converter.convertFile(path, code, finalPath, finalCode);
    }
}


