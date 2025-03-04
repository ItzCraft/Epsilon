package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.lore.*;

import static mindustry.type.ItemStack.with;

public class LoreBlocks{
    public static Block
    testBlock1;

    public static void load(){
        testBlock1 = new ImageBlock("testBlock1"){{
            requirements(Category.distribution, with(KallisteaItems.calcite, 14884252));
            health = 1;
            size = 1;
            imagePath = "assets/frog.png";
            dialogName = "Router of god";
        }};
    }
}