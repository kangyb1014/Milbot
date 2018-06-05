package com.clearT;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.io.IOException;

public class MilBot extends ListenerAdapter {

    public static JDA jda;
    private static PersonData data;

    public static void main(String[] args) throws Exception{
        try {
            data = new PersonData();
        } catch (IOException e) {
            System.out.println("file open error");
            e.printStackTrace();
        }

        JDABuilder jb = new JDABuilder(AccountType.BOT);
        jb.setAutoReconnect(true);
        //jb.setStatus(OnlineStatus.ONLINE);
        //jb.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jb.setToken(Ref.token);
        jb.addEventListener(new MilBot());

        try{
            jda = jb.buildBlocking();
        } catch(LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {

        //Objects
        User objUser = evt.getAuthor();
        MessageChannel objMsgCh = evt.getChannel();
        Message objMsg = evt.getMessage();

        if (objUser.isBot()) return;
        if (objMsg.getContentRaw().charAt(0) != '!' || objMsg.getContentRaw().length() < 2) return;

        // Commands

        String msg = objMsg.getContentRaw().substring(1);
        System.out.println(msg);

        if (msg.equalsIgnoreCase("help")) {
            SendHelp(objMsgCh);
            return;
        }

        if(msg.equalsIgnoreCase("제작자")){
            SendCreator(objMsgCh);
            return;
        }

        int index = data.SearchName(msg);
        if (index >= 0) {
            SendEndDay(objMsgCh, index);
        }

    }

    public void SendEndDay(MessageChannel ch, int index){
        Soldier sol = data.soldiers[index];
        ch.sendMessage(
                "이름: " + sol.getName()
                        + "\n복무 유형: " + sol.soldierTypeToString()
                        + "\n총 복무기간: " + sol.getWorkMonth() + "개월"
                        + "\n입대일: " + sol.StartDayToString()
                        + "\n전역일: " + sol.EndDayToString()
        ).queue();
    }

    public void SendHelp(MessageChannel ch){
        ch.sendMessage("군대 전역일자 계산 봇입니다. 데이터 추가나 변경은 제작자에게 문의하세요.").queue();
        ch.sendMessage("사용법:\n").queue();
        ch.sendMessage("!사람이름 : 입대 날짜, 복무 유형, 복무기간, 전역 날짜를 알려줍니다\n" +
                "복무기간 단축으로 인해 시간이 지나면 날짜가 맞지 않을 수도 있습니다\n" +
                "!help: 본 도움말을 출력합니다.\n" +
                "!제작자: 제작자를 알려줍니다. 현재 버전은 2018.6.5 버전입니다.").queue();

    }

    public void SendCreator(MessageChannel ch){
        ch.sendMessage("제작자: KANGYB").queue();
    }
}
