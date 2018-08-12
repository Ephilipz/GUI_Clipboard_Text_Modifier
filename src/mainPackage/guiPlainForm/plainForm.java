package mainPackage.guiPlainForm;

import mainPackage.operations.ClipBoard;
import mainPackage.operations.modifyText.alignText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class plainForm {
    private JPanel mainPanel;
    private JFormattedTextField formattedTextField1;
    private JButton confirmLineLength;
    private JLabel lineLengthLabel;
    private String current = null;
    private int lineLength;
    private boolean confirmLineLengthClicked = false;

    public plainForm() {
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (confirmLineLengthClicked) {
                    ClipBoard clipBoard = new ClipBoard();
                    String clipBoardText = clipBoard.getClipboardText();
                    if (!clipBoardText.equals(current)) {
                        String fixedString = new alignText(lineLength, clipBoardText).getFixedString();
                        clipBoard.setClipboardText(fixedString);
                        int rgb = Color.HSBtoRGB((float) Math.random(), (float) 0.5, (float) 0.5);
                        Color color = new Color(rgb);
                        mainPanel.setBackground(color);
                        current = fixedString;
                    }
                }
            }
        });
        confirmLineLength.addActionListener(e -> {
            if (!formattedTextField1.getText().isEmpty() && formattedTextField1.getText() != null) {
                if (formattedTextField1.getText().trim().matches("\\d+"))
                    lineLength = Integer.parseInt(formattedTextField1.getText().trim());
                else
                    lineLength = formattedTextField1.getText().trim().length();
                confirmLineLengthClicked = true;
                formattedTextField1.setVisible(false);
                lineLengthLabel.setVisible(false);
                confirmLineLength.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Modify Text");
        frame.setContentPane(new plainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setMinimumSize(new Dimension(500, 200));
        frame.setVisible(true);
    }
}
