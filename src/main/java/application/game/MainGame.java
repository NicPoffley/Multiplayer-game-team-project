package application.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import application.networking.ClientLogic;
import application.networking.NetworkClient;
import application.networking.Server;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class MainGame extends Application {
    //add:
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String players;
    //previous:
    private final static ArrayList<String> input = new ArrayList<>();
    private Renderer renderer;
    private static Client client;
    private static Client clients;


    @Override
    public void start(Stage primaryStage) {
           /* before edit
            initGame(primaryStage);
            */
        //add:
        System.out.println("How many players");

        try {
            players = br.readLine();
        }catch (IOException e1) {
            System.out.println("Buffered reader does not exist");
        }

        if (players.equals("one")){
            initGame(primaryStage);
        }
        else if(players.equals("more")){
            NetworkClient networkClient = new NetworkClient();
            initMultiplayerGame(primaryStage);
            System.out.println(".............................................................................................................................................................................................");
        }
        else{
            System.out.println("please enter one/more"+ players );
        }

    }

    public static void main(String[] args)  {
        launch(args);
    }


    public void initGame(Stage stage){
        System.out.println("only one player");
        client = new Client("Body.png");
        renderer = new Renderer();

        renderer.setHeight(720);
        renderer.setWidth(60*120);

        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);

        stage.setScene(scene);
        stage.show();

        Image clientImg = new Image("Body.png");

        checkForInput(scene);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameLoop(clientImg);
            }
        }.start();

    }


    private void checkForInput(Scene scene){

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
    }




    public void gameLoop(Image clientImg){
        System.out.println("sd");
        System.out.println();
        moveClient();
        client.displaySprite(renderer, clientImg, client.getClientSprite());

    }

    private void moveClient(){
        if(client.isLive()) {
            if (input.contains("UP")) {
                client.jump();
            }

            if (input.contains("LEFT")) {
                client.moveX(-5);
            }

            if (input.contains("RIGHT")) {
                client.moveX(5);
            }
            if (input.contains("DOWN")) {
                client.moveY(5);
            }

            //if (client.getVelocity().getY() < 10) {
            //    client.setVelocity(client.getVelocity().add(0,1));
           // }

            //client.moveY((int)client.getVelocity().getY());
            // send location to server

        }
    }

    //add:

    public void initMultiplayerGame(Stage stage){

        System.out.println("two players");
        client = new Client("Body.png");
        clients = new Client("Body2.png");

        renderer = new Renderer();

        renderer.setHeight(720);
        renderer.setWidth(60*120);

        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);

        stage.setScene(scene);
        stage.show();

        Image clientImg = new Image("Body.png");
        Image clientsImg = new Image("Body2.png");

        checkForInput(scene);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                //gameLoop(clientImg);
                gameLoopForMultiplayer(clientImg, clientsImg);

            }
        }.start();

    }

/*
    private void checkForInput(Scene scene){

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
    }

 */




    public void gameLoopForMultiplayer(Image clientImg, Image clientsImg){

        System.out.println("sd");
        System.out.println(input);
        moveClient();

        //moveClients();
        client.displaySprite(renderer, clientImg, client.getClientSprite());
        //clients.getClientSprite().setPosition(ClientLogic.other[1], ClientLogic.other[2]);
        clients.displaySprite(renderer, clientsImg, clients.getClientSprite());
        ClientLogic.output.println(ClientLogic.clientname+ " " + client.getClientSprite().getPositionY() + " " + client.getClientSprite().getPositionY());
    }

    private void moveClients(ArrayList<String> movingMessage){
        if(clients.isLive()) {
            if (movingMessage.contains("UP")) {
                clients.jump();
            }

            if (movingMessage.contains("LEFT")) {
                clients.moveX(-5);
            }

            if (movingMessage.contains("RIGHT")) {
                clients.moveX(5);
            }

            if (client.getVelocity().getY() < 10) {
                clients.setVelocity(clients.getVelocity().add(0,1));
            }

            clients.moveY((int)clients.getVelocity().getY());

        }
    }




    public static int getClientID(){
        return client.getID();
    }

    public static int getClientsID(){
        return clients.getID();
    }


}

