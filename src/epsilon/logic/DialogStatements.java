package epsilon.logic;

import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import mindustry.logic.*;
import mindustry.logic.LExecutor.*;
import mindustry.ui.*;
//import epsilon.logic.DialogLInstuctions.*;

/*public class DialogStatements{
    // Code from Mechanical Arms mod by MinRi2
    private static final Seq<Prov<DialogLStatement>> dialogAllStatements = Seq.with(
    ControlSwitchStatement::new
    );
    
    
    public static void register(){
        Seq<Prov<DialogLStatement>> seq = dialogAllStatements.map(prov -> prov::get);
        LogicIO.allStatements.addAll(seq);

 for(Prov<DialogLStatement> prov : dialogAllStatements){
            DialogLStatement example = prov.get();
            LAssembler.customParsers.put(example.markName, example::read);
        }
    }

    public static class DialogControlStatement extends LStatement{
        public LVar duration = 5;
        public String unitIconName = "epsilon-assets/frog.png";

        @Override
        public LCategory category(){
            return DialogLogic.dialogCategory;
        }

        @Override
        public void build(Table table){
            rebuild(table);
        }

        void rebuild(Table table){
            table.clearChildren();

            table.add(" unit name ");
            fields(table, unitIconName, path -> unitIconName = path);
            table.row();
            table.add(" for ");
            fields(table, duration, str -> duration = str);
            table.add(" sec ");
    }
}*/
