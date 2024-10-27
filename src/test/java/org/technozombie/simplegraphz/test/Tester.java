package org.technozombie.simplegraphz.test;

import org.technozombie.simplegraphz.graphics.*;
import org.technozombie.simplegraphz.keyboard.Keyboard;
import org.technozombie.simplegraphz.keyboard.KeyboardEvent;
import org.technozombie.simplegraphz.keyboard.KeyboardEventType;
import org.technozombie.simplegraphz.keyboard.KeyboardHandler;
import org.technozombie.simplegraphz.mouse.Mouse;
import org.technozombie.simplegraphz.mouse.MouseEvent;
import org.technozombie.simplegraphz.mouse.MouseHandler;
import org.technozombie.simplegraphz.pictures.Picture;

public class Tester implements KeyboardHandler, MouseHandler {

    public static void main(String[] args) throws InterruptedException {

        Tester t = new Tester();
        t.test();

    }

    public void test() throws InterruptedException {

        Keyboard k = new Keyboard(this);
        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_SPACE);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event1);

        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(event2);

        Mouse m = new Mouse(this);

        Rectangle rect = new Rectangle(10, 10, 400, 400);
        rect.setColor(Color.BLACK);
        rect.draw();

        Rectangle smallRect = new Rectangle(50, 50, 100, 100);
        smallRect.setColor(Color.RED);
        smallRect.fill();

        Ellipse ellipse = new Ellipse(30, 30, 50, 60);
        ellipse.setColor(Color.YELLOW);
        ellipse.fill();

        Line line = new Line(200, 200, 300, 250);
        line.setColor(Color.BLUE);
        line.draw();

        Text text = new Text(20, 180, "Simple Graphz");
        text.setColor(Color.MAGENTA);
        text.draw();

        Picture pic = new Picture(20, 220, "https://pbs.twimg.com/profile_images/711052272923443200/1wBpdCko_400x400.jpg");
        pic.draw();

        Thread.sleep(2000);

        smallRect.translate(100, 0);
        ellipse.translate(20, 20);
        line.translate(20, -10);
        text.translate(20, 20);
        pic.translate(40, 0);

        Thread.sleep(2000);

        smallRect.grow(10, 10);
        ellipse.grow(-20, -20);
        line.grow(10, 10);
        text.grow(5, 5);
        pic.grow(-50, -50);

        Thread.sleep(2000);

        text.setText("HaCkEd bY TechnoZombie");
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        System.out.println("SPACE KEY PRESSED");

    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        System.out.println("SPACE KEY RELEASED");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       System.out.println(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
