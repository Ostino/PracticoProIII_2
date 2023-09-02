import javax.swing.*;
import java.awt.*;

    public class Ventana extends JFrame {
        private Imagen modelo;
        public Ventana() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            this.getContentPane().setLayout(new BorderLayout());

            modelo = new Imagen(400,400);
            modelo.imagen4x4();

            Panel panel = new Panel(modelo);
            modelo.addObserver(panel);
            this.getContentPane().add(panel, BorderLayout.CENTER);


            JButton btn = new JButton("Achicar");
            btn.addActionListener(e -> {
                btnAchicar_clicked();
            });

            this.getContentPane().add(btn, BorderLayout.SOUTH);

            btn = new JButton("Matriz de Transformacion");
            btn.addActionListener(e -> {
                btnTransformacion_clicked();
            });

            this.getContentPane().add(btn, BorderLayout.NORTH);

            this.setVisible(true);
            this.pack();
        }

        private void btnTransformacion_clicked() {

            MatrizDeTransformacion m = new MatrizDeTransformacion();
            m.traslacion(modelo.getAncho(),0);
            m.rotacion(-90);
            modelo.aplicarMatriz(m);
        }

        private void btnAchicar_clicked() {
            modelo.achicar(2);
        }


        public static void main(String[] args) {
            new Ventana();
        }
    }

