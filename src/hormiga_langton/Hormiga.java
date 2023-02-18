package hormiga_langton;

public class Hormiga {

    private int x;
    private int y;
    private Direccion dir;
    private int pasos = 0;

    public Hormiga(int celda_x, int celda_y, Direccion dir) {
        this.x = celda_x;
        this.y = celda_y;
        this.dir = dir;
    }

    public void girarDerecha() {
        dir = Direccion.values()[(dir.ordinal() + 1) % 4];
    }

    public void girarIzquierda() {
        dir = Direccion.values()[(dir.ordinal() + 3) % 4];
    }

    public void avanzar() {
        switch (dir) {
            case ARRIBA:
                y--;
                break;
            case DERECHA:
                x++;
                break;
            case ABAJO:
                y++;
                break;
            case IZQUIERDA:
                x--;
                break;
        }
        pasos++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getPasos(){
        return pasos;
    }
    
    public void setPasos(int pasos){
        this.pasos = pasos;
    }
}
