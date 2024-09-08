public class Mancala
{
    private int[] board;
    private int p1Mancala;
    private int p2Mancala;

    public Mancala()
    {
        board = new int [14];

        for (int i = 0; i < board.length - 1; i++)
        {
            board[i] = 4;
        }
        p1Mancala = 6;
        p2Mancala = 13;
        board[p1Mancala] = 0;
    }
    /**
     * Returns the number of seeds on the specified
     * player's side of the board excluding their Mancala.
     *
     * Precondition: player == 1 || player == 2
     *
     *
     * @param player
     * @return
     */
    public int getSeeds(int player)
    {
        int seeds = 0;

        int beg = 0;
        int end = p1Mancala;

        if(player == 2)
        {
            beg = p1Mancala + 1;
            end = p2Mancala;
        }
        for (int i = beg; i < end; i++)
        {
            seeds += board[i];
        }
        return seeds;
    }
    public int[] getBoard()
    {
        return board;
    }

    /**
     * Performs a move action by removing all
     * the seeds from the specified pit and
     * distributing 1 seed in each subsequent
     * pit including the active player's Mancala
     * and enemy pits but excluding the enemy Mancala.
     *
     * Precondition: 0 <= pit < p2Mancala
     *               pit != p1Mancala
     *               board[pit] > 0
     *
     * @param pit
     * @return 1 if player 1 should got next,
     *         2 if player 2 should go next
     */
    public int move(int pit)
    {
        int activePlayer = 1;
        int activeMancala = p1Mancala;
        int enemyMancala = p2Mancala;

        if(pit > p1Mancala)
        {
            activePlayer = 2;
            activeMancala = p2Mancala;
            enemyMancala = p1Mancala;
        }

        int seeds = board[pit];
        board[pit] = 0;
        while(seeds > 0)
        {
            pit = (pit + 1) % board.length;
            if(pit != enemyMancala)
            {
                board[pit]++;
                seeds--;
            }

        }

        if(pit == activeMancala)
        {
            return activePlayer;
        }

        int opposite = board.length - 2 - pit;

        if(pit / (board.length / 2) + 1 == activePlayer && board[opposite] > 0)
        {
            board[activeMancala] += board[opposite] + board[pit];
            board[opposite] = 0;
            board[pit] = 0;
        }
        return 3 - activePlayer;
    }


    /**
     *
     * @return true if the game is over; false otherwise
     *          The game is over if one of the players
     *              does not have any seeds in their
     *              smaller pits.
     */
    public boolean gameOver()
    {
        return getSeeds(1) * getSeeds(2) != 0;
    }

    /**
     *
     * @return -1 if the game is not over
     *          0 if the game is a tie
     *          1 if player 1 is the winner
     *          2 if player 2 is the winner
     *
     *          The winner is th eplayer with the most seeds
     *          counting their smaller pits in addition to their
     *          Mancala.
     */
    public int getWinner()
    {
        if(!gameOver())
        {
            return -1;
        }

        int one = getSeeds(1) + board[p1Mancala];
        int two = getSeeds(2) + board[p2Mancala];
        if(one > two)
        {
            return 1;
        }
        else if(two >  one)
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }
    public String toString()
    {
        String rtn = "";

        for (int i = p2Mancala; i > p1Mancala; i--)
        {
            rtn += board[i] + " ";
        }
        rtn += "\n  ";
        for (int i = 0; i <= p1Mancala; i++)
        {
            rtn += board[i] + " ";
        }
        return rtn;
    }
    public Mancala(int[] board)
    {
        this.board = board;
        p1Mancala = 6;
        p2Mancala = 13;
    }
}
