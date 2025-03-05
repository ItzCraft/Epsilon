package epsilon.world.blocks.production;

import arc.scene.ui.layout.Table;
import mindustry.world.blocks.production.Drill;
import mindustry.world.*;

public class ConfigurableDrill extends Drill{
    public float efficiency1 = 0.5f;
    public float efficiency2 = 1f;
    public float efficiency3 = 2f;
    public String button1Name = "0.5x";
    public String button2Name = "1x";
    public String button3Name = "2x";

    public final float oldDrillTime = drillTime; 


    public ConfigurableDrill(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        canOverdrive = false;
    }
    public class ConfigurableDrillBuild extends DrillBuild{
        public boolean eff1 = false;
        public boolean eff2 = true;
        public boolean eff3 = false;

        @Override 
        public void updateTile(){
            if(eff1 && !eff2 && !eff3){
                drillTime = oldDrillTime * efficiency1;
            } else if(eff2 && !eff3 && !eff1){
                  drillTime = oldDrillTime * efficiency2;
            } else{
                  drillTime = oldDrillTime * efficiency3;
            }
            super.updateTile(); 
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(button3Name, () -> {
                eff1 = true;
                eff2 = false;
                eff3 = false;
                deselect();
            }).pad(-65f).size(60f);
           table.button(button2Name, () -> {
                eff1 = false;
                eff2 = true;
                eff3 = false;
                deselect();
            }).pad(-5.5f).size(60f);
            table.button(button1Name, () -> {
                eff1 = false;
                eff2 = false;
                eff3 = true;
                deselect();
            }).pad(65f).size(60f);
        }
    }
}
