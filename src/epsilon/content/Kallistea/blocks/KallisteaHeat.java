package epsilon.content.Kallistea.blocks;

import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.heat.EnvironmentHeater;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

public class KallisteaHeat {
    public static Block test;

    public static void load(){
        test = new EnvironmentHeater("test"){{
            requirements(Category.production, ItemStack.with(KallisteaItems.anveiur, 1));
            health = 1;
            size = 3;
            squareSprite = false;
            heatOutput = 35f;
        }};
    }
}
