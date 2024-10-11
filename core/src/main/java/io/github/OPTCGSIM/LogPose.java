package io.github.OPTCGSIM;

//Main networking component of the sim. All networking variables and methods are found here

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.*;

//peer 2 peer server functions
public class LogPose {
    //we can declare all connection variables here and use them elsewhere if needed
    public static Protocol lp_protocol;
    public static int lp_port, lp_connect;
    public static ServerSocket lp_server;
    public static Socket lp_socket;
    public static ServerSocketHints lp_s_hints;
    public static SocketHints lp_c_hints;
    public static String lp_host;

    //what functions do we need

    //start server
    //this is how you would start the server on your end
    //server = LogPose.startMatch(protocol,port,s_hints);
    public static ServerSocket startMatch(Protocol protocol, int port, ServerSocketHints hints){
        ServerSocket server = Gdx.net.newServerSocket(protocol, port, hints);
        return server;
    }
    //start client
    public static Socket findMatch(Protocol protocol, String host, int port, SocketHints hints){
        Socket socket = Gdx.net.newClientSocket(protocol, host, port, hints);
        return socket;
    }
    //seek available servers
    public static int joinMatch(){
        return 1;
    }
    //establish connection
    //send turn moves
    //receive turn moves (will need to parse JSON...)
        //differentiating the type of turn should happen within the send/receive turn methods
        //attack turn
        //defend turn
    //close connection?
    public int endMatch(ServerSocket server){
        server.dispose();
        return 0;
    }
}
