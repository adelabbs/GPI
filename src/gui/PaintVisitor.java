package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import data.Ant;
import data.Bee;
import data.Insect;
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
	public static int ICON_HUNGER = 5;
	public static int ICON_THIRST = 7;
	
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
			//Icon Hunger
			BufferedImage bufferIconHunger = ImageIO.read(new File(state[0]));
			g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + ICON_HUNGER,
					(int) insect.getCurrentPosition().getOrdinate() + ICON_HUNGER, null);

			//Icon Thirst
			BufferedImage bufferImageThirst = ImageIO.read(new File(state[1]));
			g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + ICON_THIRST,
					(int) insect.getCurrentPosition().getOrdinate() + ICON_THIRST, null);

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
			//Icon Hunger
			BufferedImage bufferIconHunger = ImageIO.read(new File(state[0]));
			g.drawImage(bufferIconHunger,(int) insect.getCurrentPosition().getAbscissa() + ICON_HUNGER,
					(int) insect.getCurrentPosition().getOrdinate() + ICON_HUNGER, null);

			//Icon Thirst
			BufferedImage bufferImageThirst = ImageIO.read(new File(state[1]));
			g.drawImage(bufferImageThirst,(int) insect.getCurrentPosition().getAbscissa() + ICON_THIRST,
					(int) insect.getCurrentPosition().getOrdinate() + ICON_THIRST, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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
	
	public static String[] getLifeState(Insect insect) {
		String[] states = new String[2];
		int maxh = insect.getMaxHunger();
		int curh = insect.getCurrentHunger();
		int maxt = insect.getMaxThirst();
		int curt = insect.getCurrentThirst();
		
		if(curh/maxh < maxh/4) {
			states[HUNGER_CASE] = HUNGER1;
		} else if(curh/maxh < maxh/2) {
			states[HUNGER_CASE] = HUNGER2;
		} else if (curh/maxh < 3*maxh/4) {
			states[HUNGER_CASE] = HUNGER3;
		}
		
		if(curh/maxt < maxt/4) {
			states[THIRST_CASE] = HUNGER1;
		} else if(curt/maxt < maxt/2) {
			states[THIRST_CASE] = HUNGER2;
		} else if (curt/maxt < 3*maxt/4) {
			states[THIRST_CASE] = HUNGER3;
		}

		return states;
	}
}