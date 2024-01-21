public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = new DataStorage();
        dataStorage.readData("data.txt");

        dataStorage.outputData();

        Trie wordsTrie = dataStorage.getWordsTrie();
        char[][] grid = dataStorage.getGrid();
        int N = dataStorage.getN();

        System.out.println("[ Words found ]:");
        if (PuzzleSolver.SolvePuzzle(N, grid, wordsTrie) == 0)
            System.out.println("<No words were found>");
    }
}