package animation.Game;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Scoreborad extends JPanel implements AnimatedObject {

    JLabel header = new JLabel();

    //score
    public int score = 0;

    /*
     * returns current score
     */
    public int getScore(){
        return score; 
    }
    
    /*
     * reads the HighScore file and gets the highest score if there's any,
     * else sets highscore to be 0
     * returns 0 or current highest score 
     */
    public int getHighScore(){
        int highS = 0 ; 
        
        File myFile = new File( "src/animation/HighScore.txt");
        Scanner reader;
        try {
            reader = new Scanner(myFile);
            
            if (reader.hasNext()){  // file is not empty 
                String line="" ; 
                while( reader.hasNextLine()){  
                    line = reader.nextLine();
                }
                String lastScore = line.split(": ")[line.split(": ").length -1]; 
                highS = Integer.parseInt(lastScore);
                return highS; 
                
                                    
            }else{
                highS = 0 ; 
            }
            
        }catch (FileNotFoundException e1) {
            e1.printStackTrace();
        };    
        return highS; 

    }

    /**
    * every time a large asteroid is hit, score increases by 20.  
    * updates the score dynamically and shows in the frame 
    */
    public void increaseScore(){
        score = score + 20; 
        this.header.setText("<html><h2>HighScore: " + getHighScore() +"     Score: "+ getScore() +"</h2></html>"); 
    }

    
    /*
     * construct the scoreboard 
    */
    public Scoreborad(){

        // Add all the GUI elements to the display.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));        
        header.setText("<html><h2>HighScore: " + getHighScore() +"     Score: "+ getScore() +"</h2></html>");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header); 
    
    }

    @Override
    public void paint(Graphics2D g) {
        
    }
    
    @Override
    public void nextFrame() {
        
    }

}




  
