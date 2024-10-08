package io.github.OPTCGSIM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.*;

//peer 2 peer server functions
public class LogPose {
    //we can declare all connection variables here and use them elsewhere maybe
    public static Protocol protocol;
    public static int port, connect;
    public static ServerSocket server;
    Socket socket;
    ServerSocketHints s_hints;
    SocketHints c_hints;
    String host;
    //what functions do we need

    //start server
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
    //establish connection
    //send turn moves
    //receive turn moves
        //differentiating the type of turn should happen within the send/receive turn methods
        //attack turn
        //defend turn
    //close connection?
    public int endMatch(ServerSocket server){
        server.dispose();
        return 0;
    }
}
