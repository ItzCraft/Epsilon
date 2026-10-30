package epsilon.game;

import arc.Core;
import epsilon.EpsilonVars;
import epsilon.type.sector.EpsilonSectorPreset;
import mindustry.game.Objectives;

public class EpsObjectives {
    public static class OnVars implements Objectives.Objective {
        public String var;
        public EpsilonSectorPreset preset;

        public OnVars(String var, EpsilonSectorPreset preset){
            this.var = var;
            this.preset = preset;
        }

        protected OnVars(){}

        @Override
        public boolean complete(){
            return EpsilonVars.getVarForSector(var);
        }

        @Override
        public String display(){
            return Core.bundle.format("objective.var", preset.localizedName);
        }
    }
}
