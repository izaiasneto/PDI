
package app1;
 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class App1   extends JFrame {
private static final long serialVersionUID = 1L;
    private BufferedImage imgBuff, img2;
    private JFileChooser fc;
    private Image img;
    private String path;
    private static File f = new File(""), f2 = new File("");

    private JPanel pane;
    private JLabel dim;
    private VisualizaImg paneImg;
    private JButton media, btnAbrir, convolucao, btnOrigin, btnSalvar, btnR, btnG, btnB, btnN, btnC, convertRGBfYIQ, convertYIQfRGB,  bMult, bMultYIQ,   mediana, blend;
    private JTextField  brilhoM, brilhoM2, maskm, maskn, maskmedia;
     
    public App1(){
        
        pane = new JPanel();

        btnAbrir = new JButton("Abrir");
        btnOrigin = new JButton("Original");
        
         
        btnR = new JButton("Red");
        btnG = new JButton("Green");
        btnB = new JButton("Blue");
        btnN = new JButton("Negativo");
        btnC = new JButton("Cinza");
        //convolucao = new JButton("Convolução");
        
        convertRGBfYIQ = new JButton("RGB -> YIQ->RGB");
        
       
        bMult = new JButton("Brilho Multi Banda Y");
        bMultYIQ = new JButton("Brilho Multi Bandas RGB");
        
        mediana = new JButton("Mediana");
        media = new JButton("Media");
        maskm = new JTextField();
        maskn = new JTextField();
        maskmedia = new JTextField();
        blend = new JButton("Blend");
        
        brilhoM = new JTextField();
        brilhoM2 = new JTextField();
        
        dim = new JLabel("Dimensões da imagem: ");
        

        setBounds(400, 100, 800, 640);
        setLocationRelativeTo(null);
        setTitle("Trabalho 1 Intro. Processamento de Imagens");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pane.setSize(800, 540);
        pane.setLayout(null);

        dim.setBounds(50, 510, 400, 20);
        dim.setFont(new Font("Sans Serif", Font.ITALIC, 11));
        pane.add(dim);
        
      
        btnAbrir.setBounds(10, 10, 100, 25);
        pane.add(btnAbrir);

        btnOrigin.setBounds(685, 10, 100, 25);
        pane.add(btnOrigin);
        
        
         
        btnR.setBounds(685, 70, 100, 25);
        pane.add(btnR);
        btnG.setBounds(685, 100, 100, 25);
        pane.add(btnG);
        btnB.setBounds(685, 130, 100, 25);
        pane.add(btnB);
        btnN.setBounds(685, 230, 100, 25);
        pane.add(btnN);
        btnC.setBounds(685, 170, 100, 25);
        pane.add(btnC);
        
        maskm.setBounds(250, 580, 25, 26);
        pane.add(maskm);
        maskn.setBounds(270, 580, 25, 26);
        pane.add(maskn);
        mediana.setBounds(300, 580, 100, 25);
        pane.add(mediana);
        
        maskmedia.setBounds(270, 550, 25, 26);
        pane.add(maskmedia);
        media.setBounds(300, 550, 100, 25);
        pane.add(media);

        convertRGBfYIQ.setBounds(685, 290, 100, 25);
        pane.add( convertRGBfYIQ);
        
       
 
        bMult.setBounds(485, 550, 200, 25);
        pane.add(bMult);
        brilhoM.setBounds(460, 550, 25, 26);
        pane.add(brilhoM);
        
        bMultYIQ.setBounds(485, 580, 200, 25);
        pane.add(bMultYIQ);
        brilhoM2.setBounds(460, 580, 25, 26);
        pane.add(brilhoM2);
        

        //convolucao.setBounds(85, 580, 200, 25);
        //pane.add(convolucao);
        

        
        add(pane);

        paneImg = new VisualizaImg();
        paneImg.setBounds(50, 100, 256, 256);
        pane.add(paneImg);

        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f = abrirArquivo();
                setImagens(caminhoArquivo(f));
            }
        }
        );
        
       /*convolucao.addActionListener(new ActionListener() {
            private float[] valors;

			public void actionPerformed(ActionEvent e) {
            	List<String[]> lista = new ArrayList<>(); 
            	valors = null;
            	
            	FileReader fr;
				try {
					fr = abrirArquivoTXT();
					
					BufferedReader br = new BufferedReader(fr);

					String str;
					while((str = br.readLine()) != null){
						lista.add(str.split(" "));
					}
					
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
            		
				
                
                
                for(int i =0; i<lista.size(); i++) {
                	
                	String[] con = lista.get(i);
                	
                	
                	float val = Float.parseFloat(con[i]);
                	
                	
                	valors[i] = val; 	
                	
                }
            
            
               img2 = Filtro.Convolucao(bufferImagem(f), valors);
               paneImg.setImg(img2);
            }
        }
        );*/ 


        btnOrigin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toImage(bufferImagem(f));
            }
        }
        );

        
        convertRGBfYIQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	img2 = Filtro.rgbtoYIQ(bufferImagem(f));
                paneImg.setImg(img2);
            
        }
    }
    ); 
        
      //escala cinza
        btnC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	
                	img2 = Filtro.escalaCinza(bufferImagem(f));
                    paneImg.setImg(img2);
                
            }
        }
        ); 
        
        //exibir a banda R
        btnR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	
                	img2 = Filtro.BandaR(bufferImagem(f));
                    paneImg.setImg(img2);
                
            }
        }
        ); 

        //exibir a banda G
        btnG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	
                	img2 = Filtro.BandaG(bufferImagem(f));
                    paneImg.setImg(img2);
                
            }
        }
        ); 
        
        //exibir a banda B
        btnB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	
                	img2 = Filtro.BandaB(bufferImagem(f));
                    paneImg.setImg(img2);
                
            }
        }
        ); 
        
        mediana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                img2 = bufferImagem(f);
                Filtro.mediana(img2, Integer.parseInt(maskm.getText()), Integer.parseInt(maskn.getText()));
                paneImg.setImg(img2);

            }
        }
        );
        
        media.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                img2 = bufferImagem(f);
                Filtro.media(img2, Integer.parseInt(maskmedia.getText()));
                paneImg.setImg(img2);

            }
        }
        );


        

        btnN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] choices = { "RGB", "BandaY", "Quit"};
                //negativo
                int op = JOptionPane.showOptionDialog(App1.getFrames()[0],
                        "Opções Negativo:",
                        "Negativo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        choices,
                        "Quit");
                switch (op) {
                    case 0:
                        img2 = Filtro.negativoRGB(bufferImagem(f));
                        break;                
                    case 1:
                        img2 = Filtro.negativoYIQ(bufferImagem(f));
                        break;
                }
                paneImg.setImg(img2);
            }
        }
        );
        
    

         

        bMult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //multiplicativo brilho
                img2 = bufferImagem(f);
                if (!brilhoM.getText().equals("")) {
                    Filtro.brilhoM(img2, Integer.parseInt(brilhoM.getText()));
                    paneImg.setImg(img2);
                } else {
                    Filtro.brilhoM(img2, 2);
                    paneImg.setImg(img2);
                }
            }
        }
        );  
        
        bMultYIQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //multiplicativo brilho
                img2 = bufferImagem(f);
                if (!brilhoM.getText().equals("")) {
                    Filtro.brilhoMyiq(img2, Integer.parseInt(brilhoM.getText()));
                    paneImg.setImg(img2);
                } else {
                    Filtro.brilhoMyiq(img2, 2);
                    paneImg.setImg(img2);
                }
            }
        }
        );  

    }
    
    
    

    public File abrirArquivo() {
        File fLocal = new File("");
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = fc.showOpenDialog(pane);
        if (i == 0) {
            fLocal = fc.getSelectedFile();
            BufferedImage im = bufferImagem(fLocal);
            int w = im.getWidth();
            int h = im.getHeight();
            dim.setText(" Dimensões da imagem: " + w + " x " + h);
        } else {
            System.out.println("Operação cancelada");
        }
        return fLocal;
    }
    
    public FileReader abrirArquivoTXT() throws FileNotFoundException {
        File fLocal = new File("");
        FileReader fr; 
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = fc.showOpenDialog(pane);
        
        if (i == 0) {
            fLocal = fc.getSelectedFile();
            
        } else {
            System.out.println("Operação cancelada");
        }    
 
        fr = new FileReader(fLocal);
		return fr;
        
    }


    public String caminhoArquivo(File f) {
        return f.getAbsolutePath();
    }

    public BufferedImage bufferImagem(File f) {
        try {
            imgBuff = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(App1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgBuff;
    }

    public void toImage(BufferedImage imgBuff) {
        paneImg.setImg(Toolkit.getDefaultToolkit().createImage(imgBuff.getSource()));
    }

    public void setImagens(String path) {
        paneImg.setImg(path);
        this.path = path;
    }
    
    public class VisualizaImg extends JPanel {

        private static final long serialVersionUID = 1L;
        private Image img;

        public VisualizaImg() {
        }

        public VisualizaImg(Image img) {
            setImg(img);
        }

        public VisualizaImg(String url) {
            setImg(url);
        }

        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, paneImg.getSize().width, paneImg.getSize().height, this);
        }

        public void setImg(Image img) {
            this.img = img;
            this.repaint();
        }

        public void setImg(String url) {
            setImg(Toolkit.getDefaultToolkit().createImage(url));
        }
    }
    
    
    public static void main(String[] args) throws IOException {
  
    
     
  

      new App1();
   
}
}
     