package telran.employees;

import telran.io.*;
import telran.net.*;

public class Main {
    private static final String FILE_NAME = "employees.data";
        private static final int PORT = 4000;
        
            public static void main(String[] args) {
                Company company = new CompanyImpl();
                if (company instanceof Persistable persistable) {
                    persistable.restoreFromFile(FILE_NAME);
            }
            Protocol protocol = new CompanyProtocol(company);
            TCPServer server = new TCPServer(protocol, PORT);
            Thread threadServer = new Thread(server);
            threadServer.start();
            SaverToFile saver = new SaverToFile(company);
            saver.start();
    }
}