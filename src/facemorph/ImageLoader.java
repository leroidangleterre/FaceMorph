package facemorph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * This class loads an image when triggered.
 * A GUI shall allow the user to select the requested image.
 *
 * @author arthu
 */
public class ImageLoader implements ActionListener {

    private ImagePanel panel;

    public ImageLoader(ImagePanel newPanel) {
        panel = newPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));
        int returnValue = chooser.showOpenDialog(panel);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            panel.loadImage(chosenFile);
        }
    }

}
