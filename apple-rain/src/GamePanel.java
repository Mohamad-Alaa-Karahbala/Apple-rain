import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    static final int unitSize = 20;
    static int score = 0;
    static int Delay = 15;
    static int appleX;
    static int appleY;
    static int player = unitSize;
    static boolean running = true;
        Random random = new Random();

    Timer timer = new Timer(Delay, this);

    GamePanel(){
        timer.start();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        spawnApples();
        repaint();

    }
    public void DelayController(int i){
        Delay = Delay + i;
        timer.setDelay(Delay);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        print(g);
    }
    public void spawnApples(){
        appleY = 0 ;
        appleX = random.nextInt((int) (SCREEN_HEIGHT/unitSize))*unitSize;
    }
    public void check(){
        int appleX = GamePanel.appleX;
        if (appleX >= player && appleX < player + 100 && appleY == SCREEN_HEIGHT){
            score++;
            spawnApples();
        }
    }
    public void print(Graphics g){
//        for (int i = 0; i < SCREEN_HEIGHT / unitSize; i++) {
//            g.drawLine(i * unitSize, 0, i * unitSize, SCREEN_HEIGHT);
//            g.drawLine(0, i * unitSize, SCREEN_WIDTH, i * unitSize);
//        }
    g.setColor(Color.red);
    g.fillOval(appleX , appleY ,unitSize, unitSize);
    g.setColor(Color.BLUE);
    g.fillRect(player, SCREEN_HEIGHT-unitSize, 100, unitSize);
        g.setFont(new Font("r",Font.ITALIC,40));
        FontMetrics fontMetrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+score,(SCREEN_WIDTH - fontMetrics.stringWidth("Score:  "+score)),SCREEN_HEIGHT-20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            check();
            if (!(appleY >= SCREEN_HEIGHT)) {
                appleY = appleY + 5;
            } else {
                appleY = appleY - unitSize;
                running = false;
            }
            repaint();
        }

    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_ENTER:
                    spawnApples();
                    score = 0;
                    running = true;
                    break;
                case KeyEvent.VK_DOWN:
                    if(running)
                    appleY = appleY + unitSize;
                    break;
                case KeyEvent.VK_RIGHT:
                    if(running)
                    player = player + 10;
                    break;
                case KeyEvent.VK_LEFT:
                    if(running)
                    player = player - 10;
                    break;
                case KeyEvent.VK_NUMPAD2:
                    DelayController(-50);
                    break;
                case KeyEvent.VK_NUMPAD8:
                    DelayController(100);
                    break;
            }

        }
    }

}