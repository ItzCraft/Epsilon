package epsilon.content.Kallistea;


import arc.graphics.Color;
import mindustry.game.Team;
import epsilon.graphics.EpsPal;

public class EpsTeams {
    public static Team incers, ganieris, fenspor;

    public static void load() {
        ganieris = newTeam(7, "ganieris", EpsPal.ganierisTeam);
        incers = newTeam(8, "incers", EpsPal.incersTeam);
        fenspor = newTeam(9, "fenspor", EpsPal.fenspor);
    }

    //modify any of 256 teams' properties
    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);

        team.palette[0] = color;
        //team.palette[1] = color.cpy().mul(0.90f);
        team.palette[1] = color.cpy().mul(0.75f);
        //team.palette[3] = color.cpy().mul(0.60f);
        team.palette[2] = color.cpy().mul(0.50f);

        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }

        return team;
    }
}