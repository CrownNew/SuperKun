package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gameinterface extends JFrame implements KeyListener{					//�̳�JFrame�����Ӽ����ӿ�
	public JPanel jP;																//�½�һ��˽��JPanel������ΪjpĿ����Ϊ�˱��⿼��˫�������⣬��Ϊ��Ϸ������JFrame�ϻ���˸
	public int x1 = 0;
	public int y1 = 0;
	public int width1 = 3000;
	public int height1 = 800;	
	Map map = new Map();
	Kunkun kunkun = new Kunkun(map, this);
	public ArrayList<Element> eneryList = new ArrayList<Element>();
	public	ArrayList<Bullet> Bullets = new ArrayList<Bullet>();

	//�����JFrame���õķ���
	public Gameinterface()throws Exception{	
		this.setSize(1500, 800);									 				//���ô��ڴ�С
		this.setTitle("Superkun");													//���ô��ڱ���
		this.setResizable(false);													//���ô��ڴ�С���ɸı�
		this.setLayout(null);														//ȡ�����jfrm��Ĭ�ϲ��ֹ�����	
		this.setLocationRelativeTo(null);											//���ô��ڳ�������Ļ����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);								//�����رհ�ť����������
		this.setVisible(true);														//��������ʾ����
		jP = new game(this);														//�½�һ�����
		this.add(jP);																//�������ӵ�������
		this.addKeyListener(this);													//���Ӽ���	
	}
	//���췽����JPanel��������
	class game extends JPanel {
		public Image bg2 = new ImageIcon("image/bg2.png").getImage();			
		public JFrame jf;	
		//������Ϸ����
		public game(JFrame jf) {													//����
			this.setSize(1500,800);													//���ô��ڴ�С
			this.setLocation(0, 0);													//�������λ��
			this.setVisible(true);													//�������ʾ����	
		}
		//���컭�ʣ�����������kunkun
		public void paint(Graphics g) {			
			g.drawImage(bg2,-kunkun.x,y1,width1,height1,this);						//������
			kunkun.drawSelf(g);														//����kunkun���Լ��ķ���
			g.setFont(new Font("����",Font.ITALIC,30));								//�����������ʽ����С
			g.setColor(Color.RED);													//����������ɫ
			g.drawString("score:"+kunkun.score, 50, 30);							//����Ļ���Ͻ����ʵʱ�÷�
			//����ͼ
			eneryList = map.getList();
			for (int i = 0; i < eneryList.size(); i++) {									
				Element nowObject = eneryList.get(i);
				g.drawImage(nowObject.img, nowObject.x-kunkun.x, nowObject.y ,nowObject.width, nowObject.height, null);
			}				
			//���ӵ�
			Bullets = kunkun.getBullets();
			for (int i = 0; i < Bullets.size(); i++) {
				Bullet nowBullet = Bullets.get(i);
				if (nowBullet.getX() > 3000 || nowBullet.getY() < 0 || nowBullet.getY() > 800) {
					Bullets.remove(i);
				} else {
					nowBullet.drawpaintSelf(g);
				}		
			}
			repaint();															//��kunkun���ڵ�ÿ��λ���ػ�kunkun		
		}
	}
	//����¼�
	public void keyPressed(KeyEvent e) {										
		kunkun.moveStart(e);
	}
	//�ɿ��¼�
	public void keyReleased(KeyEvent e) {										
		kunkun.moveStop(e);	
		kunkun.makeBullet(e);
	}
	public void keyTyped(KeyEvent e) {	
	}
}