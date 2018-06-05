package com.clearT;


public class PersonData {
    public static final int NUM_OF_PEOPLE = 3;
    public Soldier[] soldiers = new Soldier[NUM_OF_PEOPLE];


    //파일에서 입력받아 데이터를 저장하게 해야 함. 수정 필요
    PersonData() {
        soldiers[0] = new Soldier("김육군",2018,6,4,Soldier.SoldierType.Army);
        soldiers[1] = new Soldier("이해병",2018,5,24,Soldier.SoldierType.Marine);
        soldiers[2] = new Soldier("박공군",2018,3,16,Soldier.SoldierType.AirForce);

        //이후 데이터를 추가함
    }

    public int SearchName(String name){

        for(int i = 0; i < NUM_OF_PEOPLE; i++){
            if(this.soldiers[i].getName().equalsIgnoreCase(name)) {
                System.out.println("search index :" + i);
                return i;
            }
        }
        System.out.println("search index : -1 : cannot find");
        return -1;
    }

}
