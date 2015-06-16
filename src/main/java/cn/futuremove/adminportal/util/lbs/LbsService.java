package cn.futuremove.adminportal.util.lbs;

/**
 * Created with IntelliJ IDEA.
 * User: figoXu
 * Date: 14-11-10
 * Time: 上午10:44
 * 天圆地方计算法，适用于小范围内
 * 参考文档地址：http://www.cnblogs.com/ycsfwhh/archive/2010/12/20/1911232.html
 * http://blog.163.com/ezy_dk/blog/static/166651492201221445753585/
 */
public class LbsService {
    private static final double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @param lat1 经度 1
     * @param lng1 纬度 1
     * @param lat2 经度 2
     * @param lng2 纬度 2
     * @return 距离多少米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(
                                Math.sin(a / 2)
                                , 2) +
                                Math.cos(radLat1) *
                                        Math.cos(radLat2) *
                                        Math.pow(Math.sin(b / 2), 2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     *
     * @param lbsVo    当前位置
     * @param distance 附近多少米
     * @return
     */
//    public static LbsNearbyVo nearby(LbsVo lbsVo, Integer distance) {
//
//        double dLng = 2 * Math.asin(
//                Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(lbsVo.getLat())
//        );
//        dLng = Math.toDegrees(dLng);
//
//
//        double dLat = 300 / EARTH_RADIUS;
//        dLat = Math.toDegrees(dLat);
//
//        if(dLng<=0){
//            dLng *= -1;
//        }
//        if(dLat<=0){
//            dLat *= -1;
//        }
//
//        LbsNearbyVo lbsNearbyVo = new LbsNearbyVo();
//        Double latMin = lbsVo.getLat() - dLat;
//        Double latMax = lbsVo.getLat() + dLat;
//        Double lngMin = lbsVo.getLng() - dLng;
//        Double lngMax = lbsVo.getLng() + dLng;
//
//        lbsNearbyVo.setLatMin(latMin);
//        lbsNearbyVo.setLatMax(latMax);
//        lbsNearbyVo.setLngMin(lngMin);
//        lbsNearbyVo.setLngMax(lngMax);
//
//        return lbsNearbyVo;
//    }


    /**
     * http://netyum.blog.163.com/blog/static/14175022013610104150928/
     * @param lbsVo
     * @param distance
     * @return
     */
    public static LbsNearbyVo nearby(LbsVo lbsVo, Integer distance) {
        double dLng = 0.010520*distance/1000;
        double dLat = 0.009000*distance/1000;

        LbsNearbyVo lbsNearbyVo = new LbsNearbyVo();
        Double latMin = lbsVo.getLat() - dLat;
        Double latMax = lbsVo.getLat() + dLat;
        Double lngMin = lbsVo.getLng() - dLng;
        Double lngMax = lbsVo.getLng() + dLng;

        lbsNearbyVo.setLatMin(latMin);
        lbsNearbyVo.setLatMax(latMax);
        lbsNearbyVo.setLngMin(lngMin);
        lbsNearbyVo.setLngMax(lngMax);

        return lbsNearbyVo;


//        lng X坐标 1000米范围系数 ±0.010520
//
//        lat Y坐标 1000米范围系数 ±0.009000
//
//        500米=1000米系数除以2 注意系数精度，四舍五入
//
//        2000米=1000米系数乘以2
//
//        3000米=1000米系数乘以3
//
//        5000米=5000米系数乘以5

    }




    public static void main(String[] args) {
        double v = Math.toDegrees(3);
       // logger.trace(v);
    }


}
