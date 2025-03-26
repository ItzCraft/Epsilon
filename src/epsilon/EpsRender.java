package epsilon;

public class EpsRender extends Render{
    public EpsRender(){
           
    }

    public void showLanding(LaunchAnimator landCore){
        this.launchAnimator = landCore;
        launching = false;
        landTime = landCore.launchDuration();

        landCore.beginLaunch(false);
        camerascale = landCore.zoomLaunch();
    }

    public void showLaunch(LaunchAnimator landCore){
        control.input.config.hideConfig();
        control.input.planConfig.hide();
        control.input.inv.hide();

        this.launchAnimator = landCore;
        launching = true;
        landTime = landCore.launchDuration();

        Music music = landCore.launchMusic();
        music.stop();
        music.play();
        music.setVolume(settings.getInt("musicvol") / 100f);

        landCore.beginLaunch(true);
    }
}