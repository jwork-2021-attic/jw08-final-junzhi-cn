import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import my.runo.*;
import javax.swing.JFrame;

import com.anish.calabashbros.World;
import com.anish.screen.Screen;
import com.anish.screen.WorldScreen;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

public class Main extends JFrame implements KeyListener {

    private AsciiPanel terminal;
    private Screen screen;

    public Main() {
        super();
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, AsciiFont.MyTest);
        add(terminal);
        pack();
        screen = new WorldScreen();
        addKeyListener(this);
        repaint();
        

    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        while (true){
            try { 
                Thread.sleep(100);
                app.repaint();
            } catch (Exception e) { 
                System.out.println("err"); 
            }
        }
    }

}
