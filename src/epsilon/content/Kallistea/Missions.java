package epsilon.content.Kallistea;

import epsilon.EpsilonVars;
import epsilon.world.Mission;

public class Missions {
    public static Mission mission1;

    public static void load(){
        if (EpsilonVars.mission1 == false) {
            mission1 = new Mission("mission1");
        } else{
            mission1 = new Mission("mission1a");
        }
    }
}
