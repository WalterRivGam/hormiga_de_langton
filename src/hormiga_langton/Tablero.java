package hormiga_langton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener {

    private int num_celdas_x;
    private int num_celdas_y;
    private int dim_celda;
    private int borde;
    private int x_ini;
    private int y_ini;
    private Direccion dir_ini;
    private Estado[][] celdas;
    private Hormiga hormiga;
    private Timer temporizador;
    private int periodo;

    public Tablero(int num_celdas_x, int num_celdas_y, int dim_celda, int borde, int periodo) {
        this.num_celdas_x = num_celdas_x;
        this.num_celdas_y = num_celdas_y;
        this.dim_celda = dim_celda;
        this.borde = borde;
        this.x_ini = num_celdas_x / 2;
        this.y_ini = num_celdas_y / 2;
        this.dir_ini = Direccion.ARRIBA;
        celdas = new Estado[num_celdas_x][num_celdas_y];
        for (int i = 0; i < num_celdas_x; ++i) {
            for (int j = 0; j < num_celdas_y; ++j) {
                celdas[i][j] = Estado.BLANCO;
            }
        }
        this.hormiga = new Hormiga(x_ini, y_ini, dir_ini);
        this.periodo = periodo;
        this.temporizador = new Timer(periodo, this);
        this.temporizador.start();
        this.setPreferredSize(new Dimension(borde*2 + dim_celda*num_celdas_x, borde*2 + dim_celda*num_celdas_y));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < num_celdas_x; ++i) {
            for (int j = 0; j < num_celdas_y; ++j) {
                g.setColor(Color.GRAY);
                g.drawRect(borde + i * dim_celda, borde + j * dim_celda, dim_celda, dim_celda);
                if (celdas[i][j] == Estado.NEGRO) {
                    g.setColor(Color.BLACK);
                    g.fillRect(borde + i * dim_celda + 1, borde + j * dim_celda + 1, dim_celda - 1, dim_celda - 1);
                }
                else {
                    g.setColor(Color.WHITE);
                    g.fillRect(borde + i * dim_celda + 1, borde + j * dim_celda + 1, dim_celda - 1, dim_celda - 1);
                }
            }
        }
    }

    public boolean dentro(Hormiga h) {
        if (h.getX() >= 0 && h.getX() < num_celdas_y && h.getY() >= 0 && h.getY() < num_celdas_x) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dentro(hormiga)) {
            temporizador.stop();
        } else {
            int x = hormiga.getX();
            int y = hormiga.getY();
            if (celdas[x][y] == Estado.BLANCO) {
                hormiga.girarDerecha();
                celdas[x][y] = Estado.NEGRO;
                hormiga.avanzar();
                Simulacion.pasos.setText(String.format("Número de pasos: %d", hormiga.getPasos()));
                repaint();
            } else if (celdas[x][y] == Estado.NEGRO) {
                hormiga.girarIzquierda();
                celdas[x][y] = Estado.BLANCO;
                hormiga.avanzar();
                Simulacion.pasos.setText(String.format("Número de pasos: %d", hormiga.getPasos()));
                repaint();
            }
        }

    }
}
