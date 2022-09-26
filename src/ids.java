import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

public class ids  {
	 public static String getMd5(String input)
	 {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger no = new BigInteger(1, messageDigest);
	            String hashtext = no.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } 
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	 }
	 public static void addToFile(String input) throws IOException
	 {
		 FileWriter writer = new FileWriter("C:\\Users\\fener\\eclipse-workspace\\IDS\\src\\TestDirectory\\Checksum.txt");
		 File directoryPath = new File("C:\\Users\\fener\\eclipse-workspace\\IDS\\src\\TestDirectory");
		 String contents[] = directoryPath.list();
		 for(int i=0; i<contents.length; i++) {
//		    	System.out.println(contents[i]);
		    	writer.write(contents[i]+":"+(getMd5((contents)[i]))+"\n");
		 }
		    writer.close();	    
	 }
	 public static void checkMd5(int j)
	 {	 
		 Scanner path = new Scanner(System.in);
		 System.out.println("Enter Directory:");
		 String pa =path.nextLine(); 
		 File directoryPath = new File(pa);
		 //"C:\\Users\\fener\\eclipse-workspace\\IDS\\src\\TestDirectory"
		 String contents[] = directoryPath.list();   
		 while (j==1) {
			 try
			    {
			    
				  File file = new File (pa+"\\Checksum.txt"); 
			      Scanner scanner = new Scanner(file);  
			      

			      while(scanner.hasNextLine())
			      {
			    	
			    	 for(int i=0; i<contents.length; i++) {
			    		 if(scanner.findInLine(contents[i]+":"+(getMd5((contents)[i]))) != null)
			    		 {
					    		System.out.println("Not Changed");
					    		scanner.nextLine();
			    		 }
			    		 else {
			    			 System.out.println(contents[i]+"CHANGED");    			
			    			 scanner.nextLine();
			    			 break;
			    		 }	 
					 }  
			      }
			      scanner.close();    
			    }
			    catch(IOException e)
			    {
			      e.printStackTrace();
			    }
		}
	 }

	 public static void encrypFile() 
	 {
		 
		 
	 }

/*	ids() {
		 JFrame frame;
		 JButton button;
		 frame = new JFrame();
	     frame.setTitle("Basic Host IDS");
	     frame.setSize(500,500);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     JMenuBar mb = new JMenuBar();
	     JPanel panel = new JPanel(); 
	     JLabel label = new JLabel("Enter Directory");
	     JTextField tf = new JTextField(10); 
	     JButton startscan = new JButton("Start Scan");
	     JButton stopscan = new JButton("Stop Scan");
	     panel.add(label); 
	     panel.add(tf);
	     panel.add(startscan);
	     panel.add(stopscan);
	     frame.getContentPane().add(BorderLayout.SOUTH, panel);
	     frame.getContentPane().add(BorderLayout.NORTH, mb);
	     frame.setVisible(true);
	} */
	 public static void main(String args[]) throws NoSuchAlgorithmException, IOException
	{	 

//	         new ids();
			 addToFile(null);
			 checkMd5(1);
	 }
}