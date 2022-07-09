package gui;
import areacalculator.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Polygon;
import javax.swing.JLabel;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements MouseMotionListener {
	public int vis = 0;
	public int itg=0;
	private Angle poolygon = new Angle();
	private int verticesSize = 22;
	private Rectangle[] vertices = new Rectangle[4]; 
	private Polygon poly;
	private int currVertexIndex = -1;
	public Color maincol = new Color(213, 213, 32);
	public Color backgroundcol = new Color(0, 0, 0);
	private float polygonWidth = 2;
	private JLabel areaLabel = new JLabel();
	private JButton reset = new JButton("RESET");
	private final JLabel lblNewLabel = new JLabel("X       Y");
	private final JButton btnNewButton = new JButton("Show/Hide");
	private JLabel coord0;
	private JLabel coord1;
	private JLabel coord2;
	private JLabel coord3;
	private JLabel coord4;
	private JLabel totallbl;
	private JLabel arealbl;
	private JLabel lb1;
	private JLabel lb2;
	private JLabel lb3;
	private JLabel lb4;
	private JLabel lb5;
	private int getVertexIndex(int x, int y) { // This functions returns the index of vertexSquare that contains (x,y)
		// else it returns -1
		for (int i = 0; i < 4; i++) {
			if (vertices[i].contains(x, y)) {
				return i;
			}
		}

		return -1;
	}
	public DrawPanel(JFrame jFrame)
	{

		setSize(1000, 600);
		setBackground(backgroundcol);
		int xPts[] = {(getWidth()/2-300),(getWidth()/2-100),(getWidth()/2-100),(getWidth()/2-300)};
		int yPts[] = {(getHeight()/2-100),(getHeight()/2-100),(getHeight()/2+100),(getHeight()/2+100)};
		poolygon.setXs(xPts);
		poolygon.setYs(yPts);
		poolygon.changePoint();
		poly = new Polygon(poolygon.getXs(), poolygon.getYs(), 4);
		setLayout(null);
		areaLabel.setLocation(0, 0);
		areaLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		areaLabel.setForeground(Color.RED);
		areaLabel.setBackground(backgroundcol);
		areaLabel.setSize(310,38);
		add(areaLabel);
		reset.setLocation(320, 0);
		reset.setFont(new Font("Century Gothic", Font.BOLD, 30));
		reset.setForeground(Color.WHITE);
		reset.setBackground(backgroundcol);
		reset.setSize(122,38);
		add(reset);
		for (int i = 0; i < 4; i++) // This forLoop sets the initial locations(x,y) of all the vertices
		{
			Rectangle r = new Rectangle();
//			r.setForeground(backgroundcol);
			r.setBounds((int) (poolygon.getXpt(i) - verticesSize * 0.5), (int) (poolygon.getYpt(i) - verticesSize * 0.5),
					verticesSize, verticesSize);
			vertices[i] = r;
		}
		
areaLabel.setText(" Area - " + poolygon.getArea() + " unit\u00B2" );
		
		reset.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	jFrame.getContentPane().removeAll();
    	    	Info info = new Info(jFrame,0);
    	    	jFrame.getContentPane().add(info);
    	    	
    	   	}
    	    
    	});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me)
			{
				int x = me.getX();
				int y = me.getY();
				currVertexIndex = getVertexIndex(x,y);
				if (getVertexIndex(x, y) >= 0) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		addMouseMotionListener(this);
		
		JButton maxbtn = new JButton("MAX/MIN");
		maxbtn.setForeground(Color.WHITE);
		maxbtn.setBackground(backgroundcol);
		maxbtn.setFont(new Font("Century Gothic", Font.BOLD, 20));
		maxbtn.setBounds(479, 0, 143, 38);
		maxbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(itg==0)
				{
					jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					itg=1;
				}
				else if(itg==1)
				{
					jFrame.setSize(1000,600);
					itg=0;
				}
				
				
			}
		});
		add(maxbtn);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(730, 85, 137, 24);
		
		add(lblNewLabel);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(backgroundcol);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vis==0)
				{
					lblNewLabel.setVisible(false);
					coord0.setVisible(false);
					coord1.setVisible(false);
					coord2.setVisible(false);
					coord3.setVisible(false);
					coord4.setVisible(false);
					totallbl.setVisible(false);
					arealbl.setVisible(false);
					lb1.setVisible(false);
					lb2.setVisible(false);
					lb3.setVisible(false);
					lb4.setVisible(false);
					lb5.setVisible(false);
					vis=1;
				}
				else
				{
					vis=0;
//					showdimen();
					lblNewLabel.setVisible(true);
					coord0.setVisible(true);
					coord1.setVisible(true);
					coord2.setVisible(true);
					coord3.setVisible(true);
					coord4.setVisible(true);
					totallbl.setVisible(true);
					arealbl.setVisible(true);
					lb1.setVisible(true);
					lb2.setVisible(true);
					lb3.setVisible(true);
					lb4.setVisible(true);
					lb5.setVisible(true);
					
					
				}
				
			}
		});
		btnNewButton.setBounds(664, 0, 159, 39);
		
		add(btnNewButton);
		
		coord0 = new JLabel("New label");
		coord0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coord0.setForeground(Color.WHITE);
		coord0.setBounds(730, 120, 285, 14);
		add(coord0);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(0, 0, 49, 14);
		add(lblNewLabel_6);
		
		coord1 = new JLabel("New label");
		coord1.setForeground(Color.WHITE);
		coord1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coord1.setBounds(730, 170, 285, 14);
		add(coord1);
		
		coord2 = new JLabel("New label");
		coord2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coord2.setForeground(Color.WHITE);
		coord2.setBounds(730, 220, 285, 14);
		add(coord2);
		
		coord3 = new JLabel("New label");
		coord3.setForeground(Color.WHITE);
		coord3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coord3.setBounds(730, 270, 285, 14);
		add(coord3);
		
		coord4 = new JLabel("New label");
		coord4.setForeground(Color.WHITE);
		coord4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coord4.setBounds(730, 320, 117, 14);
		add(coord4);
		
		totallbl = new JLabel("New label");
		totallbl.setForeground(Color.BLUE);
		totallbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totallbl.setBounds(872, 303, 170, 14);
		add(totallbl);
		
		arealbl = new JLabel("New label");
		arealbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		arealbl.setForeground(Color.YELLOW);
		arealbl.setBounds(710, 345, 250, 63);
		add(arealbl);
		
		lb1 = new JLabel("A");
		lb1.setForeground(Color.GREEN);
		lb1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb1.setBounds(700, 122, 20, 14);
		add(lb1);
		
		lb2 = new JLabel("B");
		lb2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb2.setForeground(Color.GREEN);
		lb2.setBounds(700, 172, 49, 14);
		add(lb2);
		
		lb3 = new JLabel("C");
		lb3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb3.setForeground(Color.GREEN);
		lb3.setBounds(700, 222, 49, 14);
		add(lb3);
		
		lb4 = new JLabel("D");
		lb4.setForeground(Color.GREEN);
		lb4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb4.setBounds(700, 272, 49, 14);
		add(lb4);
		
		lb5 = new JLabel("A");
		lb5.setForeground(Color.GREEN);
		lb5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb5.setBounds(700, 322, 49, 14);
		add(lb5);
		
	}
	public void mouseMoved(MouseEvent me) { 
		showdimen();
		int x = me.getX();
		int y = me.getY();

		if (getVertexIndex(x, y) >= 0) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else
			setCursor(Cursor.getDefaultCursor());
	}
	protected void paintg1(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.draw(poly);
		g2d.setFont(new Font("Century Gothic", Font.BOLD, 15));
		
		
		
	}
	protected void paintg2(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("A("+poolygon.getXpt(0) + "," + poolygon.getYpt(0)+")", (int) poolygon.getXpt(0) - 35,
				(int) poolygon.getYpt(0) - 15);
		g2d.drawString("B("+poolygon.getXpt(1) + "," + poolygon.getYpt(1)+")", (int) poolygon.getXpt(1) - 10,
				(int) poolygon.getYpt(1) - 15);
		g2d.drawString("C("+poolygon.getXpt(2) + "," + poolygon.getYpt(2)+")", (int) poolygon.getXpt(2) - 10,
				(int) poolygon.getYpt(2) + 25);
		g2d.drawString("D("+poolygon.getXpt(3) + "," + poolygon.getYpt(3)+")", (int) poolygon.getXpt(3) - 35,
				(int) poolygon.getYpt(3) + 25);
		g2d.drawString(poolygon.getAngle(0) + "\u00B0", (int) poolygon.getXpt(0) + 10,
				(int) poolygon.getYpt(0) + 25);
		g2d.drawString(poolygon.getAngle(1) + "\u00B0", (int) poolygon.getXpt(1) - 45,
				(int) poolygon.getYpt(1) + 25);
		g2d.drawString(poolygon.getAngle(2) + "\u00B0", (int) poolygon.getXpt(2) - 40,
				(int) poolygon.getYpt(2) - 15);
		g2d.drawString(poolygon.getAngle(3) + "\u00B0", (int) poolygon.getXpt(3) + 10,
				(int) poolygon.getYpt(3) - 15);
		g2d.drawString(poolygon.getLength(0) + " units", (int) poolygon.getXMid(0) - 30,
				(int) poolygon.getYMid(0) - 10);
		g2d.drawString(poolygon.getLength(1) + " units", (int) poolygon.getXMid(1) + 10,
				(int) poolygon.getYMid(1) + 5);
		g2d.drawString(poolygon.getLength(2) + " units", (int) poolygon.getXMid(2) -30,
				(int) poolygon.getYMid(2) + 20);
		
		g2d.drawString(poolygon.getLength(3) + " units", (int) poolygon.getXMid(3) - 80,
				(int) poolygon.getYMid(3) +5);
		g2d.setFont(new Font("Century Gothic", Font.BOLD, 25));
		areaLabel.setText(" Area - " + poolygon.getArea() + " unit\u00B2");
		
	}
	protected void paintg3(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		for(int i=0;i<4;i++)
		{
			Ellipse2D.Double e = new Ellipse2D.Double((int) poolygon.getXpt(i)- verticesSize * 0.25,(int) poolygon.getYpt(i)- verticesSize * 0.25,10,10);
			g2d.fill(e);
		}
	}
	
	
	
	public void showdimen()
	{
		int arr[]= new int[4];
		for(int i=0;i<3;i++)
		{
			arr[i]=poolygon.getXpt(i)*poolygon.getYpt(i+1)-poolygon.getYpt(i)*poolygon.getXpt(i+1);
		}
		arr[3]=poolygon.getXpt(3)*poolygon.getYpt(0)-poolygon.getYpt(3)*poolygon.getXpt(0);
		
		Graphics g = getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		coord0.setText( Integer.toString(poolygon.getXpt(0))+" "+Integer.toString(poolygon.getYpt(0))+"    "+ Integer.toString(poolygon.getXpt(0)*poolygon.getYpt(1))+" - "+Integer.toString(poolygon.getYpt(0)*poolygon.getXpt(1))+" = "+Integer.toString(arr[0]));
		coord1.setText( Integer.toString(poolygon.getXpt(1))+" "+Integer.toString(poolygon.getYpt(1))+"    "+ Integer.toString(poolygon.getXpt(1)*poolygon.getYpt(2))+" - "+Integer.toString(poolygon.getYpt(1)*poolygon.getXpt(2))+" = "+Integer.toString(arr[1]));
		coord2.setText( Integer.toString(poolygon.getXpt(2))+" "+Integer.toString(poolygon.getYpt(2))+"    "+ Integer.toString(poolygon.getXpt(2)*poolygon.getYpt(3))+" - "+Integer.toString(poolygon.getYpt(2)*poolygon.getXpt(3))+" = "+Integer.toString(arr[2]));
		coord3.setText( Integer.toString(poolygon.getXpt(3))+" "+Integer.toString(poolygon.getYpt(3))+"    "+ Integer.toString(poolygon.getXpt(3)*poolygon.getYpt(0))+" - "+Integer.toString(poolygon.getYpt(3)*poolygon.getXpt(0))+" = "+Integer.toString(arr[3]));
		coord4.setText( Integer.toString(poolygon.getXpt(0))+" "+Integer.toString(poolygon.getYpt(0)));
		
		for(int k=0;k<4;k++)
		{
			if(vis==0)
			{
				g2d.setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			Line2D.Double line = new Line2D.Double(730,135+k*50,780,120 +(k+1)*50);
			g2d.setColor(Color.BLUE);
			g2d.draw(line);
			}
		}
		for(int k=0;k<4;k++)
		{
			if(vis==0)
			{
				g2d.setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				Line2D.Double line1 = new Line2D.Double(780,135+k*50,730,120 +(k+1)*50);
				g2d.setColor(Color.RED);
				g2d.draw(line1);
			}
			
		}
		int sum = arr[0]+arr[1]+arr[2]+arr[3];
		totallbl.setText("Total = "+Integer.toString(sum));
		arealbl.setText("Area = "+"| "+Integer.toString(sum)+"/2 | = "+poolygon.getArea() + " unit\u00B2");
	}
	
	protected void paintComponent(Graphics g) { // Draws initial polygon and all of it's properties such as area,
		// lengths and angles on the Frame
		super.paintComponent(g);
		g.setColor(backgroundcol);

		for (int i = 0; i < 4; i++) {
			((Graphics2D) g).draw(vertices[i]);
		}
		g.setColor(maincol);
		paintg1(g);
		g.setColor(Color.GREEN);
		paintg2(g);
		g.setColor(Color.BLUE);
		paintg3(g);
		showdimen();
	}
	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		int x = me.getX();
		int y = me.getY();
		if (getBounds().contains(x, y)) {
			if (currVertexIndex >= 0) {
				Graphics g = getGraphics();
				g.setColor(backgroundcol);
				paintg1(g);
				paintg2(g);
				paintg3(g);
//				System.out.println("hello");
				poolygon.changePoint(x, y, currVertexIndex);
				vertices[currVertexIndex].x = (int) (poolygon.getXpt(currVertexIndex) - verticesSize * 0.5);
				vertices[currVertexIndex].y = (int) (poolygon.getYpt(currVertexIndex) - verticesSize * 0.5);
				poly = new java.awt.Polygon(poolygon.getXs(), poolygon.getYs(), 4);
				g.setColor(maincol);
				paintg1(g);
				g.setColor(Color.GREEN);
				paintg2(g);
				g.setColor(Color.BLUE);
				paintg3(g);
//				System.out.println("bye");
				showdimen();
//				g.dispose();
			}
		}
		
	}

	
}
