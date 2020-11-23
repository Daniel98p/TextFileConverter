package TextConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Converter {
    public static void convertFile(String path, String code, String finalPath, String finalCode) {
        int lineIndexer = 0;
        try {
            File handler = new File(path);
            FileWriter resultFileHandler = new FileWriter(finalPath);
            Scanner myReader = new Scanner(handler);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String newData = data;
                if (Reader.getLineIndexWithCode(path, code).contains(lineIndexer)) {
                    for (int i = 0; i < Reader.findFirstIndexOfCodeInLine(data, code).size(); i += 1) {
                        newData = newData.substring(0, Reader.findFirstIndexOfCodeInLine(data, code).get(i))
                                + finalCode + data.substring(Reader.findFirstIndexOfCodeInLine(data, code).get(i)
                                + finalCode.length());
                    }
                    resultFileHandler.write(newData + "\n");
                } else {
                    resultFileHandler.write(data + "\n");
                }
                lineIndexer += 1;
            }
            resultFileHandler.close();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no file path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}