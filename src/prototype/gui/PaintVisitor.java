package prototype.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import prototype.data.Ant;
import prototype.data.Bee;
import prototype.process.InsectVisitor;

public class PaintVisitor implements InsectVisitor<Void> {

	private Graphics g;

	public PaintVisitor(Graphics graphics) {
		this.g = graphics;
	}

	@Override
	public Void visit(Bee insect) {
		String filename = "src/images/guepe_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate(), null);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Void visit(Ant insect) {
		String filename = "src/images/fourmi_test2.png";
		try {
			BufferedImage bufferImage = ImageIO.read(new File(filename));
			flip(bufferImage);
			g.drawImage(bufferImage, (int) insect.getCurrentPosition().getAbscissa(), (int) insect.getCurrentPosition().getOrdinate(), null);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void flip(BufferedImage image)	{
	for (int i=0;i<image.getWidth();i++)
		for (int j=0;j<image.getHeight()/2;j++){
			int tmp = image.getRGB(i, j);
			image.setRGB(i, j, image.getRGB(i, image.getHeight()-j-1));
			image.setRGB(i, image.getHeight()-j-1, tmp);
		}
	}
	
	
	
}