package MineSweeper.ui;

import MineSweeper.MineSweeperGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MainFrame extends JFrame implements ActionListener {

    private BorderLayout layout = new BorderLayout();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu();
    private JMenuItem newGame = new JMenuItem();
    private JMenuItem gamePreferences = new JMenuItem();
    private JMenuItem exit = new JMenuItem();
    private BoardPanel boardPanel;
    private JLabel remaining = new JLabel();
    private int row = 16;
    private int column = 16;
    private int mine = 40;

    public MainFrame() {
        boardPanel = new BoardPanel(new MineSweeperGame(row,column,mine), remaining);
        format();
    }

    private void format() {
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(layout);
        this.setTitle("Mine Sweeper");
        menu.setText("Options");
        newGame.setText("New game");
        newGame.addActionListener(this);
        menu.add(newGame);
        gamePreferences.setText("Game Preferences");
        gamePreferences.addActionListener(this);
        menu.add(gamePreferences);
        exit.setText("Exit");
        exit.addActionListener(this);
        menu.add(exit);
        menuBar.add(menu);
        JPanel messagePanel = new JPanel();
        messagePanel.add(remaining);
        this.getContentPane().add(boardPanel, BorderLayout.CENTER);
        this.getContentPane().add(messagePanel, BorderLayout.NORTH);
        this.pack();
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gamePreferences) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3,2));
            JTextField rowField = new JTextField(String.valueOf(row));
            JTextField columnField = new JTextField(String.valueOf(column));
            JTextField mineField = new JTextField(String.valueOf(mine));
            panel.add(new JLabel("rows:"));
            panel.add(rowField);
            panel.add(new JLabel("columns:"));
            panel.add(columnField);
            panel.add(new JLabel("mines:"));
            panel.add(mineField);
            int val = JOptionPane.showConfirmDialog(this, panel, "Preference", JOptionPane.OK_CANCEL_OPTION);
            if(val == JOptionPane.OK_OPTION) {
                row = Integer.parseInt(rowField.getText());
                column = Integer.parseInt(columnField.getText());
                mine = Integer.parseInt(mineField.getText());
                boardPanel.newGame(new MineSweeperGame(row,column, mine));
                pack();
            }
        } else if (src == exit) {
            System.exit(0);
        } else if (src == newGame) {
            boardPanel.newGame(new MineSweeperGame(row,column,mine));
            boardPanel.repaint();
        }
    }
}
