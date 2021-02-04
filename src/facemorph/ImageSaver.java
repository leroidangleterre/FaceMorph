package facemorph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author arthu
 */
public class ImageSaver implements ActionListener {

    private ImagePanel panel;

    public ImageSaver(ImagePanel newPanel) {
        panel = newPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));
        int returnValue = chooser.showSaveDialog(panel);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            System.out.println("Saving image to:");
            System.out.println(chosenFile + "");
            if (!chosenFile.exists()) {
                String newFileName = chosenFile.getPath() + ".bmp";
                chosenFile = new File(newFileName);
                try {
                    chosenFile.createNewFile();
                    panel.saveImage(chosenFile);
                } catch (IOException ex) {
                    Logger.getLogger(ImageSaver.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("We decide not to write: file already exists.");
            }
        }
    }
}
