package epsilon.world.blocks.production;

import arc.scene.ui.layout.Table;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.world.blocks.production.Drill;
import epsilon.world.meta.*;

public class ConfigurableDrill extends Drill{
    public float damageReload = 150;
    public float damageEff = 15;
    public float efficiency1 = 0.5f;
    public float efficiency2 = 1f;
    public float efficiency3 = 2f;
    public String button1Name = "0.5x";
    public String button2Name = "1x";
    public String button3Name = "2x";
    public float oldDrillTime, oldRotateSpeed;

    public ConfigurableDrill(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        canOverdrive = false;
    }

    @Override
    public void load(){
        super.load();
        oldDrillTime = drillTime;
        oldRotateSpeed = rotateSpeed;
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
                rotateSpeed = oldRotateSpeed * efficiency3;
                if(reloadTime <= 0 && health > 0 && efficiency > 0){
                    this.damage(damageEff*efficiency3);
                    reloadTime = damageReload;
                }
            } else if(eff2 && !eff3 && !eff1){
                drillTime = oldDrillTime * efficiency2;
                rotateSpeed = oldRotateSpeed * efficiency2;
                if(reloadTime <= 0 && health > 0 && efficiency > 0){
                    this.damage(damageEff*efficiency2);
                    reloadTime = damageReload;
               }
            } else{
                  drillTime = oldDrillTime * efficiency3;
                  rotateSpeed = oldRotateSpeed * efficiency1;
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
            }).pad(-65f).size(55f);
           table.button(button2Name, () -> {
                eff1 = false;
                eff2 = true;
                eff3 = false;
                deselect();
            }).pad(120f).size(55f);
            table.button(button1Name, () -> {
                eff1 = false;
                eff2 = false;
                eff3 = true;
                deselect();
            }).pad(35f).size(55f);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(eff1);
            write.bool(eff2);
            write.bool(eff3);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            this.eff1 = read.bool();
            this.eff2 = read.bool();
            this.eff3 = read.bool();
        }
    }
}
