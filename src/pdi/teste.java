package pdi;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class teste {

	public static void main(String[] args) throws IOException {
		
		BufferedImage imagem = ImageIO.read(new File("lena256color.jpg"));
		BufferedImage imagemnegativa = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		float y =0, i = 0, q =0;
		
		int width = imagem.getWidth();
        int height = imagem.getHeight();
        
        for (int c = 0; c < width; i++) {
            for (int j = 0; j < height; j++) {               
            		int rgb = imagem.getRGB(c, j);               //a cor inversa é dado por 255 menos o valor da cor                 
            		int r = (int)((rgb&0x00FF0000)>>>16);
            		int g = (int)((rgb&0x0000FF00)>>>8);
            		int b = (int) (rgb&0x000000FF);
            		 
            		y = (float) (0.299*r + 0.587*g + 0.114*b);
            		i = (float) (0.596*r - 0.275*g - 0.321*b);
            		q = (float) (0.212*r - 0.523*g + 0.311*b);
            		
            		y = 255 - y;
            		
            		r = (int) (y + 0.9563*i + 0.6210*q);
            		if (r > 255) {
            			r = 255;
            		} 
            		g = (int) (y - 0.2721*i - 0.6474*q);
            		if (g > 255) {
            			g = 255;
            		}
            		b = (int) (y - 1.1070*i + 1.7046*q);
            		if (b > 255) {
            			b = 255;
            		}
            		
            		
            		Color color = new Color(r, g, b);
            		imagem.setRGB(c, j, color.getRGB());
            		System.out.println("imagem salva caralhooo");
            		
            }
        }
		
		ImageIO.write(imagem, "JPG", new File("negativoYIQ.jpg"));
		System.out.println("imagem salva caralhooo");
	 

	}	
	
}

