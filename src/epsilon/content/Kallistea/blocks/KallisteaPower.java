package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import epsilon.content.Kallistea.KallisteaItems;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;

import static mindustry.type.ItemStack.with;

public class KallisteaPower{
    public static Block quartzGenerator, quartzNode;

    public static void load(){
        quartzGenerator = new ConsumeGenerator("quartz-generator"){{
            requirements(Category.power, with(KallisteaItems.gelionyte, 40, KallisteaItems.calcite, 35));
            health = 90;
            size = 2;
            powerProduction = 45f/60f;
            squareSprite = true;
            consumeItem(KallisteaItems.quartz, 1);
            itemDuration = 45f;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawDefault()
            );
        }};

        quartzNode = new PowerNode("quartz-node"){{
           requirements(Category.power, with(KallisteaItems.quartz, 3, KallisteaItems.calcite, 1));
           health = 30;
           size = 1;
           maxNodes = 5;
           maxRange = 10f;
           laserColor1 = Color.valueOf("83a5c9");
           laserColor2 = Color.valueOf("505e6e");
        }};
    }
}
