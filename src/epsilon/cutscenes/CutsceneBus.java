package epsilon.cutscene;

import arc.Core;
import epsilon.util.TimeQueue;

public class CutsceneBus extends TimeQueue<CutsceneBase> {
    public boolean skipping = false;

    public void skip() {
        skipping = true;
        if (current != null) {
            current.skip();
        }

        while (!queue.isEmpty()) {
            CutsceneBase cutscenebase = queue.removeLast();
            cutscenebase.skip();
        }

        skipping = false;

        current = null;
        queue.clear();
      }
} 
