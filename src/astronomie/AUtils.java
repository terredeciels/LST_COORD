package astronomie;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class AUtils {
    public double cosd(double val) {
        return cos(Math.toRadians(val));
    }


    public double sind(double val) {
        return sin(Math.toRadians(val));
    }
}
