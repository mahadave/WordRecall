
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author megadave
 */
public class wordList {

    /**
     * @param args the command line arguments
     */
    static ArrayList <wordMeaning> wl;
    public static void main(String[] args)
    {
        // TODO code application logic here
        wl = new ArrayList <wordMeaning> ();
        System.out.println("Loading wordlist");
        for(char i='a';i<='z';i++)
        {
            String fn = i+".txt";
            readFile(fn);
        
        }
        System.out.println("Loading complete...");
        
        makeFrame();
            
    }

    private static void readFile(String fn) {
        
        try{
        BufferedReader br = new BufferedReader(new FileReader(new File(fn)));
        String line = br.readLine();
        while(line!=null)
        {
            StringTokenizer st = new StringTokenizer(line," ");
            String word = st.nextToken();
            StringBuffer meaning= new StringBuffer("");
            while(st.hasMoreTokens())
            {
                meaning.append(" "+st.nextToken());
            }
            wl.add(new wordMeaning(word, meaning.toString()));
            line = br.readLine();
        }
        br.close();
        }catch(Exception e)
        {
            
        }
    }

    private static void printWL() {
       
        for(int i=0;i<wl.size();i++)
        {
            System.out.println(wl.get(i).toString());
            
        }
    }

    static int currentWordIndex=0;
    static JLabel wlabel ;
    static JTextArea mlabel ;
    static JButton showMeaning;
    static JButton nextWord;
    
    private static void makeFrame() {
        
        
            JFrame jf = new JFrame();
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setVisible(true);
            
            wlabel = new JLabel("");
            mlabel = new JTextArea(" ");
            mlabel.setLineWrap(true);
            showMeaning= new JButton("show meaning");
            nextWord = new JButton("start");
            showMeaning.setEnabled(false);
            mlabel.setEditable(false);
            
            nextWord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int ix = (int)(Math.random()*(wl.size()-1));
                currentWordIndex = ix;
                wlabel.setText(wl.get(ix).getWord());
                mlabel.setText("");
                showMeaning.setEnabled(true);
                nextWord.setText("next word");
            }
        });
            
            showMeaning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mlabel.setText(wl.get(currentWordIndex).getMeaning());
                mlabel.getLineWrap();
            }
        });
            
            
            jf.setLayout(new GridLayout(4,1));
            jf.add(wlabel);
            jf.add(showMeaning);
            jf.add(mlabel);
            jf.add(nextWord);
            jf.setTitle(" WordRecall by MEGADAVE");
            
            Dimension minSize = new Dimension(500,300);
            jf.setSize(minSize);
            
    }
}
class wordMeaning
{
    private String word;
    private String meaning;
    
    public wordMeaning(String w,String m)
    {
        word = w;
        meaning = m;
    }
    
    @Override
    public String toString()
    {
        return word+" , "+meaning;
    }

    String getWord() {
        return word;
    }
    
    
    String getMeaning() {
        return meaning;
    }
}