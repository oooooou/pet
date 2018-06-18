package pet.service;

import org.springframework.stereotype.Service;

@Service
public class CalDistance {

    private static double EARTH_RADIUS = 6378.137;// 单位千米

    private static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }

     public   double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = getRadian(lat1);
        double radLat2 = getRadian(lat2);
        double a = radLat1 - radLat2;// 两点纬度差
        double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s * 1000;
    }

}
