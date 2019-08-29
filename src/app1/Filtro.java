package app1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Filtro {
	

	public static BufferedImage escalaCinza(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color c = new Color(image.getRGB(i, j));
				int tomCinza = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				c = new Color(tomCinza, tomCinza, tomCinza);
				image.setRGB(i, j, c.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage negativoRGB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color c = new Color(image.getRGB(i, j));
				int r = 255 - c.getRed();
				int g = 255 - c.getGreen();
				int b = 255 - c.getBlue();
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage negativoYIQ(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		float yiq[] = new float[3];
		int rgb[] = new int[3];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color c = new Color(image.getRGB(i, j));

				rgb[0] = c.getRed();
				rgb[1] = c.getGreen();
				rgb[2] = c.getBlue();

				// RGB para YIQ
				yiq = rgbTOyiq(rgb);

				// aplicando negativo na banda Y
				yiq[0] = 255 - yiq[0];

				// YIQ para RGB
				rgb = yiqTOrgb(yiq);

				// avaliar os limites RGB para R
				rgb = avaliarlimites(rgb);

				Color color = new Color(rgb[0], rgb[1], rgb[2]);
				image.setRGB(i, j, color.getRGB());
			}
		}

		return image;

	}

	public static BufferedImage BandaR(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		// float yiq[] = new float[3];
		int rgb[] = new int[3];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				Color cor = new Color(image.getRGB(i, j));
				cor = new Color(cor.getRed(), 0, 0); // pega so a componente R
				image.setRGB(i, j, cor.getRGB());
			}
		}
		return image;
	}

	


	public static BufferedImage BandaG(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				Color cor = new Color(image.getRGB(i, j));

				// pega o valor da cor verde do pixel e aplica a nova cor
				cor = new Color(0, cor.getGreen(), 0);

				image.setRGB(i, j, cor.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage BandaB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				Color cor = new Color(image.getRGB(i, j));

				// pega valor da cor azul do pixel e aplica a nova cor
				cor = new Color(0, 0, cor.getBlue());

				image.setRGB(i, j, cor.getRGB());
			}
		}
		return image;
	}

	static BufferedImage rgbtoYIQ(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		float yiq[] = new float[3];
		int rgb[] = new int[3];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color c = new Color(image.getRGB(i, j));
				rgb[0] = c.getRed();
				rgb[1] = c.getGreen();
				rgb[2] = c.getBlue();

				// RGB para YIQ
				yiq = rgbTOyiq(rgb);

				// yiq para RGB

				rgb = yiqTOrgb(yiq);

				Color color = new Color(rgb[0], rgb[1], rgb[2]);
				image.setRGB(i, j, color.getRGB());

			}
		}

		return image;

	}

	public static float[] rgbTOyiq(int rgb[]) {
		float yiq[] = new float[3];
		yiq[0] = 0.299f * rgb[0] + 0.587f * rgb[1] + 0.114f * rgb[2];
		yiq[1] = 0.596f * rgb[0] - 0.274f * rgb[1] - 0.322f * rgb[2];
		yiq[2] = 0.211f * rgb[0] - 0.523f * rgb[1] + 0.312f * rgb[2];
		return yiq;
	}

	public static int[] avaliarlimites(int rgb[]) {

		int i;

		for (i = 0; i < 3; i++) {

			if (rgb[i] > 255) {

				rgb[i] = 255;
			} else if (rgb[i] < 0) {

				rgb[i] = 0;
			}

		}

		return rgb;
	}

	public static int[] yiqTOrgb(float[] yiq) {
		int rgb[] = new int[3];
		rgb[0] = Math.round(yiq[0] + 0.956f * yiq[1] + 0.621f * yiq[2]) > 255 ? 255
				: Math.round(yiq[0] + 0.956f * yiq[1] + 0.621f * yiq[2]) < 0 ? 0
						: Math.round(yiq[0] + 0.956f * yiq[1] + 0.621f * yiq[2]);
		rgb[1] = Math.round(yiq[0] - 0.272f * yiq[1] - 0.647f * yiq[2]) > 255 ? 255
				: Math.round(yiq[0] - 0.272f * yiq[1] - 0.647f * yiq[2]) < 0 ? 0
						: Math.round(yiq[0] - 0.272f * yiq[1] - 0.647f * yiq[2]);
		rgb[2] = Math.round(yiq[0] - 1.106f * yiq[1] + 1.703f * yiq[2]) > 255 ? 255
				: Math.round(yiq[0] - 1.106f * yiq[1] + 1.703f * yiq[2]) < 0 ? 0
						: Math.round(yiq[0] - 1.106f * yiq[1] + 1.703f * yiq[2]);
		return rgb;
	}

	// Aumentar o brilho de uma imagem consiste em incrementar a intensidade
	// luminosa de cada pixel.
	// Para uma imagem no sistema RGB, aumenta-se um valor constante nas banda R G e
	// B a uma constante c
	public static void brilhoM(BufferedImage image, int c) {
		int width = image.getWidth();
		int height = image.getHeight();
		float yiq[] = new float[3];
		int rgb[] = new int[3];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color cor = new Color(image.getRGB(i, j));
				rgb[0] = cor.getRed() * c;
				rgb[1] = cor.getGreen() * c;
				rgb[2] = cor.getBlue() * c;

				// avaliar os limites RGB para R
				rgb = avaliarlimites(rgb);

				cor = new Color(rgb[0], rgb[1], rgb[2]);
				image.setRGB(i, j, cor.getRGB());
			}
		}
	}

	public static void brilhoMyiq(BufferedImage image, int c) {
		// Para uma imagem no sistema YIQ, aumenta-se um valor constante na banda Y a
		// uma constante c
		int width = image.getWidth();
		int height = image.getHeight();
		float yiq[] = new float[3];
		int rgb[] = new int[3];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color cor = new Color(image.getRGB(i, j));
				rgb[0] = cor.getRed();
				rgb[1] = cor.getGreen();
				rgb[2] = cor.getBlue();
				yiq = rgbTOyiq(rgb); // converte para o sistema yiq, para manipular apenas o brilho
				yiq[0] *= c; // Altera o brilho no sistema yiq, multiplicando
				rgb = yiqTOrgb(yiq); // converte para o sistema rgb
				cor = new Color(rgb[0], rgb[1], rgb[2]);
				image.setRGB(i, j, cor.getRGB());
			}
		}
	}

	public static void mediana(BufferedImage image, int m, int n) {
		int width = image.getWidth();
		int height = image.getHeight();
		int r[] = new int[m * n], g[] = new int[m * n], b[] = new int[m * n], i2 = 0, j2 = 0, cont;
		Color rgb;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				// A cada pixel zera o contador para visitar o pixel central e seus vizinhos
				cont = 0;

				// Varre o elemento do meio e seus vizinhos
				for (i2 = i - (m / 2); i2 <= i + (m / 2); i2++) {
					for (j2 = j - (n / 2); j2 <= j + (n / 2); j2++) {
						// Se algum dos vizinhos estiver fora dos limites da imagem, seu pixel receberá
						// 0 para r, g e b
						try {
							r[cont] = new Color(image.getRGB(i2, j2)).getRed();
							g[cont] = new Color(image.getRGB(i2, j2)).getGreen();
							b[cont] = new Color(image.getRGB(i2, j2)).getBlue();
							cont++;
						} catch (ArrayIndexOutOfBoundsException e) {
							r[cont] = g[cont] = b[cont] = 0;
						}
					}
				}

				// ordena o array

				Arrays.sort(r);
				Arrays.sort(g);
				Arrays.sort(b);

				// calcula a mediana e atribui a cor
				rgb = new Color(r[r.length / 2], g[g.length / 2], b[b.length / 2]);

				// insere a cor no pixel do meio
				image.setRGB(i, j, rgb.getRGB());
			}
		}
	}
	
	public static void media(BufferedImage image, int n) {
		int width = image.getWidth();
		int height = image.getHeight();
		int r, g, b, //Componentes de cor
		 i2 = 0, j2 = 0; //Variáveis auxiliares
		Color rgb;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				//A cada pixel visitado, zera os elementos de cor 
                r = g = b = 0;

				// Varre o elemento do meio e seus vizinhos
                for (i2 = i - (n / 2); i2 <= i + (n / 2); i2++) {
                    for (j2 = j - (n / 2); j2 <= j + (n / 2); j2++) {
						// Se algum dos vizinhos estiver fora dos limites da imagem, seu pixel receberá
						// 0 para r, g e b
                    	 try {
                             r += new Color(image.getRGB(i2, j2)).getRed();
                             g += new Color(image.getRGB(i2, j2)).getGreen();
                             b += new Color(image.getRGB(i2, j2)).getBlue();
                         } catch (ArrayIndexOutOfBoundsException e) {
                             r += 0; g += 0; b += 0; // Processamento desnecessário
                         }
					
					}
				}

				r /= (n * n);
                g /= (n * n);
                b /= (n * n); //Após a soma, calcula-se a média

                rgb = new Color(r, g, b);
                
              //Insere a cor no pixel do meio
                image.setRGB(i, j, rgb.getRGB()); 

				
			}
		}
	}

}