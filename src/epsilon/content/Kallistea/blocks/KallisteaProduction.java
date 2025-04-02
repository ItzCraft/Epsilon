package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.production.*;

import static mindustry.type.ItemStack.with;

public class KallisteaProduction{
    public static Block
    //drills
    pitMiningRig, breakerDrill;

    public static void load(){
        //drills
        pitMiningRig = new Drill("pit-mining-rig"){{
            requirements(Category.production, with(KallisteaItems.gelionyte, 25, KallisteaItems.calcite, 20));
            health = 120;
            size = 3;
            drillTime = 1280;
            rotateSpeed = 1.25;
            tier = 3;
            squareSprite = false;
        }};
        breakerDrill = new ConfigurableDrill("breaker-drill"){{
            requirements(Category.production, with(KallisteaItems.calcite, 145, KallisteaItems.quartz, 90, KallisteaItems.fylion, 70));
            health = 300;
            size = 4;
            drillTime = 1075;
            rotateSpeed = 2;
            tier = 4;
            damageEff = 7;
            consumePower(1.33f);
        }};
    } 
}
