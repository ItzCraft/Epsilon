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
        replaceable = false;
        targetable = false;
        drawTeamOverlay = false;
    }

    
    public class DialogBlockBuild extends Building{
        public boolean goida = true;
        @Override
        public void updateTile(){
            if(goida){
                EpsUi.showDialog(unitIconName, text, duration);
                goida = false;
            }
            super.updateTile();
        }
    }
}
