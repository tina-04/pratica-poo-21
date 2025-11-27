package upm.etsisi.View;

import upm.etsisi.Model.Client;

import java.util.Comparator;
import java.util.List;

public class ViewClient implements View{
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printClient(Client client){
        if(client != null){
            messageOutput("Client{identifier=‘" + client.getDNI()+ "’, name=''" + client.getName()+
                    "', email=‘" + client.getEmail()+ "', cash=" + client.getEmail() + "}");
        }

    }
    public void listClient(List<Client> clientList){
        clientList.sort(Comparator.comparing(Client::getName));
        for(Client c : clientList){
           printClient(c);
        }
    }
    public void createOK(){
        messageOutput("client add: ok");
    }
    public void removeOK(){
        messageOutput("client remove: ok");
    }
    public void listOK(){
        messageOutput("client list: ok");
    }




}
