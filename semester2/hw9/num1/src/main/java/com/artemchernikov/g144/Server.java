package com.artemchernikov.g144;

import javafx.stage.Stage;

/**A class describing server side in connection*/
public class Server extends TicTacToeConnection {

    private int port;

    public Server(int port, Stage stage) {
        super(stage);
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return true;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }

}
