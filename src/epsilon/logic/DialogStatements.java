package epsilon.logic;

import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import mindustry.logic.*;
import mindustry.logic.LExecutor.*;
import mindustry.ui.*;
import epsilon.logic.DialogLInstuctions.*;

public class DialogStatements{
        private static final DialogLStatement writer = new DialogLStatement();

    @SuppressWarnings("unchecked")
    private static final Seq<Prov<DialogLStatement>> dialogAllStatements = Seq.with(
    DialogControlStatement::new
    );

    /**
     * Register to LogicIO
     * this will be invoked on added to {@link LCanvas} as example
     * and make customized statement iterable by {@link LogicDialog}
     */
    public static void register(){
        Seq<Prov<LStatement>> seq = dialogAllStatements.map(prov -> prov::get);
        LogicIO.allStatements.addAll(seq);

        // Customized LStatements will register customized reader automatically.
        for(Prov<DialogLStatement> prov : dialogAllStatements){
            DialogLStatement example = prov.get();
            LAssembler.customParsers.put(example.markName, example::read);
        }
    }
    public static class DialogControlStatement extends LStatement{
        public String duration = "5";
        public String unitIconName = "epsilon-frog";

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
            fields(table, duration, str -> duration = str);
            table.add(" sec ");
        }
        @Override
        public LCategory category(){
            return DialogsLogic.dialogCategory;
        }

        @Override
        public LInstruction build(LAssembler builder){
            return new DialogControlInstruction(builder.var(duration), unitIconName);
        }  
    }
}
