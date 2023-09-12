import javax.swing.*;
import java.awt.*;

    public class Ventana extends JFrame {

        private Imagen modelo;

        Panel panel = new Panel(modelo);
        public Ventana() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            this.getContentPane().setLayout(new BorderLayout());

            modelo = new Imagen(600,600);
            //modelo.imagen4x4();
            modelo.imagenBlanca();

            Panel panel = new Panel(modelo);
            modelo.addObserver(panel);
            this.getContentPane().add(panel, BorderLayout.CENTER);


            JButton btn = new JButton("Achicar");
            btn.addActionListener(e -> {
                btnAchicar_clicked();
            });


            this.getContentPane().add(btn,BorderLayout.NORTH);

            btn = new JButton("Matriz de Transformacion");
            btn.addActionListener(e -> {
                btnTransformacion_clicked();

            });
            this.getContentPane().add(btn, BorderLayout.SOUTH);
             btn = new JButton("Agrandar");
            btn.addActionListener(e -> {
                btnAgrandar_clicked();
            });
            this.getContentPane().add(btn,BorderLayout.EAST);


            this.setVisible(true);
            this.pack();  

        }


        private void btnTransformacion_clicked() {
            modelo.setPintable(false);
            MatrizDeTransformacion m = new MatrizDeTransformacion();
            m.traslacion(modelo.getAncho(),0);
            m.rotacion(-90);
            modelo.aplicarMatriz(m);
        }

        private void btnAchicar_clicked() {
            modelo.achicar(2);
        }
        private void btnAgrandar_clicked() {
            modelo.Agrandar(2);
        }


        public static void main(String[] args) {
            new Ventana();
        }
    }

