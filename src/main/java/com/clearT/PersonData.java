package com.clearT;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class PersonData {
    public static final int MAX_PEOPLE = 50;
    public Soldier[] soldiers = new Soldier[MAX_PEOPLE];
    public int num_of_people = 0;

    //파일에서 입력받아 데이터를 저장하게 해야 함. 수정 필요
    PersonData() throws IOException {
        File inputFile = new File("Data.txt");
        if(!inputFile.exists()) inputFile.createNewFile();
        Scanner input = new Scanner(inputFile);

        //first line of Data.txt is comment
        if(input.hasNextLine()) input.nextLine();

        System.out.println(input.hasNextLine());
        while(input.hasNextLine()){
            String Line = input.nextLine();
            String[] str = Line.split("[ ]");

            //월에 -1 해 주어야 함
            soldiers[num_of_people] = new Soldier(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Soldier.toSoldierType(str[4]));
            num_of_people++;
        }

        //이후 데이터를 추가함
    }

    public int SearchName(String name){

        /*debug
        System.out.println(num_of_people);
        for(int i = 0; i < num_of_people; i++){
            System.out.println(soldiers[i].getName() + " " + soldiers[i].StartDayToString());
        }*/

        for(int i = 0; i < num_of_people; i++){
            if(this.soldiers[i].getName().equalsIgnoreCase(name)) {
                System.out.println("search index :" + i);
                return i;
            }
        }
        System.out.println("search index : -1 : cannot find");
        return -1;
    }

}
