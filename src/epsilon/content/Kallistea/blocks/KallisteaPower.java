package epsilon.content.Kallistea.blocks;

import epsilon.content.Kallistea.KallisteaItems;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;

import static mindustry.type.ItemStack.with;

public class KallisteaPower{
    public static Block calciteBurner;

    public static void load(){
        calciteBurner = new ConsumeGenerator("calcite-burner"){{
            requirements(Category.power, with(KallisteaItems.gelionyte, 40, KallisteaItems.calcite, 35));
            health = 90;
            size = 2;
            powerProduction = 27f/60f;
            squareSprite = true;
            consumeItem(KallisteaItems.calcite, 1);
            itemDuration = 45f;
        }};
    }
}
