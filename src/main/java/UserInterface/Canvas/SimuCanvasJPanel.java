package UserInterface.Canvas;

import Business.DrawArea.AreaUnit;
import Business.Simulation.OnePathogenSimu;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @Author: LiuLou
 * @Description:
 * @Date: Created in 2:39 2020/11/30
 * @Modified By:
 */
public class SimuCanvasJPanel extends JPanel implements Observer{
    private int ctr = 0;
    private OnePathogenSimu mySimulation;
    private int canvasHeight;
    private int canvasWidth;
    private int maxRow;
    private int maxCol;
//    private int lineSize = 20; // How big each cell should be
    private int lineSize;
    private int ylineSize;
//    private int halfLineSize = lineSize / 2;

    public SimuCanvasJPanel() {
//        System.out.println("constructor getSize(): " + getSize());
//        System.out.println("constructor this.getSize(): " + this.getSize());
    }

    // Swing calls when a redraw is needed
    @Override
    public void paint(Graphics g) {
        drawCanvas(g);
    }

    // Draw the contents of the panel
    private void drawCanvas(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        Dimension size = getSize();
        System.out.println("drawCanvas getSize(): " + getSize());
        System.out.println("drawCanvas this.getSize(): " + this.getSize());
        this.canvasHeight = getSize().height;
        this.canvasWidth = getSize().width;
        this.lineSize = canvasWidth / OnePathogenSimu.AREA_LENGTH;
        this.ylineSize = canvasHeight / OnePathogenSimu.AREA_WIDTH;

//        g2d.setColor(new Color(240, 240, 240));
        g2d.setColor(new JButton().getBackground());
//        g2d.fillRect(0, 0, size.width, size.height);
        g2d.fillRect(0, 0, canvasWidth, canvasHeight);
//        System.out.println(size.width + " " + size.height);

        if (mySimulation != null) {
            drawOceanGrid(g2d);
        }
    }

    private void drawOceanGrid(Graphics2D g2d) {

        int maxRow = OnePathogenSimu.AREA_WIDTH;
        int maxCol = OnePathogenSimu.AREA_LENGTH;
//        int maxRow = getSize().width / lineSize;
//        int maxCol = getSize().height / lineSize;

        for (int j = 0; j < maxRow; j++) {
            for (int i = 0; i < maxCol; i++) {

                int startx = i * lineSize;
                AreaUnit[][] area = mySimulation.getAreaUnitArray();

//                System.out.println(area[j][i].getInfectNum() + "InfectNum");
//                System.out.println(i + "," + j + " " +area[j][i].getHeadcount() + "HeadCount");

                if (area[j][i].getInfectNum() > 0) {
                    int red = validColor(255 - (int) (255 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
                    int green = validColor(209 - (int) (209 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
                    int blue = validColor(111 - (int) (111 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
//                    System.out.println("i " + i +", j "+ j + " infectNum " + area[j][i].getInfectNum() + ", head count " + area[j][i].getHeadcount());
                    g2d.setColor(new Color(red, green, blue));
                } else {
//                    g2d.setColor(new Color(255, 153, 51));
                    g2d.setColor(new Color(255, 209, 111));
                }

                // draw areaUnit
                g2d.fillRect(startx, j * ylineSize, lineSize - 1, ylineSize - 1);

//                if(mySimulation.boat.getBoatPositionX()==j && mySimulation.boat.getBoatPositionY()==i) {
//                    Font font = new Font("Boat", Font.PLAIN, 30);
//                    g2d.setColor(new Color(153, 51, 250));
//                    g2d.setFont(font);
//                    g2d.drawString("\uD83D\uDEA2", startx + halfLineSize - halfLineSize * 2 / 3, j * lineSize + halfLineSize + halfLineSize / 4);
//                }
//                Font font = new Font("OilCoverage",Font.PLAIN,15);
//                g2d.setColor(new Color(94,38,18));
//                g2d.setFont(font);

//                String str = String.format("%1$.2f",mySimulation.ocean[j][i].getOilCoverage());
//                if(mySimulation.coverageShowFlag){
//                    g2d.drawString(str, startx + halfLineSize - halfLineSize * 2 / 3, j * lineSize + halfLineSize + halfLineSize / 4);
//                }
//            }
//        }
//
//        g2d.setColor(Color.green);
//        g2d.drawString("Hello World " + ctr++ ,10 ,20 );

            }
        }
    }

    private int validColor(int colVal) {
        if (colVal < 0) return 0;
        if (colVal > 255) return 255;
        return colVal;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof OnePathogenSimu) {
            mySimulation = (OnePathogenSimu) arg;
        }
        repaint(); // Tell the GUI thread that it should schedule a paint() call
    }

    public OnePathogenSimu getMySimulation() {
        return mySimulation;
    }

    public void setMySimulation(OnePathogenSimu mySimulation) {
        this.mySimulation = mySimulation;
    }
}
