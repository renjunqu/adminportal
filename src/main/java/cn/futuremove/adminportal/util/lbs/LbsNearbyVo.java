package cn.futuremove.adminportal.util.lbs;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 14-11-12
 * Time: 下午12:07
 * To change this template use File | Settings | File Templates.
 */
public class LbsNearbyVo {

    private Double latMin;
    private Double latMax;
    private Double lngMin;
    private Double lngMax;

    public Double getLatMin() {
        return latMin;
    }

    public void setLatMin(Double latMin) {
        this.latMin = latMin;
    }

    public Double getLatMax() {
        return latMax;
    }

    public void setLatMax(Double latMax) {
        this.latMax = latMax;
    }

    public Double getLngMin() {
        return lngMin;
    }

    public void setLngMin(Double lngMin) {
        this.lngMin = lngMin;
    }

    public Double getLngMax() {
        return lngMax;
    }

    public void setLngMax(Double lngMax) {
        this.lngMax = lngMax;
    }


    public void processValue4Show(){
        latMin = formatValue4Show(latMin);
        latMax = formatValue4Show(latMax);
        lngMin = formatValue4Show(lngMin);
        lngMax = formatValue4Show(lngMax);

    }


    public static Double formatValue4Show(Double val){
        if(val==null){
            return null;
        }
        BigDecimal valueFrom = new BigDecimal(val);
        Double value4Show = valueFrom.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value4Show;
    }
}
