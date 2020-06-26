package smoketypist;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SmokeTypist implements KeyListener {
    private int x = 0;
    
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private Graphics g = null;
    private Random rand = new Random();
    private String[] words = new String[]{
        "gay","straight","slow","fast",
        "fine","the","word","on","gas",
        "butt","terminal","velocity","dead",
        "alive","going","I","was","top","of",
        "world","when","real","type","cheating",
        "at","city","cities","try","sad","weary",
        "place","wrong","right","correcting","officer",
        "games","dunk","piss","off","door","knob"
    };
    private ArrayList<String> werds = new ArrayList<>();
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            String w = werds.get(0);
            String ww = w;
            for(int i=0; i<w.length(); i++) {
                String v = null;
                v = w.substring(i,i+1);
                if(v.charAt(0) == e.getKeyChar()) {
                    ww = w.substring(0,i) + w.substring(i+1,w.length());
                }
            }
            werds.set(0, ww);
            if(werds.get(0).equals("")) {
                werds.remove(werds.get(0));
                ++x;
            }

            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 1200, 500);

            g.setColor(Color.GREEN);
            g.fillRect(0, 400, 1200, 200);

            displayList();

            if(werds.size() > 0) {
                Image image = ImageIO.read(getClass().getResourceAsStream("marlboroReds.jpg"));
                g.drawImage(image, 790, x*(int)((500.0-300.0)/(double)werds.size()), 100, 300, null);
            }            
        } catch(Exception ee) {
            ee.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void remix() {
        werds.clear();
        werds = new ArrayList<>();
        while(true) {
            int v = rand.nextInt(words.length);
            werds.add(words[v]);
            if(werds.size() == words.length) {
                break;
            }
        }
    }
    
    public SmokeTypist() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("Smoker v. 1.0");
        frame.setLayout(null);
        frame.setBounds(0, 0, 1200, 600);
        panel.setBounds(frame.getBounds());
        frame.add(panel);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = panel.getGraphics();

        construct();
    }
    
    public void construct() {
        remix();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.BLUE);
                g.fillRect(0, 0, 1200, 500);

                g.setColor(Color.GREEN);
                g.fillRect(0, 400, 1200, 200);
                while(true) {
                    try {

                        Thread.sleep(1000);

                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    if(werds.size() == 0) {
                        x = 0;

                        g.setColor(Color.BLUE);
                        g.fillRect(0, 0, 1200, 500);

                        g.setColor(Color.GREEN);
                        g.fillRect(0, 400, 1200, 200);

                        remix();
                    }

                    displayList();
                }
            }
        });
        t.start();
    }
    
    public void displayList() {
        g.setColor(Color.RED);
        int j = 0;
        int k = 0;
        for(int i=0; i<werds.size(); i++) {
            j+=50;
            g.drawString(werds.get(i), j, 450+k);
            if(i%10==1) {
                k+=30;
                j = 0;
            }
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SmokeTypist();
            }
        });
    }
}