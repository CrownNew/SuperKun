package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;


public class Kunkun extends Thread{	
	public int life;																		//����kunkun������
	public int score;																		//�����÷�
	public int x,y;																		    //����x��y
	public int width,height;									 		 					//����width��height
	public int xSpeed,ySpeed;				 							 					//����speed
	public Map map;																			//������ͼ
	public Image img;											 							//����ͼƬ		
	public Gameinterface gi;																//������Ϸ����gi
	public boolean jumpFlag = true;															//�����ܷ���ԾΪtrue
	public boolean up = false,left=false,right=false; 										//��������������ʼΪfalse
	public String dir ="up";																//�����ַ�������dir��ֵΪup
	private String Dir_Right = "right",Dir_Left = "left",Dir_Up="up",Dir_Down="down";		//�����ĸ��ַ�������
	ArrayList<Bullet>Bullets = new ArrayList<Bullet>();     	         				    // ���������б����Bullets
	ArrayList<Bullet>RestBullets = new ArrayList<Bullet>();           						// ���������б����RestBullets
	//���캯����kunkun��������
	public Kunkun(Map map,Gameinterface gi) {	
		x = 0;													//һϵ�и�ֵ
		y = 600;
		score = 0;
		life = 1;
		xSpeed = 3;
		ySpeed = 2;
		width = 55;
		height = 55;
		this.map = map;
		this.gi = gi;
		img = new ImageIcon("image/jingzhikun.png").getImage();
		Bullets = new ArrayList<Bullet>();
		this.Gravity();
	}	
	//kunkun���Լ��ķ���
	public void drawSelf(Graphics g) {
		g.drawImage(img,x,y,width,height,null);								//������
		drawBullet(g);														//���ӵ�
		Kunkunmove();														//����kunkun��
		bulletHit_Element();												//�����ӵ����ϰ������ײ
		gameover();															//������Ϸ�����ķ���
	}	
	//kunkun�ƶ��Ĺ��򷽷�
	public void Kunkunmove() {	
			if(left){                                                    	//�����ʱ
				if (hit(Dir_Left)) {										//���ײ���ϰ���
					this.xSpeed = 0;										//X�����ٶ�Ϊ0����������ײ�ϵ�Ч��	
				}
				if (this.x > 0) {											//��ûײ���ϰ�����x>0
					this.x -= this.xSpeed;									//kunkun�����ƶ�
				}
				this.xSpeed = 3;											//�ָ�x���ϵ��ٶ�Ϊ3
			}
			if(right){       									  
				if (hit(Dir_Right)) {
					this.xSpeed = 0;
				}
				if (this.x <= 1450) {
					this.x += this.xSpeed;
				}
				this.xSpeed = 3;
			}
			if(up) {                 									 
				if(this.y <= 0) {											//��Y<=0
					this.ySpeed = 0;										//Y���ϵ��ٶ�Ϊ0
				}
				if(jumpFlag && !isGravity){									//������ڵ���
					jumpFlag=false;											//������Ծ
					new Thread(){											//�µ��߳�									
						public void run(){
							jump();											//������Ծ�ķ���
							jumpFlag=true;									//������Ծ
						}
					}.start();												//����start���������߳�
				}
			}
			if(y>=800) {													//���������Ļ�¶ˣ�����ֵ-1
				life--;
			}
			try {
				this.sleep(15);												//��Ϸ������µ��ٶ�
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	//�������ĺ���
	public void jump(){
		int jumpHeigh=0;													//�����ʼ�߶�Ϊ0												
		for (int i = 0; i < 100; i++) {										//ѭ����������Ծ�߶�Ϊ100
			this.y-=ySpeed;																										
			jumpHeigh++;
			if(hit(Dir_Up)){												//��ײ�����ϱߣ�����ѭ��ֹͣ��Ծ��ʵ�ֶ�ǽ
				break;				
			}
			try {
				Thread.sleep(6);											//��Ծʱ�������ٶ�
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		for (int i = 0; i <jumpHeigh; i++) {							
			this.y += ySpeed;
			if(hit(Dir_Down)){
				this.ySpeed=0;
			}
			try {
				Thread.sleep(6);											//��Ծʱ������ٶ�			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		this.ySpeed=2;														//��ԭ�ٶ�
	}
	//����Ƿ�����
	public boolean isGravity=false;   
	//�����߳�
	public void Gravity(){
		new Thread(){
			public void run(){			
				while(true){
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					while(true){
						if(!jumpFlag){										//���������Ծ������ѭ��			
							break;
						}					
						if(hit(Dir_Down)){									//����·���ײ������ѭ��
							break;
						}
						y+=ySpeed;																						
						try {
							sleep(10);										//����ÿһ��ySpeed���ʱ����
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
			}
		}.start();	
	}
	//kunkun��ש���Լ��������ײ
	public boolean hit(String dir) {										//��������ַ�����dir��
		Rectangle KunkunRec = null;											
		KunkunRec = new Rectangle(this.x, this.y-10,25, 30);				//kunkun����
		for (int i = 0; i < map.list.size(); i++) {
			Element obstacl = map.list.get(i);
			Rectangle rect = null;
			if (dir.equals("left")) {
				rect = new Rectangle(obstacl.x-x+5, obstacl.y-20,obstacl.width, obstacl.height);
			}
			if (dir.equals("right")) {
				rect = new Rectangle(obstacl.x-x-12, obstacl.y-20,obstacl.width, obstacl.height);
			}
			if (dir.equals("up")) {
				rect = new Rectangle(obstacl.x-x, obstacl.y,obstacl.width, obstacl.height);
			}
			if (dir.equals("down")) {
				rect = new Rectangle(obstacl.x-x, obstacl.y-30,obstacl.width, obstacl.height);
			}	
			if (KunkunRec.intersects(rect)) {
				   
				//kunkun��������ײ���Ե�����
				if(map.list.get(i) instanceof Basketball) {
					map.list.remove(i);
					score += 100;
					return false;
				}
				//kunkun��qiaoabiluo��ײ���Ե�qiaoboluo����Ϸ����
				else if(map.list.get(i) instanceof Qiaobiluo) {
					
					life ++;
					return false;
				}
				if(map.list.get(i) instanceof Jianci) {
					map.list.remove(i);
					score += 100;
					life --;
					return false;
				}
				else {						
					return true;
				}
			}

		}
		return false;
	}
	
	//�ӵ��͵�ͼ����ײ
	public void bulletHit_Element() {
		Rectangle bulletRec = null;
		for (int m = 0; m < Bullets.size(); m++) {
			Bullet bullet = Bullets.get(m);		 
			bulletRec = new Rectangle(bullet.getX(), bullet.getY()-10, 30, 30);				//�ӵ����εĴ�С		
			for (int i = 0; i < map.getList().size() && m < Bullets.size(); i++) {
				Element obstacl = map.getList().get(i);
				Rectangle rect = null;
				rect = new Rectangle(obstacl.x-x-20, obstacl.y-15, obstacl.width, obstacl.height);
				if(bulletRec.intersects(rect)) {
					if(map.list.get(i) instanceof PinkZhuan) {
						Bullets.remove(m);
					}
					else if (map.list.get(i) instanceof Qiaobiluo) {
						Bullets.remove(m);
					}
					else if (map.list.get(i) instanceof Brick) {
						Bullets.remove(m);			
					}
					else if (map.list.get(i) instanceof Jianci) {
						score +=100;
						Bullets.remove(m);
						map.list.remove(i);			
					}
				}
			}
		}
	}

	//����¼���������ʼ�ķ�����
	public void moveStart(KeyEvent e) { 								  
		if(e.getKeyCode()==KeyEvent.VK_UP){                               //����up
			dir = "up";
			if(hit(Dir_Down)){
				up = true;
			}else {
				up = false;
			}
			img = new ImageIcon("image/tiaokun.png").getImage();		  //����ʱ����ͼƬ
		
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){                             //����left
			dir = "left";
			left=true;
			img = new ImageIcon("image/left.gif").getImage();			  //����ʱ����ͼƬ
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){                            //����right
			dir = "right";
			right=true;
			img = new ImageIcon("image/right.gif").getImage();			  //����ʱ����ͼƬ
		}
	}
	//�ɿ��¼������������ķ�����
	public void moveStop(KeyEvent e) {                                   
		if(e.getKeyCode()==KeyEvent.VK_UP){                               //�ɿ�up
			up=false;
			img = new ImageIcon("image/jingzhikun.png").getImage();		  //�ɿ�ʱ�л�Ϊ��ֹͼ
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){                             //�ɿ�left
			left=false;
			img = new ImageIcon("image/jingzhikun.png").getImage();		  //�ɿ�ʱ�л�Ϊ��ֹͼ
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){                            //�ɿ�right
			right=false;
			img = new ImageIcon("image/jingzhikun.png").getImage();		  //�ɿ�ʱ�л�Ϊ��ֹͼ
		}
	}
	//���ӵ��ķ���
	public void drawBullet(Graphics g) {
		for (int i = 0; i < Bullets.size(); i++) {
			Bullets.get(i).drawpaintSelf(g);
		}
	}
	//�õ��ӵ�����ķ���
	public ArrayList<Bullet> getBullets() {
		return Bullets;
	}
	//�����ӵ��ķ���
	public void makeBullet(KeyEvent e) {                                
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {                      //����space������һ���ӵ�
			setBullets();
		}
	}
	//�����ӵ��ķ���
	public void setBullets() {                                      
		Bullets.add(new Bullet(x+30, y));                          //����Allbullet������������ҵķɻ���λ��
	}
	//��Ϸ�����ķ���
	public void gameover() {
		if (life >= 2) {
			gi.dispose();
			new Victory(score);
		}
		if(life <= 0) {
			gi.dispose();
			new Defeat(score);
		}
	}
}