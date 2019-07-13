package astronomie;

import static java.lang.Double.parseDouble;

public class Lst {

    private Lst() {
    }

    public static void main(String[] args) {
        new Lst().calcul();
    }

    private void calcul() {

        double longitude = 1.887;
        System.out.println("longitude: "+longitude);
        //Calculate longitude in DegHHMM format for edification of user:
        //char hemisphere = 'W';
        double LongMin = (longitude - (int) longitude) * 60;
        double LongSec = (LongMin - (int) LongMin) * 60;

        //print '\n\n\nThe longitude is set to %sdeg, [%s %sdeg %s\' %s\"]' %(Long, hemisphere, LongDeg, LongMin, LongSec)
        // TD = raw_input('\nEnter the UTC time and date as MMDDYY HHMM. (UTC = EST+5, EDT+4):\n')

        //exemple avec HH-02 (UTC)
        String TD = "071319 1727";
        System.out.println("Date: HH-02 (UTC) " + TD);


        //split TD into individual variables for month, day, etc. and convert to floats:
        double MM = parseDouble(TD.substring(0, 2));//(TD[0;:2]);
        double DD = parseDouble(TD.substring(2, 4));//(TD[2;:4]);
        double YY = parseDouble(TD.substring(4, 6));//(TD[4;:6]);
        YY = YY + 2000;
        double hh = parseDouble(TD.substring(7, 9));//TD[7;:9]);
        double mm = parseDouble(TD.substring(9, 11));//(TD[9;:11]);

        //convert mm to fractional time:
        mm = mm / 60;
        //reformat UTC time as fractional hours:
        double UT = hh + mm;

        //calculate the Julian date:
        double JD = getJD(MM, DD, YY, UT);
        System.out.println("Julian date: "+JD);
       // System.out.println("JD: "+JD);

        //calculate the Greenwhich mean sidereal time:
        double GMST = 18.697374558 + 24.06570982441908 * (JD - 2451545);
        GMST = GMST % 24;   //use modulo operator to convert to 24 hours
        double GMSTmm = (GMST - (int) (GMST)) * 60; //convert fraction hours to minutes
        double GMSTss = (GMSTmm - (int) (GMSTmm)) * 60; //convert fractional minutes to seconds
        int GMSThh = (int) (GMST);
        GMSTmm = (int) (GMSTmm);
        GMSTss = (int) (GMSTss);

        // print '\nGreenwhich Mean Sidereal Time: %s:%s:%s' %(GMSThh, GMSTmm, GMSTss)
        //System.out.println(GMSThh);
        //System.out.println(GMSTmm);
        //System.out.println(GMSTss);

        //Convert to the local sidereal time by adding the longitude (in hours) from the GMST.
        //(Hours = Degrees/15, Degrees = Hours*15)
        longitude = longitude / 15;    //Convert longitude to hours
        double LST = GMST + longitude; //Fraction LST. If negative we want to add 24...
        if (LST < 0) LST = LST + 24;
        double LSTmm = (LST - (int) (LST)) * 60; //convert fraction hours to minutes
        double LSTss = (LSTmm - (int) (LSTmm)) * 60; //convert fractional minutes to seconds
        int LSThh = (int) (LST);
        LSTmm = (int) (LSTmm);
        LSTss = (int) (LSTss);

        //  print '\nLocal Sidereal Time %s:%s:%s \n\n' %(LSThh, LSTmm, LSTss)
        System.out.println("Local Sidereal Time (LST): "+ LSThh +"h "+ Math.round(LSTmm)+"m "+Math.round(LSTss)+"s ");
       // System.out.println("LST: " + LSThh +"h "+ Math.round(LSTmm)+"m "+Math.round(LSTss)+"s ");
       // System.out.println(LSThh);
       // System.out.println(LSTmm);
       // System.out.println(LSTss);
    }

    //calculate the Julian date:
    private double getJD(double MM, double DD, double YY, double UT) {
        return (367 * YY) - (int) ((7 * (YY + (int) ((MM + 9) / 12))) / 4) + (int) ((275 * MM) / 9) + DD + 1721013.5 + (UT / 24);
    }
}
