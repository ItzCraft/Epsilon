package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.world.blocks.storage.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.storage.*;

import static mindustry.type.ItemStack.with;

public class KallisteaStorage{
    public static Block
    coreObscurity;

    public static void load(){
        coreObscurity = new EpsilonCoreBlock("core-obscurity"){{
            requirements(Category.storage, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40);
            health = 475;
            size = 4;
            itemCapacity = 450;
            
        }};
    }
}