package upm.etsisi.View;

import upm.etsisi.Model.Client;
import upm.etsisi.Model.IClientCompany;
import upm.etsisi.Model.ClientCompany;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ViewClient implements View{
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printClient(Client client){
        if(client != null){
            messageOutput("USER{identifier='" + client.getDNI()+ "’, name='" + client.getName()+
                    "', email='" + client.getEmail()+ "', cash=" + client.getCashierId() + "}");
        }

    }
    public void printCompany(ClientCompany client){
        if(client != null){
            messageOutput("COMPANY{identifier='" + client.getNIF()+ "’, name='" + client.getName()+
                    "', email='" + client.getEmail()+ "', cash=" + client.getCashierId() + "}");
        }

    }

    public void printAll(Collection<IClientCompany> list) {
        for (IClientCompany cc : list) {
            if(cc instanceof  Client){
                Client client = (Client) cc;
                printClient(client);
            }else{
                ClientCompany company = (ClientCompany) cc;
                printCompany(company);
            }
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
