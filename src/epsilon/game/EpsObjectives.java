package epsilon.game;

import arc.Core;
import epsilon.type.EpsilonSectorPreset;
import mindustry.game.Objectives;

public class EpsObjectives {
    public static class OnVars implements Objectives.Objective {
        public boolean var;
        public EpsilonSectorPreset sector;

        public OnVars(boolean var, EpsilonSectorPreset sector){
            this.var = var;
            this.sector = sector;
        }

        protected OnVars(){}

        @Override
        public boolean complete(){
            return var = true ? true : false;
        }

        @Override
        public String display(){
            return Core.bundle.format("objective.var", sector.localizedName);
        }
    }
}
