package hormiga_langton;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Simulacion {
    public static JLabel pasos = new JLabel("");
    public static void main(String[] args){
        int num_celdas_x = 80; 
        int num_celdas_y = 78; 
        int dim_celda = 8; 
        int borde = 20;
        int periodo = 1;
        Tablero panel = new Tablero(num_celdas_x, num_celdas_y, dim_celda, borde, periodo);
        JFrame frame = new JFrame("Hormiga de Langton");
        
        frame.add(panel);
        frame.add(pasos, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
