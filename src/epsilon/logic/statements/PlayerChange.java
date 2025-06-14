package epsilon.logic.statements;

import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.TextField;
import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.PlayerChangeI;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LExecutor;
import mindustry.logic.LStatement;
import mindustry.type.UnitType;
import mindustry.ui.Styles;

import static mindustry.Vars.iconSmall;

public class PlayerChange extends LStatement{
    public String unit = "@poly";
    public PlayerChange(String[] tokens){
        unit = tokens[1];
    }

    public PlayerChange(){}

    @Override
    public void build(Table table){
        table.add(" unit ");

        TextField field = field(table, unit, str -> unit = str).get();

        table.button(b -> {
            b.image(Icon.pencilSmall);
            b.clicked(() -> showSelectTable(b, (t, hide) -> {
                t.row();
                t.table(i -> {
                    i.left();
                    int c = 0;
                    for(UnitType item : Vars.content.units()){
                        if(!item.unlockedNow() || item.isHidden() || !item.logicControllable) continue;
                        i.button(new TextureRegionDrawable(item.uiIcon), Styles.flati, iconSmall, () -> {
                            unit = "@" + item.name;
                            field.setText(unit);
                            hide.run();
                        }).size(40f);

                        if(++c % 6 == 0) i.row();
                    }
                }).colspan(3).width(240f).left();
            }));
        }, Styles.logict, () -> {}).size(40f).padLeft(-2).color(table.color);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new PlayerChangeI(builder.var(unit));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("playerchange");
        builder.append(" ");
        builder.append(unit);
    }
}
