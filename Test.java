import javax.swing.JFrame;

class Test {
    public static void main(String[] args) {
	Domino fakeDomino = new Domino(1,1,2,true);
	Tableau fakeTableau = new Tableau(2);
	
	System.out.println(fakeDomino.getFirstBlock().getXVal());
	System.out.println(fakeDomino.getFirstBlock().getYVal());

	Coordinate p1 = new Coordinate (1,2);
	Coordinate p2 = new Coordinate (1,3);
	System.out.println(p1.equals(p2));
	p2.setYVal(2);
	System.out.println(p1.equals(p2));

	fakeDomino.printInfo();
	p1.print();

	//	DrawDomino panel = new DrawDomino(fakeTableau);
	//        JFrame application = new JFrame();
	//        
	//        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//        application.add(panel);           
	//
	//        application.setSize(500, 400);
	//        application.setVisible(true);          
	
	int[] arrayW = new int[4]; //[1,-3,4,-2]
	arrayW[0] = 1;
	arrayW[1] = -3;
	arrayW[2] = 4;
	arrayW[3] = -2;
	Element elmtW = new Element(arrayW);
	
	System.out.println(elmtW.mapsTo(2));
	System.out.println(elmtW.getSign(2));
	System.out.println(elmtW.getSign(3));
	System.out.println(elmtW.getSign(4));

	Element invW = elmtW.findInverse();

	System.out.println("Inverses:");

	System.out.println(invW.mapsTo(1));
	System.out.println(invW.mapsTo(2));
	System.out.println(invW.mapsTo(3));
	System.out.println(invW.mapsTo(4));
     
	int[] arrayW2 = new int[4]; //[1,-3,3,-2]
	arrayW[0] = 1;
	arrayW[1] = -5;
	arrayW[2] = 4;
	arrayW[3] = -2;
	Element elmtW2 = new Element(arrayW2);

	int[] ok = new int[3];
	System.out.println(ok[2]);
    }
}