package epsilon.planet;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;

public class KallisteaPlanetGenerator extends PlanetGenerator{
    public Seq<HeightCalc> heights = new Seq<>();
    public Seq<ColorCalc> colors = new Seq<>();
    public float baseHeight = 1;
    public Color baseColor = Color.white;

    public float rawHeight(Vec3 position){
        float height = baseHeight;
        for(HeightCalc h : heights){
            height = h.height(position, height);
        }
        return height;
    }
    @Override
    public void generateSector(Sector sector){

    }

    @Override
    public float getHeight(Vec3 position){
        return rawHeight(position);
    }
    @Override
    public Color getColor(Vec3 position){
        Color color = baseColor;
        for(ColorCalc c : colors){
            Color calculated = c.color(position, rawHeight(position));
            if(calculated != null) color = calculated;
        }
        return color;
    }
}
