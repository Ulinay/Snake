package sample;

import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.TableHeaderUI;

public class Apple implements Runnable {
    int x;
    int y;
    int xPoison;
    int yPoison;
    int widthWindow;
    int heightWindow;
    boolean eaten = false;
    boolean eatenPoison = false;
    public Apple(int widthWindow,int heightWindow){
this.widthWindow = widthWindow;
this.heightWindow = heightWindow;
        this.x = (int) (Math.random() * (widthWindow-50));
        this.y = (int) (Math.random() * (heightWindow-50));
        this.xPoison = (int) (Math.random() * (widthWindow-50));
        this.yPoison = (int) (Math.random() * (heightWindow-50));
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("");
            if (eaten) {
                this.x = (int) (Math.random() * (widthWindow-50));
                this.y = (int) (Math.random() * (heightWindow-50));
                this.eaten = false;
            }else if(eatenPoison){
                this.xPoison = (int) (Math.random() * (widthWindow-50));
                this.yPoison = (int) (Math.random() * (heightWindow-50));
                this.eatenPoison = false;
            }
        }
    }
}
