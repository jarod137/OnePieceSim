package io.github.OPTCGSIM;

//Main networking component of the sim. All networking variables and methods are found here

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.*;
import com.badlogic.gdx.utils.*;


import java.net.http.WebSocket;

// current socketio version: "io.socket:socket.io-client:2.1.1"
// import io.socket.client.IO; 
// import io.socket.client.Socket;

//peer 2 peer server functions
public class LogPose{
    //we can declare all connection variables here and use them elsewhere if needed
    public static Protocol tcp = Protocol.TCP;
    public static int lp_serverport = 3000, lp_clientport = 3002, lp_connect;
    public static ServerSocket lp_server;
    public static Socket lp_socket;
    public static ServerSocketHints lp_s_hints;
    public static SocketHints lp_c_hints;
    public static String lp_host;
    //json object to send back and forth?
    public static Json turnJSON = new Json();

    // Socket socket = IO.socket();
    //what functions do we need

    // WebSocket socket = WebSockets.newSocket(WebSockets.toWebSocketUrl(address, port));
    // socket.setSendGracefully(true);
    // socket.addListener(new WebsocketListener() { ... });
    // socket.connect();

    //start server
    //protocol and socket hints are preset
    //this is how you would start the server on your end
    //ServerSocket server = LogPose.startMatch(port);
    public static ServerSocket startServer(int port){
        ServerSocket server = Gdx.net.newServerSocket(tcp, port, lp_s_hints);
        // server.accept(lp_c_hints);
        return server;
    }
    //start client
    //must pass a valid hostname and port number
    //we may need to give the player the option to set the hostname
    public static Socket startClient(String host, int port){
        Socket socket = Gdx.net.newClientSocket(tcp, host, port, lp_c_hints);
        return socket;
    }

    //seek available servers (this should be done by the server i think...)
    //uses http requests when I think we should be using websockets...
    public static int pingServer(){
        //ping them
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        HttpResponseListener receiver = new Receiver();
        HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://localhost:1072/luffy").build();
        Gdx.net.sendHttpRequest(httpRequest, receiver);
        return 0;
    }
    //establish connection


    //send turn moves
    public static int sendTurnMove(int port, Json json){

        return 1;
    }
    //receive turn moves (will need to parse JSON...)
        //differentiating the type of turn should happen within the send/receive turn methods
        //attack turn
        //defend turn

    //close connection?
    public int closeServer(ServerSocket server){
        server.dispose();
        return 0;
    }
    // public int closeClient(Socket client){
    //     client.dispose();
    //     return 0;
    // }
}
