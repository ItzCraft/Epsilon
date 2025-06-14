package epsilon.logic.statements;

import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.SetVarsI;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

public class SetVars extends LStatement{
    public String var = ":3";

    public SetVars(String[] tokens){
        var = tokens[1];
    }

    public SetVars(){}

    @Override
    public void build(Table table){
        rebuild(table);
    }

    void rebuild(Table table){
        table.add(" variable ");

        fields(table, var, v -> var = v);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new SetVarsI(builder.var(var));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("setvars");
        builder.append(" ");
        builder.append(var);
    }
}
