package epsilon.content.Kallistea;

import epsilon.type.EpsilonSectorPreset;
import mindustry.type.SectorPreset;

public class KallisteaSectorPresets {
    public static EpsilonSectorPreset firstRiddles, abandonedOutpost, transmission;

    public static void load(){
        firstRiddles = new EpsilonSectorPreset("first-riddles", EpsilonPlanets.kallistea, 13){{
           alwaysUnlocked = true;
           difficulty = 1;
           overrideLaunchDefaults = true;
           captureWave = 2;
        }};
        abandonedOutpost = new EpsilonSectorPreset("abandoned-outpost", EpsilonPlanets.kallistea, 14){{
            difficulty = 2;
            overrideLaunchDefaults = true;
            captureWave = 4;
            requireUnlock = false;
        }};
    }
}
