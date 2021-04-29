package gameServer;

import java.util.Random;

public class GameLogic {
    public static String checkCommand(String command) {
        String response;
        Random rng = new Random();
        Integer computerResult = rng.nextInt(3);
        //command 0 -камень, 1- ножницы, 2 - бумага.
        //response 0-поражение, 1-ничья, 2 - победа
        switch (command.toLowerCase()) {
            case "0":
                if (computerResult.equals(0)) {
                    response = "1";
                } else if (computerResult.equals(1)) {
                    response = "2";
                } else {
                    response = "0";
                }
                break;

            case "1":
                if (computerResult.equals(0)) {
                    response = "0";
                } else if (computerResult.equals(1)) {
                    response = "1";
                } else {
                    response = "2";
                }
                break;

            case "2":
                if (computerResult.equals(0)) {
                    response = "2";
                } else if (computerResult.equals(1)) {
                    response = "0";
                } else {
                    response = "1";
                }
                break;

            default:
                response = "ping";
                break;

        }
        return response;
    }
}
