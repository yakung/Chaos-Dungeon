import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;


public class Boss extends GameObject{
	
	private float width = 96, height = 128;
	private int health = 700;
	private int walk = 1;

	private Animation walkright, walkleft;
	
	private boolean death = false;
	private Handler handler;
	Texture tex = Game.getInstance();
	Random rand = new Random();
	int r = rand.nextInt(9) + 6;
	int ri = rand.nextInt(100);
	public Boss(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		walkright = new Animation(10, tex.bosss[0], tex.bosss[1]);
		walkleft = new Animation(10, tex.bosss[2], tex.bosss[3]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += walk;
		if(health <= 0) {
			death = true;
		}
		Collision(object);
		walkright.runAnimation();
		walkleft.runAnimation();
	}
	private void Collision(LinkedList<GameObject> object) {
		
		for(int i=0; i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
				}
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					walk = 1;
					facing =1;
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					walk = -1;
					facing = -1;
				}
			}
			else if(tempObject.getID() ==  ObjectId.Skills) {
				if(getBounds().intersects(tempObject.getBounds())) {
					health = health - 20;

					if(death) {
							handler.removeObject(this);
							handler.addObject(new Item(tempObject.getX(), tempObject.getY() ,handler, ObjectId.Item));
					} 
				}
			}
			else if(tempObject.getID() ==  ObjectId.Fireball) {
				if(getBounds().intersects(tempObject.getBounds())) {
					health = health - 20;
					System.out.println(health);
					if(death) {
							handler.removeObject(this);
							handler.addObject(new Item(tempObject.getX(), tempObject.getY() ,handler, ObjectId.Item));
					} 
				}
			}
			else if(tempObject.getID() ==  ObjectId.WaterATK) {
				if(getBounds().intersects(tempObject.getBounds())) {
					health = health - 20;
					System.out.println(health);
					if(death) {
							handler.removeObject(this);
							handler.addObject(new Item(tempObject.getX(), tempObject.getY() ,handler, ObjectId.Item));
					} 
				}
			}
			else if(tempObject.getID() ==  ObjectId.Storm) {
				if(getBounds().intersects(tempObject.getBounds())) {
					health = health - 20;
					System.out.println(health);
					System.out.println(health);
					if(death) {
							handler.removeObject(this);
								handler.addObject(new Item(tempObject.getX(), tempObject.getY() ,handler, ObjectId.Item));			
					} 
				}
			}
			else if(tempObject.getID() ==  ObjectId.Visible) {
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					walk = 1;
					facing = 1;
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					walk = -1;
					facing = -1;
				}
			}
	}
}
	@Override
	public void render(Graphics g) {

			if(facing == 1) {
					walkright.drawAnimation(g, (int)x, (int)y, 96, 128);
				}
				else {
					walkleft.drawAnimation(g, (int)x, (int)y, 96,128);
				}
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		// �繡���Ҵ��������Ҥ��������㹡�ê��ͧ�Ҿ
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsHalf());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		//ź���������������
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsHalf() {
		return new Rectangle((int)x+46, (int)y, 4, 128 );
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
}
