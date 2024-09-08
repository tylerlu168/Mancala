public class Driver
{
    public static void main(String[] args)
    {
        int[] test = {3, 0, 9, 0, 2, 0, 10, 8, 4, 5, 13, 0, 1, 7};
        Mancala game = new Mancala(test);
        System.out.println(game);
        System.out.println(game.move(0));
        System.out.println(game);
        //System.out.println(game.getSeeds(1));
        //System.out.println(game.gameOver());
        //System.out.println(game.getWinner());
    }
}
