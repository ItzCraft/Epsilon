package epsilon.world.blocks.lore;

import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.world.Block;
import epsilon.ui.EpsUi;

// This block is a another method of showing Dialogs for that case, we won't make it in wprocs
public class DialogBlock extends Block{
    public int duration = 5;
    public String unitIconName = "epsilon-frog";
    public String text = "Template";

    public DialogBlock(String name){
        super(name);
        destructible = false;
    }

    
    @Override
    public void init(){
        super.init();
        EpsUi.showDialog(unitIconName, text, duration);
    }
}
