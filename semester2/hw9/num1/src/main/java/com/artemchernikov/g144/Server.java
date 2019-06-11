package com.artemchernikov.g144;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Server extends Game {

    private InputStream inputStream;
    private OutputStream outputStream;

    public Server(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
    }

}
