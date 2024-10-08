package io.github.OPTCGSIM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import java.net.*;

//peer 2 peer server functions
public class LogPose {
    //what functions do we need
    //start server
    public int findClient(){
        ServerSocket server = Gdx.net.newServerSocket(Protocol, port, ServerSocketHints);

        return 1;
    }
    //start client
    //seek available servers
    //establish connection
    //send turn moves
    //receive turn moves
    //attack turn
    //defend turn
    //close connection
}
