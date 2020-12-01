package Business.DrawArea;

public class Area {
    private AreaUnit[][] area;
    public Area(){}

    public Area(int areaLength, int areaWidth){
        this.area = new AreaUnit[areaWidth/AreaUnit.BLOCK_WIDTH][areaLength/AreaUnit.BLOCK_LENGTH];
        for(int i=0;i<areaWidth/AreaUnit.BLOCK_WIDTH;i++){
            for(int j=0;j<areaLength/AreaUnit.BLOCK_LENGTH;j++){
                area[i][j] = new AreaUnit();
            }
        }
    }

    public AreaUnit[][] getArea() {
        return area;
    }

    public void setArea(AreaUnit[][] area) {
        this.area = area;
    }
}
