package com.dido.exercise;

import java.util.*;

public class Matrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] size = sc.nextLine().split("\\s");

        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);

        char[][] matrix = new char[r][c];

        for (int i = 0; i < r; i++) {

            String[] chars = sc.nextLine().split("\\s");

            for (int j = 0; j < c; j++) {
                matrix[i][j] = chars[j].charAt(0);
            }
        }

        char fillChar = sc.nextLine().charAt(0);
        String[] rowCols = sc.nextLine().split("\\s");
        int startRow = Integer.parseInt(rowCols[0]);
        int startCol = Integer.parseInt(rowCols[1]);

//        printMatrix(matrix);

        fillMatrix(matrix, fillChar, startRow, startCol);

        printMatrix(matrix);
    }

    static class PositionEntry {

        private Integer row;
        private Integer col;

        public PositionEntry() {
        }

        public PositionEntry(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }

        public Integer getRow() {
            return row;
        }

        public void setRow(Integer row) {
            this.row = row;
        }

        public Integer getCol() {
            return col;
        }

        public void setCol(Integer col) {
            this.col = col;
        }
    }

    public static void fillMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {

        char startChar = matrix[startRow][startCol];

        Map<Integer, List<Integer>> positionsMap = new LinkedHashMap<>();

        Deque<PositionEntry> stack = new ArrayDeque<>();
        stack.push(new PositionEntry(startRow, startCol));
        while(!stack.isEmpty()) {

            PositionEntry posEntry = stack.pop();

            if (matrix[posEntry.getRow()][posEntry.getCol()] == startChar) {

//                addToMap(positionsMap, posEntry.getRow(), posEntry.getCol());
                matrix[posEntry.getRow()][posEntry.getCol()] = fillChar;
                // check up
                int upPos = posEntry.getRow();

                if (--upPos >= 0 && matrix[upPos][posEntry.getCol()] == startChar) {

                    stack.push(new PositionEntry(upPos, posEntry.getCol()));
                }

                // check right
                int rightPos = posEntry.getCol();

                if (++rightPos < matrix[posEntry.getRow()].length
                        && matrix[posEntry.getRow()][rightPos] == startChar) {
                    stack.push(new PositionEntry(posEntry.getRow(), rightPos));
                }

                // check down
                int downPos = posEntry.getRow();

                if (++downPos < matrix.length && matrix[downPos][posEntry.getCol()] == startChar) {

                    stack.push(new PositionEntry(downPos, posEntry.getCol()));
                }

                // check left
                int leftPos = posEntry.getCol();

                if (--leftPos >= 0 && matrix[posEntry.getRow()][leftPos] == startChar) {
//                    addToMap(positionsMap, posEntry.getRow(), leftPos);

                    stack.push(new PositionEntry(posEntry.getRow(), leftPos));
                }


            }
        }

    }

    public static void addToMap(Map<Integer, List<Integer>> positionsMap, int row, int col) {

        if (positionsMap.containsKey(row)) {

            List<Integer> positions = positionsMap.get(row);
            if (!positions.contains(col)) {
                positions.add(col);
            }
        } else {
            List<Integer> positions = new LinkedList<>();
            positions.add(col);
            positionsMap.put(row, positions);
        }
    }

    public static void printMatrix(char[][] matrix) {

        System.out.println();
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%c",matrix[i][j]);
            }

            System.out.println();
        }
    }
}
