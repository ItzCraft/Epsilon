package epsilon.logic;

import arc.Core;
import mindustry.logic.LVar;
import mindustry.logic.*;
import mindustry.logic.LExecutor.*;
import epsilon.ui.EpsUi;

public class DialogLInstuctions{
    public static abstract class DialogControlInstruction implements LInstruction{
        public LVar duration;
        public String unitIconName;

        public DialogControlInstruction(LVar duration, String unitIconName){
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

            EpsUi.showDialog(unitIconName, text, duration.numf());
        } 
    }

    /*public static abstract class BaseDialogTextInstruction implements LInstruction{
        public String name = template;
        public LVar duration;

        public BaseDialogTextInstruction(String name, LVar duration){
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

            EpsUi.BaseDialogText(name, text, duration.numf());
        }
    }*/
}
