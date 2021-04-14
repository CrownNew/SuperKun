package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Maininterface extends JFrame implements ActionListener{	
	JButton start;      										//������ť               	
	JButton about;
	JButton exit;	
	ImageIcon startbg;											//����ͼƬ����			
	ImageIcon aboutbg;
	ImageIcon exitbg;	
	JLabel jlab;												//��������
	ImageIcon bg1;
	//���췽��
	public Maininterface() {									
		this.newObject();										//���÷���
		this.setFrame();										//���÷���		
		JButton[] but={start,about,exit};                   	//����һ�������Ű�ť
		for(int i=0;i<=2;i++) {
			but[i].setBounds(300,300+i*90,250,70); 				//���ð�ť����λ�ü���С
			this.add(but[i]); 									//�Ѱ�ť��ӵ������� 
			but[i].addActionListener(this); 					//���Ӽ���
		}
		this.add(jlab);											//���������뵽������				
		this.setVisible(true);									//��������ʾ����		
	}
	//����new����ķ���
	public void newObject() {
		startbg = new ImageIcon("image/kaishiyouxibeijing.jpg");
		aboutbg = new ImageIcon("image/guanyuyouxibeijing.jpg");
		exitbg  = new ImageIcon("image/tuichuyouxibeijing.jpg");
		bg1     = new ImageIcon("image/zhujiemianbeijng.jpg");
		start   = new JButton("��ʼ��Ϸ",startbg);		
		about   = new JButton("������Ϸ",aboutbg);
		exit    = new JButton("�˳���Ϸ",exitbg);
		jlab = new JLabel(bg1);
	}
	//�����Դ��ڽ���һЩ���õķ���
	public void setFrame() {
		this.setSize(900, 640);									//���ô��ڴ�С
		this.setTitle("Superkun");
		this.setLocationRelativeTo(null);						//���ô��ڳ�������Ļ����
		this.setResizable(false);								//���ô��ڴ�С���ɸı�
		this.setLayout(null);									//ȡ������jfrm��Ĭ�ϲ��ֹ�����	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�����رհ�ť����������
		jlab.setOpaque(true);									//���ñ���ͼƬ��͸��
		jlab.setSize(900, 640);									//���ñ���ͼƬ��С
	}	
	//�¼�����ʱ�Ĵ����� 
	public void  actionPerformed(ActionEvent e) {                   
		JButton btn = (JButton)e.getSource();                   //������ť���󲢻�ȡ������Դ	
		if(btn==start) { 
			this.dispose();
			try {
				new Gameinterface();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} 
		else if(btn ==about) {
			//����һ����Ϣ�Ի���
			JOptionPane.showMessageDialog(this, "���̷�����������ƽ�ɫ�ƶ���������Ծ(�ɲȻ�����),�ո�����ʦ��\n�����ˣ�һ�� �½���", "������Ϸ", JOptionPane.CLOSED_OPTION);     //thisָ�������嵱��         �����JOptionPane.CLOSED_OPTION��ʾֻ�йر����ѡ��
		}
		else if(btn==exit) { 
			//����һ��ȷ�϶Ի���
			int n=JOptionPane.showConfirmDialog(this,"���������","Superkun",JOptionPane.YES_NO_OPTION);  
			//�������yes��ť���ֵĲ���
			if(n==JOptionPane.YES_OPTION) {
				this.dispose();                         //�ر��������
			}		
		}
	}
}