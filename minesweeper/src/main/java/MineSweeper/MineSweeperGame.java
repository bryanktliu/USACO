package MineSweeper;

import java.util.LinkedList;
import java.util.Queue;

public class MineSweeperGame {

    public boolean ended;
    public long startTime;
    private Block[][] board;
    private static final int[] directionsx = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] directionsy = {-1, -1, -1, 0, 1, 1, 1, 0};
    public int rows;
    public int columns;
    public int mines;
    public int flags;

    public MineSweeperGame(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        generate(rows, columns, mines);
    }

    public Block[][] getBoard() {
        return board;
    }

    public void bothClicked(int x, int y) {
        if (board[y][x].revealed) {
            int flags = 0;
            for (int i = 0; i < 8; ++i) {
                int newx = x + directionsx[i];
                int newy = y + directionsy[i];
                if (newx >= 0 && newx < board[0].length && newy >= 0 && newy < board.length && board[newy][newx].flagged) {
                    ++flags;
                }
            }
            if (flags == board[y][x].number) {
                for (int i = 0; i < 8; ++i) {
                    int newx = x + directionsx[i];
                    int newy = y + directionsy[i];
                    if (newx >= 0 && newx < board[0].length && newy >= 0 && newy < board.length && !board[newy][newx].flagged) {
                        floodfill(newx, newy);
                    }
                }
            }
        }
    }

    public boolean isWin() {
        for (Block[] row : board) {
            for (Block block : row) {
                if (block.number >= 0 && !block.revealed) {
                    return false;
                }
            }
        }
        ended = true;
        return true;
    }

    public boolean isLose() {
        for (Block[] row : board) {
            for (Block block : row) {
                if (block.number == -1 && block.revealed) {
                    ended = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void generate(int rows, int columns, int mines) {
        board = new Block[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (count < mines) {
                    board[i][j] = new Block(-1, false, false);
                    ++count;
                } else {
                    board[i][j] = new Block(0, false, false);
                }
            }
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                int x = (int) (Math.random() * columns);
                int y = (int) (Math.random() * rows);
                Block temp = board[i][j];
                board[i][j] = board[y][x];
                board[y][x] = temp;
            }
        }
    }

    public void placeNumbers() {
        for (Block[] blocks : board) {
            for (Block block : blocks) {
                if (block.number != -1) {
                    block.number = 0;
                }
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j].number == -1) {
                    for (int k = 0; k < 8; ++k) {
                        int x = j + directionsx[k];
                        int y = i + directionsy[k];
                        if (x < 0 || x >= board[i].length || y < 0 || y >= board.length || board[y][x].number == -1) {
                            continue;
                        }
                        ++board[y][x].number;
                    }
                }
            }
        }
    }

    public void floodfill(int x, int y) {
        Queue<Integer> queuex = new LinkedList<>();
        Queue<Integer> queuey = new LinkedList<>();
        queuex.add(x);
        queuey.add(y);
        while (!queuex.isEmpty()) {
            int posx = queuex.poll();
            int posy = queuey.poll();
            board[posy][posx].revealed = true;
            if (board[posy][posx].number == 0) {
                for (int i = 0; i < 8; ++i) {
                    int newx = posx + directionsx[i];
                    int newy = posy + directionsy[i];
                    if (newx >= 0 && newx < board[0].length && newy >= 0 && newy < board.length && !board[newy][newx].revealed && !board[newy][newx].flagged) {
                        queuex.add(newx);
                        queuey.add(newy);
                    }
                }
            }
        }
    }

    public void setFlag(int x, int y) {
        if (board[y][x].flagged) {
            board[y][x].flagged = false;
            --flags;
        } else {
            board[y][x].flagged = true;
            ++flags;
        }
    }

    public static class Block {

        public int number;
        public boolean revealed;
        public boolean flagged;

        private Block(int number, boolean revealed, boolean flagged) {
            this.number = number;
            this.revealed = revealed;
            this.flagged = flagged;
        }
    }
}