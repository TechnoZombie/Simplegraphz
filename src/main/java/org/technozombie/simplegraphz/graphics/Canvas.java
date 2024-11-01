package org.technozombie.simplegraphz.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Container of shapes
 */
public class Canvas {
    private static final int MIN_SIZE = 100;
    private static final int MARGIN = 10;
    private static final int LOCATION_OFFSET = 120;
    private static Canvas canvas = new Canvas();
    private ArrayList<org.technozombie.simplegraphz.graphics.Shape> shapes = new ArrayList<org.technozombie.simplegraphz.graphics.Shape>();
    private BufferedImage background;
    private JFrame frame;
    private CanvasComponent component;
    private JMenuBar menuBar = new JMenuBar();

    private Canvas() {
        component = new CanvasComponent();
        frame = new JFrame();
        frame.add(component);
        frame.pack();
        frame.setLocation(LOCATION_OFFSET, LOCATION_OFFSET);
        frame.setVisible(true);
        frame.setJMenuBar(menuBar);
    }

    public void setAppIcon(String filePath){
        try {
            BufferedImage icon = ImageIO.read(new File(filePath));
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAppTitle(String title){
        frame.setTitle(title);
    }

    public void addMenu(String menuName){
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
    }

    public void addItemToMenu(String menuName, String label, ActionListener action) {
        JMenuItem item = new JMenuItem(label);
        findOrCreateMenu(menuName).add(item);
        item.addActionListener(action);
    }

    public void addMenuSeparator(String menuName){
        findMenu(menuName).addSeparator();
    }

    /**
     * Finds an existing menu by name or creates a new one if it doesn't exist.
     *
     * @param menuName the name of the menu to find or create
     * @return the JMenu object
     */
    private JMenu findOrCreateMenu(String menuName) {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu.getText().equals(menuName)) {
                return menu;
            }
        }
        JMenu newMenu = new JMenu(menuName);
        menuBar.add(newMenu);
        return newMenu;
    }

    private JMenu findMenu(String menuName) {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu.getText().equals(menuName)) {
                return menu;
            }
        }
        return null;
    }


    /**
     * Provides access to the canvas singleton instance
     *
     * @return the canvas
     */
    public static Canvas getInstance() {
        return canvas;
    }

    /**
     * Pauses so that the user can see the picture before it is transformed.
     */
    public static void pause() {
        JFrame frame = getInstance().frame;
        if (frame == null) return;
        JOptionPane.showMessageDialog(frame, "Click Ok to continue");
    }

    /**
     * Takes a snapshot of the screen, fades it, and sets it as the background.
     */
    public static void snapshot() {
        Dimension dim = getInstance().component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, rect.width, rect.height);
        g.setColor(java.awt.Color.BLACK);
        getInstance().component.paintComponent(g);
        float factor = 0.8f;
        float base = 255f * (1f - factor);
        RescaleOp op = new RescaleOp(factor, base, null);
        BufferedImage filteredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        op.filter(image, filteredImage);
        getInstance().background = filteredImage;
        getInstance().component.repaint();
    }

    public void show(org.technozombie.simplegraphz.graphics.Shape s) {
        if (!shapes.contains(s)) {
            shapes.add(s);
        }
        repaint();
    }

    public void hide(org.technozombie.simplegraphz.graphics.Shape s) {
        if (shapes.contains(s)) {
            shapes.remove(s);
        }
        repaint();
    }

    /**
     * Repaints the screen
     */
    public void repaint() {
        if (frame == null) return;
        Dimension dim = component.getPreferredSize();
        if (dim.getWidth() > component.getWidth() || dim.getHeight() > component.getHeight()) {
            frame.pack();
        } else {
            frame.repaint();
        }
    }

    /**
     * Saves the current canvas to disk
     *
     * @param fileName the name of the file to save
     */

    public void saveToDisk(String fileName) {
        Dimension dim = component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        try {
            // Set high-quality rendering hints for better sharpness
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Fill background color and draw component
            g.setColor(java.awt.Color.WHITE);
            g.fill(rect);
            g.setColor(java.awt.Color.BLACK);
            component.paintComponent(g);
        } finally {
            g.dispose();
        }

        // Get the file extension or default to "png"
        String extension = "png";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1).toLowerCase();
        }

        try {
            ImageIO.write(image, extension, new File(fileName));
            System.out.println("Image saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save the image to " + fileName + ": " + e.getMessage());
        }
    }


    /*
    public void saveToDisk(String fileName) {
        Dimension dim = component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fill(rect);
        g.setColor(java.awt.Color.BLACK);
        component.paintComponent(g);
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        try {
            ImageIO.write(image, extension, new File(fileName));
        } catch (IOException e) {
            System.err.println("Was unable to save the image to " + fileName);
        }
        g.dispose();
    }*/

    /**
     * Adds a key listener to the canvas
     *
     * @param handler reference to the KeyListener object
     */
    public void addKeyListener(KeyListener handler) {
        frame.addKeyListener(handler);
    }

    /**
     * Adds a mouse listener to the canvas
     *
     * @param handler reference to the MouseListener object
     */
    public void addMouseListener(MouseListener handler) {
        frame.addMouseListener(handler);
    }

    class CanvasComponent extends JComponent {
        public void paintComponent(Graphics g) {
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(java.awt.Color.BLACK);
            if (background != null) {
                g.drawImage(background, 0, 0, null);
            }
            for (org.technozombie.simplegraphz.graphics.Shape s : new ArrayList<org.technozombie.simplegraphz.graphics.Shape>(shapes)) {
                Graphics2D g2 = (Graphics2D) g.create();
                s.paintShape(g2);
                g2.dispose();
            }
        }

        public Dimension getPreferredSize() {
            int maxx = MIN_SIZE;
            int maxy = MIN_SIZE;
            if (background != null) {
                maxx = Math.max(maxx, background.getWidth());
                maxy = Math.max(maxx, background.getHeight());
            }
            for (Shape s : shapes) {
                maxx = (int) Math.max(maxx, s.getX() + s.getWidth());
                maxy = (int) Math.max(maxy, s.getY() + s.getHeight());
            }
            return new Dimension(maxx + MARGIN, maxy + MARGIN);
        }
    }
}
