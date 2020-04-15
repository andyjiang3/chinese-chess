package GameLogic;

public class tester {
    public static void main(String[] args) {
        Board gBoard1 = new Board();
        System.out.println("Before Move:");
        System.out.println(gBoard1.getPoint(4, 9).getPiece());
        System.out.println(gBoard1.getPoint(4, 8).getPiece());

        gBoard1.tryMove( new Move(4, 9, 4, 8));


        System.out.println("\nAfter Move:");
        System.out.println(gBoard1.getPoint(4, 9).getPiece());
        System.out.println(gBoard1.getPoint(4, 8).getPiece());

        System.out.println("\nBefore Move:");

        System.out.println(gBoard1.getPoint(1, 7).getPiece());
        System.out.println(gBoard1.getPoint(1, 0).getPiece());

        gBoard1.tryMove( new Move(1, 7, 1, 0));


        System.out.println("\nAfter Move:");
        System.out.println(gBoard1.getPoint(1, 7).getPiece());
        System.out.println(gBoard1.getPoint(1, 0).getPiece());

        System.out.println("\nBefore Move:");

        System.out.println(gBoard1.getPoint(4, 3).getPiece());
        System.out.println(gBoard1.getPoint(4, 4).getPiece());

        gBoard1.tryMove( new Move(4, 3, 4, 4));


        System.out.println("\nAfter Move:");
        System.out.println(gBoard1.getPoint(4, 3).getPiece());
        System.out.println(gBoard1.getPoint(4, 4).getPiece());


    }
}
