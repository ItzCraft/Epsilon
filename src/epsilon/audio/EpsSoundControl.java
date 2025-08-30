package epsilon.audio;

import arc.Events;
import epsilon.EpsilonVars;
import epsilon.type.EpsilonEventTypes;
import mindustry.Vars;
import mindustry.audio.SoundControl;

public class EpsSoundControl extends SoundControl{

    public EpsSoundControl(){
        Events.on(EpsilonEventTypes.MusicResetEvent.class, e -> {
            playOnce(Vars.tree.loadMusic(EpsilonVars.musicName));
        });
    }
}
