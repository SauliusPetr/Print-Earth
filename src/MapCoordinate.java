package src;

public class MapCoordinate {
    public final double LATITUDE ;
    public final double LONGITUDE ;
    public final double ALTITUDE ;
    final int R = 6371;

    public MapCoordinate(double latitude, double longitude, double altitude) {
        LATITUDE = latitude;
        LONGITUDE = longitude;
        ALTITUDE =altitude;
    }
    public double distanceTo(double lat2,double lon2){
        double latDistance = Math.toRadians(lat2 - LATITUDE);
        double lonDistance = Math.toRadians(lon2 - LONGITUDE);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(LATITUDE)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000000; // convert to meters

        distance = Math.pow(distance, 2)+ Math.pow(0.0,2);
        System.out.println("Distance from one point to another in km: "+Math.sqrt(distance));
        return Math.sqrt(distance);
    }
    public int compareTo(MapCoordinate op){
        int compare = 0;

        return compare;
    }

}
