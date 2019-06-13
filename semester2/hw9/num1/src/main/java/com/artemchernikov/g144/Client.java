package com.artemchernikov.g144;

import javafx.stage.Stage;

/**A class describing client side in connection*/
public class Client extends TicTacToeConnection {

    private String ip;
    private int port;

    public Client(String ip, int port, Stage stage) {
        super(stage);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }

}

