
import org.snmp4j.smi.OID;
import model.SnmpClient;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        SnmpClient client = new SnmpClient();
        client.start("udp:192.168.126.6/161");
        Scanner scan = new Scanner(System.in);
        String str = "";
        while(true) {
            str = scan.nextLine();
            System.out.println(client.getNextAsString(new OID(str)));
        }
    }
}
