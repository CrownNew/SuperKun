package Game;

import java.awt.Image;
import java.awt.Rectangle;

public class PinkZhuan extends Element{ 
	public PinkZhuan(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
	}
	public Rectangle getRect() {                                      //�õ����η�Χ
		return new Rectangle(x, y, 45,45);     //����˵�ǳ����εĹ��캯����x��yָ�������ϽǶ�Ӧ������
	}
}
