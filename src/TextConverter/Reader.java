package TextConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa, w której odczytujemy z pliku i znajdujemy to co się nam przydaje (metody definiują co konkretnie)
 */
public class Reader {
    /**
     * Metoda odpowiedzialna za znalezienie indeksów linii, w których znajduje się szukany kod
     *
     * @param path ścieżka do pliku
     * @param code szukany kod znaków
     */
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
//            System.out.println("There is no file path on your disk");
//            e.printStackTrace();
        }
        return indexesOfLineWithCode;
    }

    /**
     * Metoda odpowiedzialna za znalezienie pierwszych indeksów kodu w linii
     *
     * @param line linia, którą analizujemy
     * @param code szukany kod znaków
     * @return lista indeksów
     */
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
}


