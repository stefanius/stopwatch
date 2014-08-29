package app;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class CountdownApplet extends JApplet {
    protected JLabel label;
    protected Timer timer;
    protected NumberFormat format;
    protected AppListener listener;
    protected Countdown countdown;
    protected JTextField textfield;
    protected JButton button;
    protected boolean initialized = false;
    protected boolean runTimer = false;

    public void init() {
        if (!this.initialized ) {
            this.countdown = new Countdown();

            this.listener = new AppListener(this);
            this.buildGui();

            this.format = NumberFormat.getNumberInstance();
            this.format.setMinimumIntegerDigits(2);

            this.initialized = true;
        }

        if (this.runTimer) {
            this.buildTimer();
            this.timer.start();
        }
    }

    public void start() {
        super.start();
        init();
    }

    public void startTimer() {
        String rawTime = this.textfield.getText();

        if (rawTime.length() != 5) {
            return;
        }

        String[] splitted = rawTime.split(":");
        String mins = splitted[0];
        String secs = splitted[1];

        long lMins = Long.parseLong(mins) * 60000;
        long lSecs = Long.parseLong(secs) * 1000;

        this.countdown.setRemaining(lMins + lSecs);


        this.runTimer = true;
        this.countdown.setLastUpdate(System.currentTimeMillis());
        this.init();
    }

    protected void buildGui() {
        this.label = new JLabel();
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setOpaque(false);
        this.label.setBackground(Color.black);

        this.textfield = new JTextField();
        this.textfield.setText("00:15");

        this.button = new JButton();
        this.button.setText("Start!");
        this.button.addMouseListener(this.listener);

        this.getContentPane().add(this.textfield, BorderLayout.NORTH);
        this.getContentPane().add(this.label, BorderLayout.CENTER);
        this.getContentPane().add(this.button, BorderLayout.SOUTH);
    }

    protected void buildTimer() {
        this.timer = new Timer(1000, this.listener);
        this.timer.setInitialDelay(0);
    }

    public void updateDisplay() {
        if (this.runTimer) {
            long now = System.currentTimeMillis();
            long elapsed = now - this.countdown.getLastUpdate();
            this.countdown.setRemaining(this.countdown.getRemaining() - elapsed);

            this.countdown.setLastUpdate(now);

            if (this.countdown.getRemaining() < 0) {
                this.countdown.setRemaining(0);
            }

            int minutes = (int) (this.countdown.getRemaining() / 60000);
            int seconds = (int) (this.countdown.getRemaining() / 1000) - (minutes * 60) ;
            label.setText(format.format(minutes) + ":" + format.format(seconds));

            if (this.countdown.getRemaining() == 0) {
                timer.stop();
                this.runTimer = false;
                this.setBackground(Color.red);
                this.repaint();
            }
        }
    }
} 