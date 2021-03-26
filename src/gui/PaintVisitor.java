package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import data.Ant;
import data.Bee;
import data.Centipede;
import data.Insect;
import data.Spider;
import process.InsectVisitor;

public class PaintVisitor implements InsectVisitor<Void> {

	private Graphics g;

	public static String HUNGER1 = "resources/icon/Faim1.png";
	public static String HUNGER2 = "resources/icon/Faim2.png";
	public static String HUNGER3 = "resources/icon/Faim3.png";
	public static String THIRST1 = "resources/icon/Soif1.png";
	public static String THIRST2 = "resources/icon/Soif2.png";
	public static String THIRST3 = "resources/icon/Soif3.png";
	
	public static int HUNGER_CASE = 0;
	public static int THIRST_CASE = 1;
	
	public static int INSECT_SIZE = 53;
	public static int ICON_HUNGER = 10;
	public static int ICON_THIRST = 20;
	public static int DY = 10;
	
	public static int LIFEBAR_POSITION = 15;
	public static int LIFEBAR_WIDTH = 50;
	public static int LIFEBAR_HEIGHT = 4;
	
	private String[] state;
	
	
	public PaintVisitor(Graphics graphics) {
		this.g = graphics;
	}

	@Override
	public Void visit(Bee insect) {
		String filename = "resources/images/guepe_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			bufferImage = rotate(bufferImage, insect.getDirection());
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);
			state = getLifeState(insect);
			
			double lifeRatio = insect.getCurrentHealth() / (double) insect.getMaxHealth();
			int ratio = (int) (lifeRatio * 100);
			
			//Life bar system
			g.drawRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					LIFEBAR_WIDTH, LIFEBAR_HEIGHT);
			g.fillRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					ratio / 2, LIFEBAR_HEIGHT);
			
			if(state[HUNGER_CASE] != null) {
			//Icon Hunger
			BufferedImage bufferIconHunger = ImageIO.read(new File(state[HUNGER_CASE]));
			g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_HUNGER,
					(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}

			if(state[THIRST_CASE] != null) {
			//Icon Thirst
			BufferedImage bufferImageThirst = ImageIO.read(new File(state[THIRST_CASE]));
			g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_THIRST,
					(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Void visit(Ant insect) {
		String filename = "resources/images/fourmi_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			bufferImage = rotate(bufferImage, insect.getDirection());
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);
			state = getLifeState(insect);
			
			double lifeRatio = insect.getCurrentHealth() / (double) insect.getMaxHealth();
			int ratio = (int) (lifeRatio * 100);
			
			//Life bar system
			g.drawRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					LIFEBAR_WIDTH, LIFEBAR_HEIGHT);
			g.fillRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					ratio / 2, LIFEBAR_HEIGHT);
			
			if(state[HUNGER_CASE] != null) {
				//Icon Hunger
				BufferedImage bufferIconHunger = ImageIO.read(new File(state[HUNGER_CASE]));
				g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_HUNGER,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
			if(state[THIRST_CASE] != null) {
				//Icon Thirst
				BufferedImage bufferImageThirst = ImageIO.read(new File(state[THIRST_CASE]));
				g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_THIRST,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Void visit(Spider insect) {
		String filename = "resources/images/araignee_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			bufferImage = rotate(bufferImage, insect.getDirection());
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);
			state = getLifeState(insect);
			
			double lifeRatio = insect.getCurrentHealth() / (double) insect.getMaxHealth();
			int ratio = (int) (lifeRatio * 100);
			
			//Life bar system
			g.drawRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					LIFEBAR_WIDTH, LIFEBAR_HEIGHT);
			g.fillRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					ratio / 2, LIFEBAR_HEIGHT);
			
			if(state[HUNGER_CASE] != null) {
				//Icon Hunger
				BufferedImage bufferIconHunger = ImageIO.read(new File(state[HUNGER_CASE]));
				g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_HUNGER,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
			if(state[THIRST_CASE] != null) {
				//Icon Thirst
				BufferedImage bufferImageThirst = ImageIO.read(new File(state[THIRST_CASE]));
				g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_THIRST,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Void visit(Centipede insect) {
		String filename = "resources/images/centipede.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			bufferImage = rotate(bufferImage, insect.getDirection());
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);
			state = getLifeState(insect);
			
			double lifeRatio = insect.getCurrentHealth() / (double) insect.getMaxHealth();
			int ratio = (int) (lifeRatio * 100);
			
			//Life bar system
			g.drawRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					LIFEBAR_WIDTH, LIFEBAR_HEIGHT);
			g.fillRect((int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate() - LIFEBAR_POSITION,
					ratio / 2, LIFEBAR_HEIGHT);
			
			if(state[HUNGER_CASE] != null) {
				//Icon Hunger
				BufferedImage bufferIconHunger = ImageIO.read(new File(state[HUNGER_CASE]));
				g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_HUNGER,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
			if(state[THIRST_CASE] != null) {
				//Icon Thirst
				BufferedImage bufferImageThirst = ImageIO.read(new File(state[THIRST_CASE]));
				g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + INSECT_SIZE - ICON_THIRST,
						(int) insect.getCurrentPosition().getOrdinate() - DY, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * This method is used to rotate insects sprite
	 * @param bimg
	 * @param angle
	 * @return a rotated BufferedImage
	 */
	public static BufferedImage rotate(BufferedImage bimg, double angle) {
		int w;
		int h;
		// width and height equals to max(width,height) to avoid image crop
		if (bimg.getHeight() > bimg.getWidth()) {
			w = bimg.getHeight();
			h = bimg.getHeight();
		} else {
			w = bimg.getWidth();
			h = bimg.getWidth();
		}

		BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
		Graphics2D graphic = rotated.createGraphics();
		graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
		graphic.drawImage(bimg, null, 0, 0);
		graphic.dispose();
		return rotated;
	}
	
	/***
	 * 
	 * @param insect
	 * @return an array of hunger and thirst state
	 */
	public static String[] getLifeState(Insect insect) {
		String[] states = new String[2];
		int maxh = insect.getMaxHunger();
		int curh = insect.getCurrentHunger();
		int maxt = insect.getMaxThirst();
		int curt = insect.getCurrentThirst();
		
		double ratioH = (double) curh / maxh;
		double ratioT = (double) curt / maxt;
		
		if (ratioH <= 0.25) {
			states[HUNGER_CASE] = HUNGER3;
		} else if (ratioH <= 0.5 && ratioH > 0.25) {
			states[HUNGER_CASE] = HUNGER2;
		} else if (ratioH >= 0.5 && ratioH < 0.75) {
			states[HUNGER_CASE] = HUNGER1;
		}
		
		if (ratioT <= 0.25) {
			states[THIRST_CASE] = THIRST3;
		} else if (ratioT <= 0.5 && ratioT > 0.25) {
			states[THIRST_CASE] = THIRST2;
		} else if (ratioT >= 0.5 && ratioT < 0.75) {
			states[THIRST_CASE] = THIRST1;
		}
	
		return states;
	}
}