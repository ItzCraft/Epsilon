package epsilon.audio;

import arc.Events;
import arc.audio.Music;
import arc.struct.ObjectMap;
import epsilon.content.Kallistea.EpsMusic;
import epsilon.type.EpsilonEventTypes;
import mindustry.audio.SoundControl;

public class EpsSoundControl extends SoundControl{
    public static ObjectMap<String, Music> musicMap = new ObjectMap<>();

    static{
        musicMap.put("alarm", EpsMusic.alarm);
        musicMap.put("inevitability", EpsMusic.inevitability);
        musicMap.put("planet-of-despair", EpsMusic.planetOfDespair);
        musicMap.put("youre-safe-mow", EpsMusic.youreSafeNow);
    }
    public EpsSoundControl(){
        Events.on(EpsilonEventTypes.MusicResetEvent.class, e -> {
            if(current != null){
                fade = 1f;
                current.stop();
                Music music = musicMap.get(e.musicn);
                current = null;
                playOnce(music);
            }else{
                Music music = musicMap.get(e.musicn);
                playOnce(music);
            }
        });
    }
}
