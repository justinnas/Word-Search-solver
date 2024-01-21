import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataStorage {
    private Trie wordsTrie;
    private ArrayList<String> wordsList;
    private char[][] grid;
    private int N;

    public char[][] getGrid() {
        return grid;
    }

    public int getN() {
        return N;
    }

    public Trie getWordsTrie() {
        return wordsTrie;
    }

    public void readData(String fileName) {
        try {
            wordsTrie = new Trie(26);
            wordsList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            N = Integer.parseInt(reader.readLine());
            grid = new char[N][N];
            String line;
            for (int i = 0; i < N; i++) {
                line = reader.readLine();
                String[] letters = line.split(" ");
                for (int j = 0; j < N; j++) {
                    grid[i][j] = (letters[j].charAt(0));
                }
            }

            while ((line = reader.readLine()) != null) {
                wordsList.add(line);
                wordsTrie.insert(line);
            }
            reader.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void outputData() {
        StringBuilder xValues = new StringBuilder();
        xValues.append("        x ");
        for (int j = 0; j < N; j++) {
            xValues.append(String.format("%3d",j));
            if (j+1 != N) xValues.append(" ");
        }
        System.out.println("=".repeat(xValues.length()+6));
        System.out.println(xValues);
        System.out.print("      y +");
        System.out.println("-".repeat(xValues.length()-8));

        for (int i = 0; i < N; i++) {
            System.out.printf("%10s", "     " + i + " | ");
            for (int j = 0; j < N; j++) {
                System.out.printf("%4s", (grid[i][j] + " "));
            }
            System.out.println();
        }
        System.out.println("=".repeat(xValues.length()+6));
        System.out.print("[ Words to find ]: ");
        int wordsListSize = wordsList.size();
        for (int i = 0; i < wordsListSize; i++) {
            System.out.print(wordsList.get(i));
            if (i+1 != wordsListSize) System.out.print(", ");
            if ((i+1)%7 == 0) System.out.println();
        }
        System.out.println("\n");
    }
}
