public class PuzzleSolver {
    public static int SolvePuzzle(int N, char[][] grid, Trie words) {
        int wordsFoundCount = 0;
        int allWordsCount = words.getCount();
        int minL = 3; // Minimum word length (4 letters)
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // The program stops if all words were found
                if (wordsFoundCount == allWordsCount) return wordsFoundCount;
                // Checking if any words start from current letter
                if (words.multiSearch(Character.toString(grid[row][col])) == -1) continue;

                String word = null;

                if ((word = checkRight(row, col, minL, N, grid, words)) != null){
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "RIGHT");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkLeft(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "LEFT");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkUp(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "UP");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkDown(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "DOWN");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkLeftUp(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "LEFT UP");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkRightUp(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "RIGHT UP");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkRightDown(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "RIGHT DOWN");
                    words.remove(word);
                    wordsFoundCount++;
                }

                if ((word = checkLeftDown(row, col, minL, N, grid, words)) != null) {
                    System.out.printf("\"%s\" in (%d, %d) going [%s]%n", word, col, row, "LEFT DOWN");
                    words.remove(word);
                    wordsFoundCount++;
                }
            }
        }
        return wordsFoundCount;
    }

    private static String checkDown(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (N - 1 - row < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        for (int i = row + 1; i < N; i++) {
            wordStart.append(grid[i][col]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkRight(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (N - 1 - col < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        for (int i = col + 1; i < N; i++) {
            wordStart.append(grid[row][i]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkUp(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (row < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        for (int i = row - 1; i >= 0; i--) {
            wordStart.append(grid[i][col]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkLeft(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (col < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        for (int i = col - 1; i >= 0; i--) {
            wordStart.append(grid[row][i]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkLeftUp(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (col < minL || row < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        int j = col - 1;
        for (int i = row - 1; i >= 0; i--) {
            if (j < 0) break;
            wordStart.append(grid[i][j]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
            j--;
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkRightUp(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (col > N - 1 - minL || row < minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        int j = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (j >= N) break;
            wordStart.append(grid[i][j]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
            j++;
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkRightDown(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (col > N - 1 - minL || row > N - 1 - minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        int j = col + 1;
        for (int i = row + 1; i < N; i++) {
            if (j >= N) break;
            wordStart.append(grid[i][j]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
            j++;
        }
        if (wordFound) return wordStart.toString();
        return null;
    }

    private static String checkLeftDown(int row, int col, int minL, int N, char[][] grid, Trie words) {
        if (col < minL || row > N - 1 - minL) return null;
        StringBuilder wordStart = new StringBuilder();
        wordStart.append(grid[row][col]);
        boolean wordFound = false;
        int j = row + 1;
        for (int i = col - 1; i >= 0; i--) {
            if (j >= N) break;
            wordStart.append(grid[j][i]);
            int searchResult = words.multiSearch(wordStart.toString());
            if (searchResult == -1) {
                break;
            }
            else if (searchResult == 1) {
                wordFound = true;
                break;
            }
            j++;
        }
        if (wordFound) return wordStart.toString();
        return null;
    }
}
