import javax.swing.*;

public class GameFrame extends JFrame {
     GameFrame(){
         this.add(new GamePanel());
         this.setTitle("Apple Rain");
         this.setResizable(false);
         this.pack();
         this.setVisible(true);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);
     }
}
