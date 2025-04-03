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
    coreObscurityBroken, coreObscurity;

    public static void load(){
        coreObscurityBroken = new CoreBlock("core-obscurity-broken"){{
            requirements(Category.effect, /*BuildVisibility.editorOnly,*/ with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 828;
            //unitType = EpsUnitTypes
        }};
        coreObscurity = new EpsilonCoreBlock("core-obscurity"){{
            requirements(Category.effect, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 1450;
            isFirstTier = true;
            //unitType = EpsUnitTypes
        }};
    }
}
