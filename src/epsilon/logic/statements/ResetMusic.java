package epsilon.logic.statements;

import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.ResetMusicI;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;

public class ResetMusic extends LStatement{
    public String musicName = "myLove", delay = "15";

    public ResetMusic(String[] tokens){
        musicName = tokens[1];
        delay = tokens[2];
    }

    public ResetMusic(){}

    @Override
    public void build(Table table){
        rebuild(table);
    }

    void rebuild(Table table){
        table.add(" Music name ");

        fields(table, musicName, v -> musicName = v);

        table.add(" music in ");

        fields(table, delay, v -> delay = v);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new ResetMusicI(builder.var(musicName), builder.var(delay));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("resetmusic");
        builder.append(" ");
        builder.append(musicName);
        builder.append(" ");
        builder.append(delay);
    }
}
