package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import data.Ant;
import data.Bee;
import process.InsectVisitor;

public class PaintVisitor implements InsectVisitor<Void> {

	private Graphics g;

	public PaintVisitor(Graphics graphics) {
		this.g = graphics;
	}

	@Override
	public Void visit(Bee insect) {
		String filename = "resources/images/guepe_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);
			

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
			//flip(bufferImage);
			bufferImage = rotate(bufferImage, 50);
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(),
					(int) insect.getCurrentPosition().getOrdinate(), null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void flip(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++)
			for (int j = 0; j < image.getHeight() / 2; j++) {
				int tmp = image.getRGB(i, j);
				image.setRGB(i, j, image.getRGB(i, image.getHeight() - j - 1));
				image.setRGB(i, image.getHeight() - j - 1, tmp);
			}
	}
	
	public static BufferedImage rotate(BufferedImage bimg, double angle) {
		// width and height equals to height to avoid image crop
	    int w = bimg.getHeight();    
	    int h = bimg.getHeight();

	    BufferedImage rotated = new BufferedImage(w, h, bimg.getType());  
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.rotate(Math.toRadians(angle), w/2, h/2);
	    graphic.drawImage(bimg, null, 0, 0);
	    graphic.dispose();
	    return rotated;
	}
}