import javax.swing.*;
import java.awt.*;

public class Tarea1BresenhamMendozaReyesAngelEmanuel extends JPanel {

    private int x0, y0, x1, y1;

    public Tarea1BresenhamMendozaReyesAngelEmanuel() {
        x0 = solicitarValor("Ingrese x0:", 500);
        y0 = solicitarValor("Ingrese y0:", 500);
        x1 = solicitarValor("Ingrese x1:", 500);
        y1 = solicitarValor("Ingrese y1:", 500);
    }

    private int solicitarValor(String mensaje, int max) {
        int valor = -1;
        while (valor < 0 || valor > max) {
            try {
                valor = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
                if (valor < 0 || valor > max) {
                    JOptionPane.showMessageDialog(null, "Valor fuera de rango. Debe estar entre 0 y " + max, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valor;
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Tarea 1 Bresenham Mendoza Reyes Angel Emanuel");
        Tarea1BresenhamMendozaReyesAngelEmanuel panel = new Tarea1BresenhamMendozaReyesAngelEmanuel();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graficos) {
        super.paintComponent(graficos);
        dibujarCuadricula(graficos);
        dibujarLineaBresenham(graficos, x0, y0, x1, y1);
    }

    private void dibujarCuadricula(Graphics graficos) {
        graficos.setColor(Color.LIGHT_GRAY);
        int tamanoCuadricula = 10;
        for (int i = 0; i < getWidth(); i += tamanoCuadricula) {
            graficos.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i < getHeight(); i += tamanoCuadricula) {
            graficos.drawLine(0, i, getWidth(), i);
        }
    }

    public void dibujarLineaBresenham(Graphics graficos, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        graficos.setColor(Color.RED);
        while (true) {
            graficos.fillRect(x0, y0, 1, 1);
            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
}