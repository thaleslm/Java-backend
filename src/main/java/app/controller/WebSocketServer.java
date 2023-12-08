package app.controller;
import java.util.List;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.*;

import app.data.ConnectionMongo;
import app.services.SearchDataDynamic;


public class WebSocketServer {
        private SearchDataDynamic serviceSearchDataDynamic = SearchDataDynamic.getInstance();
    public void Initial() {
        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        config.setPort(9092); 
        
        
        
        // Escolha a porta que deseja usar
        // config.setAuthorizationListener(new AuthorizationListener() {
        //     @Override
        //     public boolean isAuthorized(HandshakeData data) {
        //         return true; // Permitir todas as solicitações por enquanto
        //     }
        // });
    
        reusePort(config);
        final SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Cliente conectado: " + client.getSessionId());
            }
        });

        
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Cliente desconectado: " + client.getSessionId());
            }
        });
        
        server.start();

        server.addEventListener("998/0",InformationSource.class, new DataListener<InformationSource>() {
            @Override
            public void onData(SocketIOClient client, InformationSource informationSource, AckRequest ackRequest) {
                
                // InformationSource information = new InformationSource("cadastro_ativos", true, "todos");
                MessageReturn result = serviceSearchDataDynamic.queryStringMongo(informationSource);
                ackRequest.sendAckData(result);

            }
        });

        

        // Mantenha o servidor em execução
        // Thread.sleep(Integer.MAX_VALUE);

        // server.stop();
       
        };
    
      
        public void reusePort(Configuration config) {
            SocketConfig sockConfig = new SocketConfig();
            sockConfig.setReuseAddress(true);
            config.setSocketConfig(sockConfig);
        }
       

}   

