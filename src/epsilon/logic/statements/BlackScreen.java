package epsilon.logic.statements;

import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.SetVarsI;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

// Makes black screen appear for {n} duration of time.
public class BlackScreen extends LStatement{
    public String duration = "3";

    public BlackScreen(String[] tokens){
        duration = tokens[1];
    }

    public BlackScreen(){}

    @Override
    public void build(Table table){
        rebuild(table);
    }

    void rebuild(Table table){
        table.add(" variable ");

        fields(table, duration, v -> duration = v);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new SetVarsI(builder.var(duration));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("blackscreen");
        builder.append(" ");
        builder.append(duration);
    }
}
