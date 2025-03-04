package epsilon.world.blocks.production;

import mindustry.world.blocks.production.Drill;

public class ConfigurableDrill extends Drill{
    public Float efficiency1 = 0.5f;
    public Float efficiency2 = 1f;
    public float efficiency3 = 2f;

    public ConfigurableDrill(String name){
        super(name);
        configurable = true;
        saveConfig = true;
    }
    /*public class ConfigurableDrillBuild extends DrillBuild{
        @Override 
        public void updateTile(){
            
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(ToString(efficiency1) + "x", Styles.cleari, () -> {
                
            });
        }
    }*/
}
