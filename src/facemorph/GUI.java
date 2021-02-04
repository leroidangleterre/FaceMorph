package facemorph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

/**
 * This window shows two panels, each of which displays an image (a real one or
 * a Fourier transform).
 * The tools allow to open an image from a file, to compute a forward or reverse
 * Fourier transform, and to display and save the result.
 *
 * @author arthu
 */
public class GUI extends JFrame {

    private static final int HEIGHT = 200;
    private static final int WIDTH = 610;

    private JMenuBar menubar;
    private final ImagePanel rightPanel;
    private final ImagePanel leftPanel;

    public GUI() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        leftPanel = new ImagePanel();
        rightPanel = new ImagePanel();
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        setupMenu();

        this.setJMenuBar(menubar);
        split.add(leftPanel);
        split.add(rightPanel);
        add(split);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

    private void setupMenu() {
        menubar = new JMenuBar();

        JMenu loadMenu = new JMenu("Load");
        JMenuItem loadLeft = new JMenuItem("Left image");
        loadLeft.addActionListener(new ImageLoader(leftPanel));
        loadMenu.add(loadLeft);
        JMenuItem loadRight = new JMenuItem("Right image");
        loadRight.addActionListener(new ImageLoader(rightPanel));
        loadMenu.add(loadRight);

        JMenu saveMenu = new JMenu("Save");
        JMenu transformMenu = new JMenu("Transform");

        menubar.add(loadMenu);
        menubar.add(saveMenu);
        menubar.add(transformMenu);
    }
}
