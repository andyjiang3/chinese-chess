package GameLogic;

public class tester {
    public static void main(String[] args) {
        Board gBoard1 = new Board();
        System.out.println("Before Move:");
        System.out.println(gBoard1.getPoint(0, 0).getPiece());
        System.out.println(gBoard1.getPoint(0, 1).getPiece());

        gBoard1.doMove( new Move(0, 0, 0, 1));


        System.out.println("\nAfter Move:");
        System.out.println(gBoard1.getPoint(0, 0).getPiece());
        System.out.println(gBoard1.getPoint(0, 1).getPiece());


    }
}
