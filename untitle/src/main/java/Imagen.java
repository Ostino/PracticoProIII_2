import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

    public class Imagen implements IDibujador {
        private int alto;
        private int ancho;
        private int[][] pixeles;
        private PropertyChangeSupport observado;

        public Imagen(int w, int h) {
            ancho = w;
            alto = h;
            pixeles = new int[ancho][alto];
            observado = new PropertyChangeSupport(this);
        }

        @Override
        public void dibujar(Graphics g) {
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    g.setColor(new Color(pixeles[i][j]));
                    g.drawLine(i,j,i,j);
                }
            }
        }
        public void imagenBlanca(){
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    pixeles[i][j] = 0x00FFFFFF;
                }
            }
        }

        public void imagen4x4() {
            // Primer cuadrado, parte superior izquierda BLACNO
            for (int i = 0; i < ancho/2; i++) {
                for (int j = 0; j < alto/2; j++) {
                    pixeles[i][j] = 0x00FFFFFF;
                }
            }
            // Segundo cuadrado, parte superior derecha ROJO
            for (int i = ancho/2; i < ancho; i++) {
                for (int j = 0; j < alto/2; j++) {
                    pixeles[i][j] = 0x00FF0000;
                }
            }

            // 3er cuadrado, parte inferior izquierda VERDE
            for (int i = 0; i < ancho/2; i++) {
                for (int j = alto/2; j < alto; j++) {
                    pixeles[i][j] = 0x0000FF00;
                }
            }
            // 4to cuadrado, parte inferior derecha AZUL
            for (int i = ancho/2; i < ancho; i++) {
                for (int j = alto/2; j < alto; j++) {
                    pixeles[i][j] = 0x000000FF;
                }
            }
        }

        public void addObserver(PropertyChangeListener listener) {
            observado.addPropertyChangeListener(listener);
        }

        public void achicar(int t) {
            int[][] nuevosPixeles = new int[ancho/t][alto/t];

            for (int i = 0; i < ancho; i+=t) {
                for (int j = 0; j < alto; j+=t) {
                    nuevosPixeles[i/2][j/2] = pixeles[i][j];
                }
            }

            pixeles = nuevosPixeles;
            ancho = ancho/t;
            alto = alto /t;

            observado.firePropertyChange("IMAGEN",true, false);
        }

        public void aplicarMatriz(MatrizDeTransformacion m) {
            int[][] nuevosPixeles = new int[ancho][alto];

            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    Vectores entrada = new Vectores(i,j);
                    Vectores salida = m.aplicarMatriz(entrada);
                    if ((int)salida.getX() >= 0 &&
                            (int)salida.getX() < ancho &&
                            (int)salida.getY() >= 0 &&
                            (int)salida.getY() < alto) {
                        nuevosPixeles[(int)salida.getX()][(int)salida.getY()] =
                                pixeles[i][j];
                    }
                }
            }
            pixeles = nuevosPixeles;
            observado.firePropertyChange("IMAGEN",true, false);
        }

        public int getAlto() {
            return alto;
        }

        public int getAncho() {
            return ancho;
        }

        public int[][] getPixeles() {
            return pixeles;
        }

        public void punto(int x, int y, int tamano) {

            for (int i = x - tamano / 2; i < x + tamano / 2; i++) {
                for (int j = y - tamano / 2; j < y + tamano / 2; j++) {
                    if (i >= 0 && i < ancho &&
                            j >= 0 && j < alto) {
                        pixeles[i][j] = 0;
                    }
                }
            }
            observado.firePropertyChange("IMAGEN", true, false);
        }
}
