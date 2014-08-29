package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by stefanius on 21/08/14.
 */
public class AppListener implements ActionListener, MouseListener {

    CountdownApplet applet;

    public AppListener(CountdownApplet applet) {
        this.applet = applet;
    }

    public void actionPerformed(ActionEvent e) {
        this.applet.updateDisplay();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.applet.startTimer();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
