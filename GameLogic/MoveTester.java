package GameLogic;

import GameLogic.Pieces.Piece;

/**
 * The Move Tester has two options, a shallow check and a deep check. It will set it's own value that staes whether the given move is valid.
 * The shallow check only does static checking which is not dependent on rules like generals and checks
 * The deep check also checks for generals (which involves trying a move and undoing it for the following checks
 *
 * @author Venkat Pamulapati
 * @version 4/10/20
 */
public class MoveTester {
    private Board board;
    private Move move;


    private int obstacleCount; //number of pieces in the way
    private boolean isClear; //if obstacles = 0
    private boolean attack;
    private boolean legal;

    /**
     * This is the deep checker. Upon instantiation it will:
     * <ul>
     *     <li>Check Move patter validity</li>
     *     <li>Check if it's an attacking move or a movement move</li>
     *     <li>Count the number of obstackles and determine if it's a legal move (ie Chariot can attack by jumping over one obstacle</li>
     *     <li>Check if the generals are facing each other</li>
     * </ul>
     * <p>
     * It differs from the shallow check because it checks generals, something not required for delivering check.
     *
     * @param board the current board object
     * @param move  the move to be validated
     */
    MoveTester(Board board, Move move) {
        this.board = board;
        this.move = move;
        this.legal = true;

        //  1. first check if movement pattern is legal (ie horse moves 1 up 2 left)
        CheckPiece();
        Piece curr = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece();
        Piece captured = board.getPoint(move.getFinalX(), move.getFinalY()).getPiece();

        //  2. check if we are doing an attack, and also check if the end point is blocked by a friendly piece
        if (legal) {
            isAttack();
        }

        //  3. Check if the path is clear, if not See if we're an attacking cannon or a non attacking cannon that can't move
        if (legal) {
            obstacleStats();

            if (!isClear) {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (!(obstacleCount == 1 && attack)) {
                        legal = false;
                    }
                } else {
                    legal = false;
                }
            } else {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (attack) {
                        legal = false;
                    }
                }
            }
        }

        //###########postmove checking##################
        if (legal) {
            board.doMove(move);
            if (!approveGenerals()) {
                legal = false;
            }
            board.undoMove(move, captured);
            board.updateGenerals();
        }


    }

    /**
     * This is the shallow checker. Upon instantiation it will:
     * <ul>
     *     <li>Check Move patter validity</li>
     *     <li>Check if it's an attacking move or a movement move</li>
     *     <li>Count the number of obstackles and determine if it's a legal move (ie Chariot can attack by jumping over one obstacle</li>
     * </ul>
     * <p>
     * It differs from the deep check because it doesn't check generals, something not required for delivering check.
     *
     * @param board the current board object
     * @param move  the move to be validated
     * @param i     is just used to overload
     */
    MoveTester(Board board, Move move, int i) {
        this.board = board;
        this.move = move;
        this.legal = true;

        //  1. first check if movement pattern is legal (ie horse moves 1 up 2 left)
        CheckPiece();

        //  2. check if we are doing an attack, and also check if the end point is blocked by a friendly piece
        if (legal) {
            isAttack();
        }

        //  3. Check if the path is clear, if not See if we're an attacking cannon or a non attacking cannon that can't move

        if (legal) {
            obstacleStats();

            if (!isClear) {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (!(obstacleCount == 1 && attack)) {
                        legal = false;
                    }
                } else {
                    legal = false;
                }
            } else {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (attack) {
                        legal = false;
                    }
                }
            }
        }
    }


    /**
     * Returns that the generals aren't facing each other by counting the obstacles between them if they're in line.
     *
     * @return True if they are facing eachother (illegal)
     */
    private boolean approveGenerals() {

        //uses location to determine if generals are facing each other
        board.updateGenerals();

        if (board.getUpGeneralX() != board.getDownGeneralX()) {
            return true;
        } else {
            for (int i = board.getUpGeneralY() + 1; i < board.getDownGeneralY(); i++) {
                if (board.getPoint(board.getDownGeneralX(), i).getPiece() != null) {
                    obstacleCount++;
                }
            }

            // System.out.print(" Generals Exposed!");
            return obstacleCount != 0;
        }

    }


    /**
     * Checks if the move pattern is a valid move pattern and if there's a piece present.
     * If not, terminates the process
     */
    private void CheckPiece() {
        Piece temp = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece();

        if (temp == null) {
            this.legal = false;
        } else {
            temp.checkPattern(move);
            if (!move.isValid()) {
                this.legal = false;
            }
        }

    }

    /**
     * Checks the destination piece to see if we're attacking or self blocked
     */
    private void isAttack() {
        if (board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() == null) {
            attack = false;
        } else {
            Piece.Side origin = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().getSide();
            Piece.Side dest = board.getPoint(move.getFinalX(), move.getFinalY()).getPiece().getSide();
            if (origin != dest) {
                attack = true;
            }
            if (origin == dest) {
                this.attack = false;
                this.legal = false;
                //this.isClear = false; //shouldn't matter because illegal anyway now
            }
        }

    }

    /**
     * Finds out if there are obstacles, and if so, how many?
     * Useful for seeing if we have one obstacle for the cannon
     * Useful for seeing if a piece is blocked, handles knights as well.
     */
    private void obstacleStats() {

        isClear = true;
        this.obstacleCount = 0;

        //vertical move
        if (move.isVertical()) {
            if (move.getDy() > 0) {
                for (int y = move.getOriginY() + 1; y < move.getFinalY(); y++) {
                    if (board.getPoint(move.getOriginX(), y).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            } else if (move.getDy() < 0) {
                for (int y = move.getOriginY() - 1; y > move.getFinalY(); y--) {
                    if (board.getPoint(move.getOriginX(), y).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }


        }
        //horizontal move
        else if (move.isHorizontal()) {
            if (move.getDx() > 0) {
                for (int x = move.getOriginX() + 1; x < move.getFinalX(); x++) {
                    if (board.getPoint(x, move.getOriginY()).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            } else if (move.getDx() < 0) {
                for (int x = move.getOriginX() - 1; x > move.getFinalX(); x--) {
                    if (board.getPoint(x, move.getOriginY()).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //diagonal move
        }
        //diagonal move
        else if (move.isDiagonal()) {

            //left up
            if (move.getDx() < 0 && move.getDy() < 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() - x, move.getOriginY() - x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //left down
            else if (move.getDx() < 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() - x, move.getOriginY() + x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //right down
            else if (move.getDx() > 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() + x, move.getOriginY() + x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }

            //right up
            else {// (move.getDx() > 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() + x, move.getOriginY() - x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
        }
        //only for knights, as only knights have non linear moves. Knights are only blocked by the nearest pieces.
        else {

            if (move.getDx() == 2) {
                if (board.getPoint(move.getOriginX() + 1, move.getOriginY()).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDx() == -2) {
                if (board.getPoint(move.getOriginX() - 1, move.getOriginY()).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDy() == 2) {
                if (board.getPoint(move.getOriginX(), move.getOriginY() + 1).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDy() == -2) {
                if (board.getPoint(move.getOriginX(), move.getOriginY() - 1).getPiece() != null) {
                    obstacleCount++;
                }
            }


        }
        if (obstacleCount != 0) {
            isClear = false;
        }


    }

    public boolean isLegal() {
        return legal;
    }
}
