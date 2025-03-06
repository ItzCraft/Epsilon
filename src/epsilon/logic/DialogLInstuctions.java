package epsilon.logic;

import arc.Core;

import mindustry.logic.*;
import mindustry.logic.LExecutor.*;
import epsilon.ui.EpsUi;

import static mindustry.Vars.*;

public class DialogLInstuctions{
    public static class DialogControlInstruction implements LInstruction{
        public float duration;
        public String unitIconName;

        public DialogControlInstruction(float duration, String unitIconName){
            this.unitIconName = unitIconName;
            this.duration = duration;
            return this;
        }
        
        @Override
        public void run(LExecutor exec){
            String text = exec.textBuffer.toString();
            if(text.startsWith("@")){
                String substr = text.substring(1);
                if(Core.bundle.has(substr)){
                    text = Core.bundle.get(substr);
                }
            }

            EpsUi.showDialog(unitIconName, text, duration);
        } 
    }

    /*public static abstract class BaseDialogTextInstruction implements LInstruction{
        public String name = template;
        public float duration;

        public BaseDialogTextInstruction(String name, float duration){
            this.name = name;
            this.duration = duration
            return this;
        }

        @Override 
        public void run(LExecutor exec){
            String text = exec.textBuffer.toString();
            if(text.startsWith("@")){
                String substr = text.substring(1);
                if(Core.bundle.has(substr)){
                    text = Core.bundle.get(substr);
                }
            }

            if(name.startsWith("@")){
                String subname = name.substring(1)
                if(Core.bundle.has(subname)){
                    name = Core.bundle.get(subname);
                }
            }

            EpsUi.BaseDialogText(name, text, duration);
        }
    }*/
}
