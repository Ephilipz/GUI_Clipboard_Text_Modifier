package mainPackage.operations;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipBoard {
    public ClipBoard() {
    }

    public boolean setClipboardText(String target){
        StringSelection selection = new StringSelection(target);
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if(Toolkit.getDefaultToolkit().getSystemClipboard() == null)
            return false;
        clipBoard.setContents(selection, selection);
        return true;
    }

    public String getClipboardText(){
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
