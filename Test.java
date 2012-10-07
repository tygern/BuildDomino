import javax.swing.JFrame;

class Test {
    public static void main(String[] args) {
	Domino d1 = new Domino(1);
	Domino d2 = new Domino(2);
	Domino d3 = new Domino(-3);
	Tableau t1 = new Tableau(3);

	t1.addDomino(d2);
	t1.addDomino(d3);
	t1.addDomino(d1);

	t1.printAll();


	int[] w = new int[4];
	w[0] = 1;
	w[1] = -4;
	w[2] = 3;
	w[3] = -2;
	Element elmW = new Element(w);
	Tableau t2 = new Tableau(elmW);

	t2.printAll();
    }
}