package epsilon.cutscenes;

import arc.Events;
import arc.struct.Queue;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.game.EventType;

public class CutsceneControl {
        public boolean waiting = false;
        public float waitSpacing = 60;
        public float waitTimer = 0;

        public CutsceneBus mainBus;
        public Queue<CutsceneBus> waitingBuses = new Queue<>();
        public Seq<CutsceneBus> subBuses = new Seq<>();

        public CutsceneControl() {
            Events.on(EventType.WorldLoadEvent.class, event -> clear());
        }

        public void update(){
            //update the main action bus and remove completed action bus
            if(mainBus != null){
                mainBus.update();
                if(mainBus.complete()) {
                    mainBus = null;
                    waiting = true;
                    CutsceneUI.reset();
                }
            }

            //increase timer when waiting
            if (waiting) {
                waitTimer += Time.delta;
                if (waitTimer >= waitSpacing) {
                    waitTimer = 0;
                    waiting = false;
                }
            }

            //change current main bus when waiting finished
            if (mainBus == null && !waiting && !waitingBuses.isEmpty()) {
                mainBus = waitingBuses.removeLast();
            }

            //update the sub action bus
            for(int i = 0; i < subBuses.size; i++){
                    CutsceneBus bus = subBuses.get(i);
                    if(bus.complete())subBuses.remove(i);
                    bus.update();
            }

            CutsceneUI.update();
        }

        public void clear(){
                waiting = false;
                waitTimer = 0;
                mainBus = null;
                waitingBuses.clear();
                subBuses.clear();
        }

        public void addMainBus(CutsceneBus bus) {
                if (mainBus == null) {
                        mainBus = bus;
                }else {
                        waitingBuses.add(bus);
                }
        }

        public void addCutsceneBus(CutsceneBus bus) {
                subBuses.add(bus);
        }
}
