package Business.DrawArea;


import Business.Pathogen.Pathogen;

public class Area {
    private AreaUnit[][] area;
    public Area(int areaLength, int areaWidth) {
        this.area = new AreaUnit[areaWidth/ AreaUnit.BLOCK_WIDTH][areaLength/ AreaUnit.BLOCK_LENGTH];
        for(int i = 0; i<areaWidth/ AreaUnit.BLOCK_WIDTH; i++){
            for(int j = 0; j<areaLength/ AreaUnit.BLOCK_LENGTH; j++){
                area[i][j] = new AreaUnit();
            }
        }
    }

    public Area(int areaLength, int areaWidth, Pathogen pathogen, double popuDensity, boolean isWearingMask, boolean isQuarantine, boolean isTest){
        this.area = new AreaUnit[areaWidth/ AreaUnit.BLOCK_WIDTH][areaLength/ AreaUnit.BLOCK_LENGTH];
        for(int i = 0; i<areaWidth/ AreaUnit.BLOCK_WIDTH; i++){
            for(int j = 0; j<areaLength/ AreaUnit.BLOCK_LENGTH; j++){
                area[i][j] = new AreaUnit(pathogen);
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
