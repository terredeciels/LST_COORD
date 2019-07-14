package astronomie;

import static java.lang.Math.*;

public class DecRa_AzAlt extends AUtils {

    private static double LSTdeg;

    public static void main(String[] args) {
        Lst lst = new Lst();
        LSTdeg = lst.getLSTdeg();
        //TEST
        LSTdeg = 304.80762;
        new DecRa_AzAlt().calcul();
    }

    private void calcul() {
        //exemple 1
        double RA = 250.425;
        double DEC = 36.466667;
        double LAT = 52.5;

        double HA = LSTdeg - RA;
        System.out.println("HA = " + HA + " deg");
        double sindALT = sind(DEC) * sind(LAT) + cosd(DEC) * cosd(LAT) * cosd(HA);
        double ALT = asin(sindALT) * 180 / PI;
        System.out.println("ALT = " + ALT + " deg");

        double cosdA = (sind(DEC) - sind(ALT) * sind(LAT)) / (cosd(ALT) * cosd(LAT));
        double A = acos(cosdA) * 180 / PI;
        System.out.println("A = " + A + " deg");

        double AZ;
        if (sind(HA) < 0) AZ = A;
        else AZ = 360 - A;
        System.out.println("AZ = " + AZ + " deg");
    }


}
