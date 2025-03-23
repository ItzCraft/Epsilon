package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.lore.*;

import static mindustry.type.ItemStack.with;

public class LoreBlocks{
    public static Block
    testBlock1, cutsceneBlock;

    public static void load(){
        testBlock1 = new ImageBlock("test-block1"){{
            requirements(Category.distribution, with(KallisteaItems.calcite, 14884252));
            health = 1;
            size = 1;
            imagePath = "epsilon-frog";
            dialogName = "Router of god";
        }};


        cutsceneBlock = new CutsceneBlock("cutscene-block"){{
            requirements(Category.logic, with(KallisteaItems.gelionyte, 1448425269));
            size = 1;
            health = 99999;
        }};
    }
}
