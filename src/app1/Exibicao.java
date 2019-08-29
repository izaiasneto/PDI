/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Larissa
 */
public class Exibicao {
    
    public static void exibirImagem(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout());
        contentPane.add(new JScrollPane(imageLabel));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void exibirImagem(BufferedImage image, BufferedImage image2) {
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        ImageIcon icon2 = new ImageIcon(image2);
        JLabel imageLabel2 = new JLabel(icon2);
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout());
        contentPane.add(new JScrollPane(imageLabel));
        contentPane.add(new JScrollPane(imageLabel2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 680);
        frame.setVisible(true);
    }
}
 
