
import java.io.*;
import java.io.File;

import java.io.FileOutputStream;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.datatransfer.Clipboard;
import javax.swing.border.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.util.Scanner;


class Simpler extends JFrame implements ActionListener
{
	
JTextArea ta;
JButton b;
JTextField tf;
JScrollPane jsp;
BorderLayout bor;
JMenuBar mb;
JMenu file,edit,view;
JMenuItem news,open,save,close,cut,copy,paste,clear;
String message; 
JFileChooser jfc;
Border border;
JPanel jp;
Simpler() 
{
	
String message=JOptionPane.showInputDialog(null);
ta=new JTextArea();
ta.setEditable(true);
ta.append("public class "+message+"\n"+"{// Class Open \n\n");
ta.append("public static void main(String arg[]) \n");
ta.append("\t { //main open\n //write code here \n\n\n");
ta.append("\t } //main close \n ");
ta.append(" } //Class Close\n");
	Font f=new Font("Arial",Font.BOLD,20);
	ta.setFont(f);
tf=new JTextField();
b=new JButton("COMPILER");
mb=new JMenuBar();
ta.setBackground(new Color(0,153,153));
ta.setForeground(new Color(255,255,255));
ta.setWrapStyleWord(true);
ta.setTabSize(4);
ta.setLineWrap(true);
border=BorderFactory.createMatteBorder(10,10,20,10,Color.LIGHT_GRAY);
//ta.setBorder(border);
jsp=new JScrollPane(ta);
bor=new BorderLayout();
String s=System.getProperty("user.dir");
jfc=new JFileChooser(s);
jfc.setSelectedFile(new File(message));
jp=new JPanel();
file=new JMenu("File");
edit=new JMenu("Edit");
view=new JMenu("view");	
jsp.setViewportBorder(new LineBorder(Color.BLACK));

clear=new JMenuItem("CLEAR");
news=new JMenuItem("NEW");
open=new JMenuItem("OPEN");
save=new JMenuItem("SAVE");
close=new JMenuItem("CLOSE");
cut=new JMenuItem("CUT");
copy=new JMenuItem("COPY");
paste=new JMenuItem("PASTE");


file.add(clear);
file.add(news);
file.add(open);
file.add(save);
file.add(close);

edit.add(cut);
edit.add(copy);
edit.add(paste);

mb.add(file);
mb.add(edit);
mb.add(view);

file.setMnemonic(KeyEvent.VK_F);	
edit.setMnemonic(KeyEvent.VK_E);
view.setMnemonic(KeyEvent.VK_V);	

clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
news.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));


setLayout(new BorderLayout());
add(mb,BorderLayout.NORTH);
add(jsp,BorderLayout.CENTER);
add(b,BorderLayout.SOUTH);
add(jp,BorderLayout.WEST);

setDefaultCloseOperation(EXIT_ON_CLOSE);


b.addActionListener(this);
clear.addActionListener(this);

news.addActionListener(this);
open.addActionListener(this);
save.addActionListener(this);
close.addActionListener(this);
cut.addActionListener(this);
copy.addActionListener(this);
paste.addActionListener(this);




}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource()==b)
	{
		try
		{
			Process pro;
			pro=Runtime.getRuntime().exec("cmd /c start cmd");
			//System.out.println("Cmd opened");
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	else if(ae.getSource()==clear)
	{
ta.setText("");
	
	}	
	else if(ae.getSource()==news)
	{
		ta.setText("");
String s=JOptionPane.showInputDialog(null);

ta.append("public class "+s+"\n"+"{// Class Open \n\n");
ta.append("public static void main(String arg[]) \n");
ta.append("\t { //main open\n //write code here \n\n\n");
ta.append("\t } //main close \n ");
ta.append(" } //Class Close\n");

jfc.setSelectedFile(new File(s));

	
	}
	
	
	else if(ae.getSource()==open)
	{
		ta.setText(" ");
		//File f=new File("user.dir");
		//jfc.setCurrentDirectory(f);
		int n=jfc.showOpenDialog(this);
		
		if(n==JFileChooser.APPROVE_OPTION)
		{
			try
			{
				Scanner s=new Scanner(jfc.getSelectedFile());
				
				while(s.hasNext())
				{
					String str=s.nextLine();
					ta.append(str+"\n");
				JOptionPane.showMessageDialog(null,"File Opened");
				}
			}
			catch(Exception e)
			{}
			
		}
		
	}
	
	else if(ae.getSource()==save)
	{
		
		int a;
		a=jfc.showSaveDialog(this);
		
		if(a==JFileChooser.APPROVE_OPTION)
		{
			try(BufferedWriter bw=new BufferedWriter(new FileWriter(jfc.getSelectedFile()+".java")))
			{
				Scanner s=new Scanner(ta.getText());
				while(s.hasNext())
				{
					bw.write(s.nextLine());
					bw.newLine();
				}
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	else if(ae.getSource()==close)
	{
		System.exit(0);
	}
	
	
	else if(ae.getSource()==cut)
	{
		String str=ta.getSelectedText();
		Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection data=new StringSelection(str);
		clip.setContents(data,data);
		ta.replaceRange(str,ta.getSelectionStart(),ta.getSelectionEnd());
	}
	
	else if(ae.getSource()==copy)
	{
		String str=ta.getSelectedText();
		Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection data=new StringSelection(str);
		clip.setContents(data,data);
	}
	
	
	else if(ae.getSource()==paste)
	{
		try
		{
		Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();		
		Transferable cdata=clip.getContents(clip);
		
		if(cdata.isDataFlavorSupported(DataFlavor.stringFlavor))
		{
			String str=(String)(cdata.getTransferData(DataFlavor.stringFlavor));
		}
		}
		catch(Exception e)
		{ }	
	}
	
	
	
}

public static void main(String arg[]) throws IOException,InterruptedException
{
	Simpler s=new Simpler();
	
	s.setSize(700,600);
	s.setLocationRelativeTo(null);
	s.setResizable(true);
	s.setTitle("SIMPLER");
	s.setVisible(true);
	
}
}