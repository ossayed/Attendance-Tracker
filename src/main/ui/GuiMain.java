package ui;

import javax.swing.*;

// code snippet from stack overflow
// main class for the gui
public class GuiMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Gui()::thing);
    }

}
