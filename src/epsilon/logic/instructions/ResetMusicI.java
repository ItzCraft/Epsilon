package epsilon.logic.instructions;

import arc.Events;
import arc.util.Time;
import epsilon.type.EpsilonEventTypes;
import mindustry.logic.LExecutor;
import mindustry.logic.LVar;

public class ResetMusicI implements LExecutor.LInstruction {
    public LVar musicName, delay;

    public ResetMusicI(LVar musicName, LVar delay){
        this.musicName = musicName;
        this.delay = delay;
    }

    public ResetMusicI(){}

    @Override
    public void run(LExecutor exec){;
        Events.fire(new EpsilonEventTypes.MusicResetEvent());
        if(delay.obj() instanceof Integer delayN){
            int delayB = delayN;
            while (delayB <= 0){
                delayB -= Time.delta;
            }
            if(musicName.obj() instanceof String musicN){
                //EpsSoundControl.play(musicN);
            }
        }
    }
}