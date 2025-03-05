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
    exponentialDrill;

    public static void load(){
        exponentialDrill = new ConfigurableDrill("exponential-drill"){{
            requirements(Category.production, with(KallisteaItems.gelionyte, 25, KallisteaItems.calcite, 20));
            health = 120;
            size = 3;
            drillTime = 480;
            tier = 3;
        }};
    } 
}
