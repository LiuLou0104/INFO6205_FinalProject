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
    private int lineSize = 20; // How big each cell should be
    private int halfLineSize = lineSize / 2;

    public SimuCanvasJPanel() {
    }

    // Swing calls when a redraw is needed
    @Override
    public void paint(Graphics g) {
        drawCanvas(g);
    }

    // Draw the contents of the panel
    private void drawCanvas(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Dimension size = getSize();

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, size.width, size.height);
//        System.out.println(size.width + " " + size.height);

        if (mySimulation != null) {
            drawOceanGrid(g2d);
        }
    }

    private void drawOceanGrid(Graphics2D g2d) {

        int maxRows = OnePathogenSimu.AREA_WIDTH;
        int maxCols = OnePathogenSimu.AREA_LENGTH;
//        int maxRows = getSize().width / lineSize;
//        int maxCols = getSize().height / lineSize;

        for (int j = 0; j < maxRows; j++) {
            for (int i = 0; i < maxCols; i++) {

                int startx = i * lineSize;
                AreaUnit[][] area = mySimulation.getAreaUnitArray();

//                System.out.println(area[j][i].getInfectNum() + "InfectNum");
//                System.out.println(i + "," + j + " " +area[j][i].getHeadcount() + "HeadCount");

                if (area[j][i].getInfectNum() > 0) {
                    int red = validColor(255 - (int) (255 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
                    int green = validColor(153 - (int) (153 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
                    int blue = validColor(51 - (int) (51 * area[j][i].getInfectNum() / area[j][i].getHeadcount()));
//                    System.out.println("i " + i +", j "+ j + " infectNum " + area[j][i].getInfectNum() + ", head count " + area[j][i].getHeadcount());
                    g2d.setColor(new Color(red, green, blue));
                } else {
                    g2d.setColor(new Color(255, 153, 51));
                }


                g2d.fillRect(startx, j * lineSize, lineSize - 1, lineSize - 1);

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
