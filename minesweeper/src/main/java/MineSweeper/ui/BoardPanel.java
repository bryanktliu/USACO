package MineSweeper.ui;

import MineSweeper.MineSweeperGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class BoardPanel extends JPanel implements MouseListener {

    private int width;
    private int height;
    private MineSweeperGame game;
    private MineSweeperGame.Block[][] board;
    private boolean[] seen;
    private JLabel remaining;

    public BoardPanel(MineSweeperGame game, JLabel remaining) {
        this.remaining = remaining;
        newGame(game);
        this.addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (int i = 0; i < board.length; ++i) {
            g.drawLine(0, i * 20, width, i * 20);
        }
        for (int i = 0; i < board[0].length; ++i) {
            g.drawLine(i * 20, 0, i * 20, height);
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j].revealed) {
                    if (board[i][j].number == -1) {
                        drawMine(g, j, i);
                    } else if (board[i][j].number != 0) {
                        g.drawString(String.valueOf(board[i][j].number), 6 + j * 20, 15 + i * 20);
                    }
                } else if (board[i][j].flagged) {
                    drawFlag(g, j, i, board[i][j].number != -1 && game.ended);
                } else {
                    drawUnclicked(g, j, i);
                }
            }
        }
    }

    public void drawUnclicked(Graphics g, int x, int y) {
        g.setColor(Color.gray);
        g.fillRect(1 + x * 20, 1 + y * 20, 19, 19);
        g.setColor(Color.black);
    }

    public void drawFlag(Graphics g, int x, int y, boolean wrong) {
        g.setColor(Color.gray);
        g.fillRect(1 + x * 20, 1 + y * 20, 19, 19);
        g.setColor(Color.red);
        g.fillRect(6 + x * 20, 3 + y * 20, 8, 5);
        g.setColor(Color.black);
        g.drawLine(14 + x * 20, 3 + y * 20, 14 + x * 20, 16 + y * 20);
        if (wrong) {
            g.setColor(Color.red);
            g.drawLine(3 + x * 20, 3 + y * 20, 16 + x * 20, 16 + y * 20);
            g.drawLine(3 + x * 20, 16 + y * 20, 16 + x * 20, 3 + y * 20);
            g.setColor(Color.black);
        }
    }

    public void drawMine(Graphics g, int x, int y) {
        g.fillOval(5 + x * 20, 5 + y * 20, 10, 10);
        g.drawLine(3 + x * 20, 10 + y * 20, 17 + x * 20, 10 + y * 20);
        g.drawLine(10 + x * 20, 3 + y * 20, 10 + x * 20, 17 + y * 20);
        g.drawLine(5 + x * 20, 5 + y * 20, 15 + x * 20, 15 + y * 20);
        g.drawLine(15 + x * 20, 5 + y * 20, 5 + x * 20, 15 + y * 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (game.ended) {
            return;
        }
        int button = e.getButton();
        if (game.startTime == 0) {
            game.startTime = System.currentTimeMillis();
            if (board[e.getY() / 20][e.getX() / 20].number == -1) {
                end:
                for (int i = 0; i < board.length; ++i) {
                    for (int j = 0; j < board[i].length; ++j) {
                        if (board[i][j].number != -1) {
                            MineSweeperGame.Block temp = board[i][j];
                            board[i][j] = board[e.getY() / 20][e.getX() / 20];
                            board[e.getY() / 20][e.getX() / 20] = temp;
                            break end;
                        }
                    }
                }
            }
            game.placeNumbers();
        }
        seen[button] = true;
        if (seen[1] && seen[3]) {
            game.bothClicked(e.getX() / 20, e.getY() / 20);
        } else if (button == 1) {
            game.floodfill(e.getX() / 20, e.getY() / 20);
        } else if (button == 3 && !board[e.getY() / 20][e.getX() / 20].revealed) {
            game.setFlag(e.getX() / 20, e.getY() / 20);
            remaining.setText(String.valueOf(game.mines - game.flags));
        }
        repaint();
        if (game.isLose()) {
            for (MineSweeperGame.Block[] blocks : board) {
                for (MineSweeperGame.Block block : blocks) {
                    if (block.number == -1 && !block.flagged) {
                        block.revealed = true;
                    }
                }
            }
            int val = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "You Lose.", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (val == JOptionPane.OK_OPTION) {
                newGame(new MineSweeperGame(game.rows, game.columns, game.mines));
                repaint();
            }
        } else if (game.isWin()) {
            for (MineSweeperGame.Block[] blocks : board) {
                for (MineSweeperGame.Block block : blocks) {
                    if (block.number == -1) {
                        block.flagged = true;
                    }
                }
            }
            remaining.setText("0");
            int time = (int) (System.currentTimeMillis() - game.startTime) / 1000;
            int val = JOptionPane.showConfirmDialog(this, "Time Taken: " + time + " seconds.\nDo you want to play again?", "You Win!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (val == JOptionPane.OK_OPTION) {
                newGame(new MineSweeperGame(game.rows, game.columns, game.mines));
                repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        seen[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void newGame(MineSweeperGame game) {
        this.remaining.setText(String.valueOf(game.mines));
        this.seen = new boolean[4];
        this.game = game;
        this.board = game.getBoard();
        width = board[0].length * 20;
        height = board.length * 20;
        this.setSize(width, height);
    }
}

