package epsilon.logic;

import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import mindustry.logic.*;
import mindustry.logic.LExecutor.*;
import mindustry.ui.*;
import epsilon.logic.DialogLInstuctions.*;

public class DialogStatements{
    public static class DialogControlStatement extends LStatement{
        public int duration = 5f;
        public String unitIconName = "epsilon-frog";

        @Override
        public LCategory category(){
            return DialogsLogic.dialogCategory;
        }

        @Override
        public LInstruction build(LAssembler builder){
            return new DialogControlInstruction(builder.var(duration), unitIconName);
        } 

        @Override
        public void build(Table table){
            rebuild(table);
        }

        void rebuild(Table table){
            table.clearChildren();

            table.add(" unit name ");
            fields(table, unitIconName, str -> unitIconName = str);
            table.row();
            table.add(" for ");
            fields(table, duration, int -> duration = int);
            table.add(" sec ");
        }
    }
}
