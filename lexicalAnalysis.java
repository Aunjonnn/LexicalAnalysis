package lab1;

public class lexicalAnalysis {

	public static void main(String[] args) {
		lexicalAnalyzer la=new lexicalAnalyzer();
		la.analyze();
		System.out.println(la.kw);
		System.out.println("Identifiers: "+la.id);
		System.out.println(la.mo);
		System.out.println(la.lo);
		System.out.println(la.nv);
		System.out.println(la.oth);


	}

}
