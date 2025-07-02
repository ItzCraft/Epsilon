package epsilon.content.Kallistea.blocks;

import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.heat.VulcanHeatProducer;
import mindustry.type.Category;
import mindustry.world.Block;

import static mindustry.type.ItemStack.with;

public class KallisteaHeat {
    public static Block test;

    public static void load(){
        test = new VulcanHeatProducer("test"){{
            requirements(Category.production, with(KallisteaItems.anveiur, 1));
            health = 1;
            size = 3;
            squareSprite = false;
            heatOutput = 35f;
            craftTime = 45f;
        }};
    }
}
