package epsilon.world.blocks.production;

import mindustry.world.blocks.production.Drill;

public class ConfigurableDrill extends Drill{
    public Float efficiency1 = 0.5f;
    public final Float efficiency2 = 1f;
    public float efficiency3 = 2f;

    public ConfigurableDrill(String name){
        super(name);
        configurable = true;
        saveConfig = true;
    }
    /*public class ConfigurableDrillBuild extends DrillBuild{
        public boolean eff1 = false;
        public boolean eff2 = true;
        public boolean eff3 = false;
        public final int oldDrillTime = drillTime;

        @Override 
        public void updateTile(){
            if(eff1){
                drillTime = oldDrillTime * efficiency1;
            } else if(eff2){
                drillTime = oldDrillTime * efficiency2;
            } else{
                drillTime = oldDrillTime * efficiency3;
            }
            super.updateTile();
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(efficiency1.toString() + "x", Styles.cleari, () -> {
                eff1 = true;
                eff2 = false;
                eff3 = false;
                deselect();
            });
           table.button(efficiency2.toString() + "x", Styles.cleari, () -> {
                eff1 = false;
                eff2 = true;
                eff3 = false;
                deselect();
            });
            table.button(efficiency3.toString() + "x", Styles.cleari, () -> {
                eff1 = false;
                eff2 = false;
                eff3 = true;
                deselect();
            });
        }
    }*/
}
