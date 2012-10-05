import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class DrawDomino extends JPanel {
    Tableau dominoTableau;
    
    public DrawDomino(Tableau input) {
        super();
        setBackground(Color.WHITE);
	dominoTableau = input;
    }
    
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
		DrawDomino panel = new DrawDomino(fakeTableau);
        JFrame application = new JFrame();
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);           

        application.setSize(500, 400);
        application.setVisible(true);          

        super.paintComponent(g);
	
	g.drawRect(0,0,100,100);
    }

}