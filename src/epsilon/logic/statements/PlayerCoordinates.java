package epsilon.logic.statements;

import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.PlayerCoordinatesI;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

public class PlayerCoordinates extends LStatement {
    public String x = "0", y="0";

    public PlayerCoordinates(String[] tokens){
        x = tokens[1];
        y = tokens[2];
    }

    public PlayerCoordinates(){}

    @Override
    public void build(Table table){
        table.add(" x ");

        fields(table, x, v -> x = v);

        table.add(" y ");

        fields(table, y, i -> y = i);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new PlayerCoordinatesI(builder.var(x), builder.var(y));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("playercoordinates");
        builder.append(" ");
        builder.append(x);
        builder.append(" ");
        builder.append(y);
    }
}
