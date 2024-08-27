import javax.swing.*;
import java.awt.*;

public class Tarea1DDAMendozaReyesAngelEmanuel extends JPanel {

    private int x0, y0, x1, y1;

    public Tarea1DDAMendozaReyesAngelEmanuel() {
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
        JFrame ventana = new JFrame("Tarea1 DDA Mendoza Reyes Angel Emanuel");
        Tarea1DDAMendozaReyesAngelEmanuel panel = new Tarea1DDAMendozaReyesAngelEmanuel();
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
        dibujarLineaDDA(graficos, x0, y0, x1, y1);
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

    public void dibujarLineaDDA(Graphics graficos, int x0, int y0, int x1, int y1) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int pasos = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        float incrementoX = (float) deltaX / pasos;
        float incrementoY = (float) deltaY / pasos;
        float x = x0;
        float y = y0;
        graficos.setColor(Color.RED);
        for (int i = 0; i <= pasos; i++) {
            graficos.fillRect(Math.round(x), Math.round(y), 1, 1);
            x += incrementoX;
            y += incrementoY;
        }
    }
}