package astronomie;

import static java.lang.Math.PI;
import static java.lang.Math.asin;

public class AzAlt_DecRa extends AUtils {

    private static double LSTdeg;

    public static void main(String[] args) {
        Lst lst = new Lst();
        LSTdeg = lst.getLSTdeg();
        //TEST
        LSTdeg = 304.80762;
        new AzAlt_DecRa().calcul();
    }

    private void calcul() {
        double LAT = 52.5;
        double AZ = 269.14633014;
        double ALT = 49.16912566;
        double sindDEC =  sind(ALT) * sind(LAT)  + cosd(ALT) * cosd(LAT)  * cosd(AZ);
        double DEC = asin(sindDEC) * 180 / PI;
        System.out.println("DEC = "+DEC);

        double sindH = -sind(AZ) * cosd(ALT) / cosd(DEC);
        double HA = asin(sindH) * 180 / PI;
        System.out.println("H = "+HA);
        System.out.println();

        double RA = LSTdeg - HA;
        System.out.println("RA = "+RA);
    }
}
