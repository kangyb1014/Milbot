package com.clearT;

import java.util.Calendar;
import java.util.TimeZone;

public class Soldier {
    public enum SoldierType {Army,Navy,AirForce,Marine,ERROR};
    private final static int PERIOD_OF_ARMY = 21;
    private final static int PERIOD_OF_NAVY = 23;
    private final static int PERIOD_OF_AIRFORCE = 24;
    private final static int PERIOD_OF_MARINE = 21;


    private String name;
    private int enlistYear;
    private int enlistMonth;
    private int enlistDay;
    private SoldierType soldierType;

    Soldier(String name, int enlistYear, int enlistMonth, int enlistDay, SoldierType soldierType){
        this.name = name;
        this.enlistYear = enlistYear;
        this.enlistMonth = enlistMonth;
        this.enlistDay = enlistDay;
        this.soldierType = soldierType;

    }
    public String getName() {
        return name;
    }

    //총 복무기간 리턴
    public int getWorkMonth(){
        if(this.soldierType.equals(SoldierType.Army)){
            return PERIOD_OF_ARMY;
        }
        if(this.soldierType.equals(SoldierType.Navy)){
            return PERIOD_OF_NAVY;
        }
        else if(this.soldierType.equals(SoldierType.AirForce)) {
            return PERIOD_OF_AIRFORCE;
        }
        else if (this.soldierType.equals((SoldierType.Marine))){
            return PERIOD_OF_MARINE;
        }
        else return 0;
    }

    public String StartDayToString(){
        //1월이 0의 값을 가지므로 월에 1을 더해 주어야 한다.
        String startString = this.enlistYear + "년 " + (this.enlistMonth + 1) + "월 " + this.enlistDay + "일";
        return startString;
    }

    public String EndDayToString(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        calendar.set(this.enlistYear, this.enlistMonth, this.enlistDay);
        calendar.add(Calendar.MONTH, this.getWorkMonth());
        calendar.add(Calendar.DATE, -1);

        int year = calendar.get(Calendar.YEAR);
        //January가 0이므로 1을 더해 주어야 한다.
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String EndString = year + "년 " + month + "월 " + day + "일";

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        return EndString;
    }

    public String soldierTypeToString(){
        if(this.soldierType.equals(SoldierType.Army))
            return "육군";
        if(this.soldierType.equals(SoldierType.Navy))
            return "해군";
        if(this.soldierType.equals(SoldierType.AirForce))
            return "공군";
        if(this.soldierType.equals(SoldierType.Marine))
            return "해병";
        else return "error";

    }

    public static SoldierType toSoldierType(String str){
        if(str.equalsIgnoreCase("Army") || str.equalsIgnoreCase("육군"))
            return SoldierType.Army;
        if(str.equalsIgnoreCase("Navy") || str.equalsIgnoreCase("해군"))
            return SoldierType.Navy;
        if(str.equalsIgnoreCase("AirForce") || str.equalsIgnoreCase("공군"))
            return SoldierType.AirForce;
        if(str.equalsIgnoreCase(("Marine"))|| str.equalsIgnoreCase("해병대"))
            return SoldierType.Marine;
        return SoldierType.ERROR;
    }

}
