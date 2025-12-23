package epsilon.logic.instructions;

import arc.Events;
import epsilon.type.EpsilonEventTypes;
import mindustry.logic.*;

public class ResetMusicI implements LExecutor.LInstruction {
    public LVar musicName;

    public ResetMusicI(LVar musicName){
        this.musicName = musicName;
    }

    public ResetMusicI(){}

    @Override
    public void run(LExecutor exec){;
        if(musicName.obj() instanceof String musicN){
            Events.fire(new EpsilonEventTypes.MusicResetEvent(musicN));
        }
    }
}
