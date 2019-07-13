public class Lst_copy {

    private Lst_copy(){
    }


    private void calcul() {


//Long = -72.866      #Longitude of location in question (BMX LAT = 40.869 [40deg 52' 8"], BMX LONG = -72.866 [-72deg 51' 57"], Custer LONG = -72.435)
      double  Long = 1.887 ;

//Calculate longitude in DegHHMM format for edification of user:
        char hemisphere = 'W';
        if (Long > 0)        //if the number is positive it's in the Eastern hemisphere
        hemisphere = 'E';
        int LongDeg = (int) Long;
        double LongMin = (Long - (int) Long) * 60;
        double LongSec = (LongMin - (int) LongMin) * 60;
        LongMin = (int)LongMin;
        LongSec = (int) LongSec;

        //print '\n\n\nThe longitude is set to %sdeg, [%s %sdeg %s\' %s\"]' %(Long, hemisphere, LongDeg, LongMin, LongSec)
       // TD = raw_input('\nEnter the UTC time and date as MMDDYY HHMM. (UTC = EST+5, EDT+4):\n')
        String TD = "071319 1727";

//split TD into individual variables for month, day, etc. and convert to floats:
        double MM = Double.parseDouble(TD.substring(0,2));//(TD[0;:2]);
        double DD =  Double.parseDouble(TD.substring(2,4));//(TD[2;:4]);
        double YY = Double.parseDouble(TD.substring(4,6));//(TD[4;:6]);
        YY = YY+2000;
        double hh =  Double.parseDouble(TD.substring(7,9));//TD[7;:9]);
        double mm =  Double.parseDouble(TD.substring(9,11));//(TD[9;:11]);

//convert mm to fractional time:
        mm = mm/60;

//reformat UTC time as fractional hours:
        double UT = hh + mm;

//calculate the Julian date:
        double JD = (367 * YY) - (int) ((7 * (YY + (int) ((MM + 9) / 12))) / 4) + (int) ((275 * MM) / 9) + DD + 1721013.5 + (UT / 24);
       System.out.println(JD);
        // print '\nJulian Date: JD%s' %(JD)

//calculate the Greenwhich mean sidereal time:
        double GMST = 18.697374558 + 24.06570982441908 * (JD - 2451545);
        GMST = GMST % 24 ;   //use modulo operator to convert to 24 hours
        double GMSTmm = (GMST - (int) (GMST)) * 60;;        //convert fraction hours to minutes
        double GMSTss = (GMSTmm - (int) (GMSTmm)) * 60;;    //convert fractional minutes to seconds
        int GMSThh = (int) (GMST);
        GMSTmm = (int)(GMSTmm);
        GMSTss = (int)(GMSTss);
               // print '\nGreenwhich Mean Sidereal Time: %s:%s:%s' %(GMSThh, GMSTmm, GMSTss)
        System.out.println(GMSThh);
        System.out.println(GMSTmm);
        System.out.println(GMSTss);


//Convert to the local sidereal time by adding the longitude (in hours) from the GMST.
//(Hours = Degrees/15, Degrees = Hours*15)
        Long = Long/15  ;    //Convert longitude to hours
        double LST = GMST + Long;;   //Fraction LST. If negative we want to add 24...
        if (LST < 0)  LST = LST +24;
        double LSTmm = (LST - (int) (LST)) * 60;;        //convert fraction hours to minutes
        double LSTss = (LSTmm - (int) (LSTmm)) * 60;;     //convert fractional minutes to seconds
        int LSThh = (int) (LST);
        LSTmm = (int)(LSTmm);
        LSTss = (int)(LSTss);

              //  print '\nLocal Sidereal Time %s:%s:%s \n\n' %(LSThh, LSTmm, LSTss)
        System.out.println("Local Sidereal Time");

        System.out.println(LSThh);
        System.out.println(LSTmm);
        System.out.println(LSTss);

    }


    public static void main(String[] args){
        new Lst_copy().calcul();
    }
}
