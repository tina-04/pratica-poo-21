package upm.etsisi.View;

import upm.etsisi.Model.Client;

import java.util.List;

public class ViewClient implements View{
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printInfo(Client client){
        messageOutput("User:" + client.getDNI() + ",  Name:" + client.getName() + ", Email:" + client.getEmail());

    }
    public void listClient(List<Client> clientList){
        for(Client c : clientList){
           printInfo(c);
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
