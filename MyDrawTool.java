package repo;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


public class MyDrawTool extends JFrame implements ActionListener {

	JMenuItem item1,item2;
	JButton button;
	APanel panel;

	public static void main(String[] args)
	{
		MyDrawTool md=new MyDrawTool("MyDrawTool");
		md.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		md.setSize(500,500);
		md.setVisible(true);
	}

	 MyDrawTool(String title)
	 {
		super (title);
		panel=new APanel();
		JLabel label =new JLabel("");
		panel.add(label);
		MyMouseAdapter mma=new MyMouseAdapter(label);
		panel.addMouseListener(mma);
		panel.addMouseMotionListener(mma);
		getContentPane().add(panel);

		JMenuBar menuBar =new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu =new JMenu("描画");
		menuBar.add(menu);

		JMenu menu1 =new JMenu("描画1");
		menuBar.add(menu1);

		item1=new JMenuItem("長方形");
		item2=new JMenuItem("円");

		menu.add(item1);
		menu.add(item2);

		item1.addActionListener(this);
		item2.addActionListener(this);

		button =new JButton("clear");

		panel.add(button);

		button.addActionListener(this);
	}

	 int v=0,w=0,v1=0,w1=0;
	 int x[]=new int[50];
	 int y[]=new int[50];
	 int x1[]=new int[50];
	 int y1[]=new int[50];
	 int c=0,c1=0;
	 int tx,ty,tx1,ty1;
	 int d=0;
	 int kx[]=new int[50];
	 int ky[]=new int[50];
	 int kx1[]=new int[50];
	 int ky1[]=new int[50];

	 class APanel extends JPanel
	 {
		 Color s;
		 public void paintComponent(Graphics g)
		 	{
			 	super.paintComponent(g);
				g.setColor(s);
				if(d==2)
					{
						if(v==1)
							{
								for(int i=1;i<=c;i++)
									{
										g.drawRect(x[i],y[i],kx[i],ky[i]);
									}
							}
						if(w==1)
							{
								for(int i=1;i<=c1;i++)
									{
										g.drawOval(x1[i],y1[i],kx1[i],ky1[i]);
									}
							}
						d=0;
					}
		 	}
	 }

		public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==item1)
				{
					v=1;v1=1;
					w1=0;
				}
				else if(e.getSource()==item2)
				{
					w=1;w1=1;
					v1=0;
				}
				else if(e.getSource()==button)
				{
					v=0;w=0;v1=0;w1=0;
					for(int i=0;i<=c;i++)
					{
						x[i]=0;
						y[i]=0;
						kx[i]=0;
						ky[i]=0;
					}
					for(int i=0;i<=c1;i++)
					{
						x1[i]=0;
						y1[i]=0;
						kx1[i]=0;
						ky1[i]=0;
					}
					c=0;c1=0;
					repaint();
				}
			}

		class MyMouseAdapter extends MouseInputAdapter
		{
			JLabel label;
			MyMouseAdapter(JLabel label)
				{
					this.label=label;
				}
			public void mouseReleased(MouseEvent e)
			{
				if(d==1)
				{
					tx1=e.getX();
					ty1=e.getY();
					if(v==1&&v1==1)
						{
							x[c]=tx;
							y[c]=ty;
							kx[c]=tx1-tx;
							ky[c]=ty1-ty;
							d=2;
							repaint();
						}
					if(w==1&&w1==1)
						{
							x1[c1]=tx;
							y1[c1]=tx;
							kx1[c1]=tx1-tx;
							ky1[c1]=ty1-ty;
							d=2;
							repaint();
						}
				}
			}
			public void mouseDragged(MouseEvent e)
				{
					if(v==1&&v1==1&&d==0)
						{
							c++;
							tx=e.getX();ty=e.getY();
							d=1;
						}
					if(w==1&&w1==1&&d==0)
						{
							c1++;
							tx=e.getX();ty=e.getY();
							d=1;
						}
				}
		}



}
