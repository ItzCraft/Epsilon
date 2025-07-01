package epsilon.logic.statements;

import arc.scene.ui.layout.Table;
import arc.util.Align;
import epsilon.EpsilonVars;
import epsilon.logic.EpsLogicFx;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.CustomEffectI;
import mindustry.logic.*;
import mindustry.ui.Styles;

import static mindustry.Vars.ui;

public class CustomEffect extends LStatement{
    public String effect = "fire", sizerot = "2", color = "%ffaaff", data = "";
    public String x = "0";
    public String y = "0";

    public CustomEffect(String[] tokens){
        effect = tokens[1];
        x = tokens[2];
        y = tokens[3];
        sizerot = tokens[4];
        color = tokens[5];
    }

    public CustomEffect(){}

    @Override
    public void build(Table table){
        rebuild(table);
    }

    void rebuild(Table table){
        table.clearChildren();

        table.button(b -> {
            b.label(() -> effect).growX().wrap().labelAlign(Align.center);
            b.clicked(() -> EpsilonVars.epsEffectsDialog.show(entry -> {
                effect = entry.name;
                build(table);
            }));
        }, Styles.logict, () -> {}).size(150f, 40f).margin(5f).pad(4f).color(table.color).colspan(2);

        EpsLogicFx.EffectEntry entry = EpsLogicFx.get(effect);

        row(table);

        fields(table, "x", x, str -> x = str);
        fields(table, "y", y, str -> y = str);
        row(table);

        if(entry != null){
            if(entry.color){
                fields(table, "color", color, str -> color = str).width(120f);

                col(table, color, res -> {
                    color = "%" + res.toString().substring(0, res.a >= 1f ? 6 : 8);
                    build(table);
                });
            }

            row(table);

            if(entry.size || entry.rotate){
                fields(table, entry.size ? "size" : "rotation", sizerot, str -> sizerot = str);
            }

            if(entry.data != null){
                fields(table, "data", data, str -> data = str);
            }
        }
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new CustomEffectI(EpsLogicFx.get(effect), builder.var(x), builder.var(y), builder.var(sizerot), builder.var(color), builder.var(data));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("customeffect");
        builder.append(" ");
        builder.append(effect);
        builder.append(" ");
        builder.append(x);
        builder.append(" ");
        builder.append(y);
        builder.append(" ");
        builder.append(sizerot);
        builder.append(" ");
        builder.append(color);
        builder.append(" ");
        builder.append(data);
    }
}
