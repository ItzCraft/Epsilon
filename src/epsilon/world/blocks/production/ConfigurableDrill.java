package epsilon.world.blocks.production;

import arc.scene.ui.layout.Table;
import arc.math.*;
import arc.util.*;
import mindustry.world.blocks.production.Drill;
import mindustry.world.*;
import mindustry.entities.*;
import mindustry.game.Team;
import epsilon.world.meta.*;

public class ConfigurableDrill extends Drill{
    public float damageReload = 150;
    public float damage = 15;
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

    @Override
    public void setStats(){
        super.setStats();
        stats.add(EpsStats.efficiency1, efficiency1);
        stats.add(EpsStats.efficiency2, efficiency2);
        stats.add(EpsStats.efficiency3, efficiency3); 
    }

    public class ConfigurableDrillBuild extends DrillBuild{
        public boolean eff1 = false;
        public boolean eff2 = true;
        public boolean eff3 = false;
        public float reloadTime = damageReload;

        @Override 
        public void updateTile(){
            if(eff1 && !eff2 && !eff3){
                drillTime = oldDrillTime * efficiency1;
            } else if(eff2 && !eff3 && !eff1){
                drillTime = oldDrillTime * efficiency2;
                if(reloadTime <= 0 && health > 0){
                    Lightning.create(Team.derelict, team.color, damage*efficiency2, this.x, this.y, Mathf.random(0, 360), 1);
                    reloadTime = damageReload;
               }
            } else{
                  drillTime = oldDrillTime * efficiency3;
                  if(reloadTime <= 0 && health > 0){
                      Lightning.create(Team.derelict, team.color, damage*efficiency3, this.x, this.y, Mathf.random(0, 360), 1);
                      reloadTime = damageReload;
                  }
            }
            reloadTime -= Time.delta;
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
            }).pad(20f).size(60f);
            table.button(button1Name, () -> {
                eff1 = false;
                eff2 = false;
                eff3 = true;
                deselect();
            }).pad(65f).size(60f);
        }
    }
}
