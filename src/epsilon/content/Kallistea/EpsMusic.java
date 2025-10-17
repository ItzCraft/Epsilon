package epsilon.content.Kallistea;

import arc.Core;
import arc.Events;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.*;
import arc.struct.*;
import arc.util.Log;
import epsilon.content.Kallistea.blocks.KallisteaStorage;
import mindustry.content.Planets;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Musics;

//CODE FROM OMALOON
public class EpsMusic{
    public static final ObjectMap<String, Seq<Music>> musicSets = new ObjectMap<>();

    public static Music
            // Vanilla
            launch, land,
    // Epsilon launch
    eLaunch,
    // Kallistea music
    alarm,
    // Other
    inevitability,
    // Ambient
    inTheSecrets;
    // Dark

    // Boss
    ;

    public static Sound
    // shoots
    highShoot = new Sound();

    public static void load(){
        initializeMusics();
        initializeMusicSets();
        setupEventHandlers();
    }

    /** Initializes individual music tracks. */
    private static void initializeMusics(){
        // Vanilla
        launch = Musics.launch;
        land = Musics.land;

        // Epsilon
        eLaunch = loadMusic("e-launch");

        // Kallistea
        alarm = loadMusic("other/alarm");

        // Load Epsilon musics
        String[] otherTracks = {"inevitability"};
        String[] ambientTracks = {"in-the-secrets"};
        //String[] darkTracks = {"fragile", "solidFire", "soredLuna"};
        //String[] bossTracks = {""};

        loadMusicSet("epsilon/other/", otherTracks);
        loadMusicSet("epsilon/ambient/", ambientTracks);
        //loadMusicSet("epsilon/dark/", darkTracks);
        //loadMusicSet("epsilon/boss/", bossTracks);

        //Sounds
        highShoot = loadSound("high-shoot");
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
    private static void loadMusicSet(String basePath, String[] trackNames){
        for(String track : trackNames){
            try{
                Music music = loadMusic(basePath + track);
                EpsMusic.class.getField(track).set(null, music);
            }catch(Exception e){
                Log.err("Failed to load music: " + track, e);
            }
        }
    }

    /** Initializes music sets for different game scenarios. */
    private static void initializeMusicSets(){
        musicSets.put("vanillaAmbient", new Seq<>(Vars.control.sound.ambientMusic));
        musicSets.put("vanillaDark", new Seq<>(Vars.control.sound.darkMusic));
        musicSets.put("vanillaBoss", new Seq<>(Vars.control.sound.bossMusic));

        musicSets.put("epsilonOther", Seq.with(inevitability));
        musicSets.put("epsilonAmbient", Seq.with(inTheSecrets));
        //musicSets.put("glasmoreDark", Seq.with(fragile, solidFire, soredLuna));
        //musicSets.put("glasmoreBoss", Seq.with(buryAlive, chaoticFlames, liquefy, piercingLine));
    }

    /** Sets up event handlers for updating music based on game events. */
    private static void setupEventHandlers(){
        Events.run(EventType.Trigger.update, EpsMusic::updateLaunchMusic);
        Events.on(EventType.WorldLoadEvent.class, e -> {
            updateLandMusic();
            updatePlanetMusic();
        });
    }

    /** Updates launch music based on current planet. */
    private static void updateLaunchMusic(){
        Musics.launch = (Vars.ui.planet.state.planet == EpsilonPlanets.kallistea)
                ? eLaunch
                : launch;
    }

    /** Updates landing music based on core block type. */
    private static void updateLandMusic(){
        Vars.state.rules.defaultTeam.cores().each(core ->
                Musics.land = (core.block == KallisteaStorage.coreObscurityBroken)
                        ? alarm
                        : land);
    }

    /** Updates planet music sets based on a current planet. */
    private static void updatePlanetMusic(){
        if(Vars.state.rules.planet != Planets.sun){
            String prefix = Vars.state.rules.planet == EpsilonPlanets.kallistea ? "epsilon" : "vanilla";
            setMusicSet(prefix + "Ambient", Vars.control.sound.ambientMusic);
            setMusicSet(prefix + "Dark", Vars.control.sound.darkMusic);
            setMusicSet(prefix + "Boss", Vars.control.sound.bossMusic);
        }else{
            // For 'any' environment (Planets.sun), mix mod and vanilla music
            mixMusic();
        }
    }

    /** Mixes vanilla and mod music sets. */
    private static void mixMusic(){
        mixMusicSets("vanillaAmbient", "epsilonAmbient", Vars.control.sound.ambientMusic);
        //mixMusicSets("vanillaDark", "glasmoreDark", Vars.control.sound.darkMusic);
        //mixMusicSets("vanillaBoss", "glasmoreBoss", Vars.control.sound.bossMusic);
    }

    /**
     * Mixes two music sets and assigns the result to a target set.
     * @param target Target sequence to store the mixed music.
     */
    private static void mixMusicSets(String vanillaSetName, String modSetName, Seq<Music> target){
        Seq<Music> vanillaSet = musicSets.get(vanillaSetName);
        Seq<Music> modSet = musicSets.get(modSetName);
        if(vanillaSet != null && modSet != null){
            target.clear();
            target.addAll(vanillaSet);
            target.addAll(modSet);
        }
    }

    /**
     * Sets a music set to a target sequence.
     * @param setName Name of the music set to use.
     * @param target Target sequence to update.
     */
    private static void setMusicSet(String setName, Seq<Music> target){
        Seq<Music> set = musicSets.get(setName);
        if(set != null){
            target.set(set);
        }
    }

    /** Loads a music file from the game's asset tree. */
    private static Music loadMusic(String name){
        return Vars.tree.loadMusic(name);
    }
}