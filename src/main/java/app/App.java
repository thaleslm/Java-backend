package app;

import app.controller.WebSocketServer;
import app.data.ConnectionMongo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebSocketServer server = new WebSocketServer();
        ConnectionMongo connectionMongo = ConnectionMongo.getInstance();

        /*Starting Server Socket */
        server.Initial();

        /*Starting comnunication with MongoDB*/
        connectionMongo.startConnectionWithMongo();

    }
}
