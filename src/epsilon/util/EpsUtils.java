package epsilon.util;

import arc.util.*;

public class EpsUtils{



    public void Wait(int time){
        time *= 100;
        while (time <= 0){
            time -= Time.delta;
        }
    }
}