package epsilon.content.Kallistea;

import arc.Core;
import arc.Events;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.*;
import arc.audio.*;
import arc.struct.*;
import arc.util.Log;
import mindustry.Vars;
import mindustry.audio.SoundControl;
import mindustry.core.GameState;
import mindustry.game.EventType.*;

import java.lang.reflect.Field;

//note: mostly code is from aquarion
public class EpsMusic {
    public static Seq<Music> epsAmbientMusic = new Seq<>();
    public static Seq<Music> epsDarkMusic = new Seq<>();
    public static Seq<Music> epsBossMusic = new Seq<>();

    public static String[] epsAmbientList = {"in-the-secrets", "its-over"};
    public static String[] epsDarkList = {};
    public static String[] epsBossList = {"the-end-is-near"};

    public static Seq<Music> origAmbientMusic;
    public static Seq<Music> origDarkMusic;
    public static Seq<Music> origBossMusic;

    public static Field currentMus;

    public static Music inevitability, planetOfDespair, youreSafeNow, alarm;

    public static Sound highShoot, electricShoot, scan;

    // Don't change from outside I trust you by putting it in public
    public static boolean enabled = true;

    public static void load() {
        epsAmbientMusic = loadMultiple(epsAmbientList, "epsilon/ambient");
        epsDarkMusic = loadMultiple(epsDarkList, "epsilon/dark");
        epsBossMusic = loadMultiple(epsBossList, "epsilon/boss");

        origAmbientMusic = Vars.control.sound.ambientMusic.copy();
        origDarkMusic = Vars.control.sound.darkMusic.copy();
        origBossMusic = Vars.control.sound.bossMusic.copy();

        //other music
        inevitability = loadMusic("inevitability");
        planetOfDespair = loadMusic("planet-of-despair");
        youreSafeNow = loadMusic("youre-safe-now");
        alarm = loadMusic("alarm");

        //Sound region
        highShoot = loadSound("high-shoot");
        electricShoot = loadSound("electric-shoot");
        scan = loadSound("scan");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless){
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        }else{
            return new Sound();
        }
    }
    private static Music loadMusic(String musicName){
        if(!Vars.headless){
            String name = "music/epsilon/other/" + musicName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Music music = new Music();

            AssetDescriptor<?> desc = Core.assets.load(path, Music.class, new MusicLoader.MusicParameter(music));
            desc.errored = Throwable::printStackTrace;

            return music;

        }else{
            return new Music();
        }
    }

    public static Seq<Music> loadMultiple(String[] filenames, String folder) {
        Seq<Music> result = new Seq<>();

        for (String filename : filenames) {
            Music music = Vars.tree.loadMusic(folder + "/" + filename);
            if (music != null) {
                result.add(music);
            } else {
                Log.warn("Failed to load music: " + filename);
            }
        }

        return result;
    }

    public static void attach() {
        Events.on(WorldLoadEvent.class, e -> {
            if (Vars.state.rules.planet.parent != null && Vars.state.rules.planet.parent.name.equals("epsilon-kallistea")) {
                enableCustomMusic();
            } else if (enabled) {
                disableCustomMusic();
            }
        });

        Events.on(StateChangeEvent.class, e -> {
            if (e.from != GameState.State.menu && e.to == GameState.State.menu) {
                disableCustomMusic();
            }
        });

        reflectCurMus();
    }

    public static void enableCustomMusic() {
        Vars.control.sound.ambientMusic = Seq.with(epsAmbientMusic);
        Vars.control.sound.darkMusic = Seq.with(epsDarkMusic);
        Vars.control.sound.bossMusic = Seq.with(epsBossMusic);

        enabled = true;
    }

    public static void disableCustomMusic() {
        Vars.control.sound.ambientMusic = Seq.with(origAmbientMusic);
        Vars.control.sound.darkMusic = Seq.with(origDarkMusic);
        Vars.control.sound.bossMusic = Seq.with(origBossMusic);
        enabled = false;
    }

    public static void reflectCurMus() {
        try {
            currentMus = SoundControl.class.getDeclaredField("current");
            currentMus.setAccessible(true);
        } catch (Exception e) {
            Log.err("Failed to set visibility of music things");
            Log.err(e);
        };
        }
}