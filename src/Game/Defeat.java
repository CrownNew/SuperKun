package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Defeat extends JFrame implements ActionListener{	
	JButton start;      										//������ť               	
	JButton exit;	
	ImageIcon startbg;											//����ͼƬ����			
	ImageIcon exitbg;	
	Image bg1;
	int score;
	//���췽��
	public Defeat(int score) {									
		this.newObject();										//���÷���
		this.setFrame();										//���÷���		
		this.score = score;
		JButton[] but={start,exit};                   	//����һ�������Ű�ť
		for(int i=0;i<=1;i++) {
			but[i].setBounds(320+i*650,620,240,70); 				//���ð�ť����λ�ü���С
			this.add(but[i]); 									//�Ѱ�ť���ӵ������� 
			but[i].addActionListener(this); 					//���Ӽ���
		}			
		this.setVisible(true);									//��������ʾ����		
	}
	//����new����ķ���
	public void newObject() {
		startbg = new ImageIcon("image/zailai1.png");
		exitbg  = new ImageIcon("image/tuichu1.png");
		bg1     = new ImageIcon("image/youxishibai.png").getImage();
		start   = new JButton("����һ��",startbg);		
		exit    = new JButton("�����˳�",exitbg);
	}
	//�����Դ��ڽ���һЩ���õķ���
	public void setFrame() {
		this.setSize(1500, 800);									//���ô��ڴ�С
		this.setTitle("�ػ�ʧ��");
		this.setLocationRelativeTo(null);						//���ô��ڳ�������Ļ����
		this.setResizable(false);								//���ô��ڴ�С���ɸı�
		this.setLayout(null);									//ȡ������jfrm��Ĭ�ϲ��ֹ�����	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�����رհ�ť����������
	}	      
	//�¼�����ʱ�Ĵ������� 
	public void  actionPerformed(ActionEvent e) {                   
		JButton btn = (JButton)e.getSource();                   //������ť���󲢻�ȡ������Դ	
		if(btn==start) { 
			this.dispose();
			try {
				new Gameinterface();
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		} 
		else if(btn==exit) { 
			//����һ��ȷ�϶Ի���
			int n=JOptionPane.showConfirmDialog(this,"��Ҫ���ػ��˼���������","�ر���Ϸ",JOptionPane.YES_NO_OPTION);    //showConfirmDialogȷ�϶Ի��򡣿��ڿ�
			//�������yes��ť���ֵĲ���
			if(n==JOptionPane.YES_OPTION) {
				this.dispose();                         //�ر��������
			}		
		}
	}
	public void paint(Graphics g) {
		g.drawImage(bg1,0,0,1500,800,null);
		start.repaint();
		exit.repaint();
		g.setFont(new Font("����",Font.ITALIC,60));           //������Ҫ�����һ��ʵ�������󣬲��ҶԶ�����е��ã���this����ָ���Ķ���                
		g.setColor(Color.GREEN);                              //��������00��ʾ��ʼλ��
		
		g.drawString("��ĳɼ���:"+score, 550,630);              //�������������score����λ��
	}
}