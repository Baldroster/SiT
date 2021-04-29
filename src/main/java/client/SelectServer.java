package client;

import utils.ServerDTO;

import java.util.List;
import java.util.Scanner;

public class SelectServer {
    public static ServerDTO selectServer(){
        while (true){
            List<ServerDTO> servers = UdpRequests.getServers();
            System.out.println("Выберите сервер");
            System.out.println("0: обновить список серверов");
            for (int i = 0; i < servers.size(); i++) {
                System.out.println(i+1+": "+servers.get(i).getIp()+":"+servers.get(i).getPort()
                        +" load "+servers.get(i).getLoad()+"/1");
            }

            Scanner scanner = new Scanner(System.in);
            String request = scanner.nextLine();
            try{
             int server =  Integer.parseInt(request);
             if(server==0){
                 continue;
             }else if(server>servers.size()){
                 System.out.println("Вы ввели неверное значение");
             }else{
                 servers = UdpRequests.getServers();
                 if(servers.get(server-1).getLoad()==1){
                     System.out.println("Сервер занят");
                     }else return servers.get(server-1);
                 }
            }catch (NumberFormatException e){
                System.out.println("Вы ввели неверное значение");
            }

        }
    }
}
