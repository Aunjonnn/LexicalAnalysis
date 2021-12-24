package lab1;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class lexicalAnalyzer {

	String[] keywords= {"if", "else", "int", "float", "double", "or", "try", "catch", "while", "for", "abstract", "assert", "boolean", "break", "byte", "case", "char", "class", "const", "continue", "default", "double", "do", "enum", "extends", "false", "final", "finally", "for", "goto", "implements", "import", "instanceof", "interface", "long", "native", "new", "null", "package",	"private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "void", "volatile"};
	String[] others= {",", ";", "(", ")", "{", "}", "[", "]"};
	String[] mathops= {"+", "-", "*", "/", "="};
	String[] logicalops= {">", "<"};
	String[] numericalvalues= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
	
	String kw="Keywords: ";
	String mo="Math Operators: ";
	String lo="Logical Operators: ";
	String nv="Numerical Values: "; 
	String id="";
	String s="Identifiers: ";
	String oth="Others: ";
	
		
	public String[] split(String sp, String del)
	{
		String[] s=sp.split(del);
		return s;
	}
	
	
	public void detectivetask(String t)
	{
		if ((!Arrays.asList(numericalvalues).contains(t)) 
				&& (!Arrays.asList(others).contains(t)) 
				&& (!Arrays.asList(logicalops).contains(t)) 
				&& (!Arrays.asList(mathops).contains(t))
				&&(!Arrays.asList(keywords).contains(t))
				&&(!id.contains(t)))
		{
			identifiers(t);
		}
		if((Arrays.asList(mathops).contains(t))&&(!mo.contains(t)))
		{
			mathoperators(t);
		}
		
		if((Arrays.asList(logicalops).contains(t))&&(!lo.contains(t)))
		{
			logicaloperators(t);
		}
		
		if((Arrays.asList(others).contains(t))&&(!oth.contains(t)))
		{
			others(t);
		}
		
		if((Arrays.asList(numericalvalues).contains(t))&&(!nv.contains(t)))
		{
			numericalvalues(t);
		}
		
	}
	
	public void detective1(String s)
	{
		if((Arrays.asList(keywords).contains(s)))
		{
			if((!kw.contains(s)))
			{
				keywords(s);
			}
		}
		else
		{
			
			if(s.indexOf("(")>=0)
			{
				others("(");
				String[] sp=split(s, "\\(");
				if(Arrays.asList(keywords).contains(sp[0]))
				{
					keywords(sp[0]);
				}
				String[] dsp=new String[sp.length-1];
				for(int i=0; i<dsp.length; i++)
				{
					dsp[i]=sp[i+1];
				}
				detective2(dsp);
			}
			
			
		}
	}
		

	public void detective2(String[] s)
	{
		String[] spres;
		if(s[0].indexOf("(")>=0)
		{
			String[] sp=split(s[0], "\\(");
			detective1(sp[0]);
		}
		else
		{
			if(Arrays.asList(keywords).contains(s[0]))
			{
				detective1(s[0]);
				for(int i=1; i<s.length; i++)
				{
					spres=split(s[i], "");
					for(int j=0; j<spres.length; j++)
					{
						detectivetask(spres[j]);
					}
				}
			}
			else
			{
				for(int i=0; i<s.length; i++)
				{
					spres=split(s[i], "");
					for(int j=0; j<spres.length; j++)
					{
						detectivetask(spres[j]);
					}
				}
			}
		}
		
	}
	
	
	
	public void analyze() 
	{
		try
		{
			File fl=new File("input.txt");
			Scanner sc=new Scanner(fl);
			while(sc.hasNextLine())
			{
				String currentln=sc.nextLine();
				String num=null;
				String[] splitresult;
				
				//for non integer numbers
				if(currentln.contains(".")
						&&(Arrays.asList(numericalvalues).contains(Character.toString(currentln.charAt(currentln.indexOf(".")-1))))
						&&(Arrays.asList(numericalvalues).contains(Character.toString(currentln.charAt(currentln.indexOf(".")+1)))))
				{
					int index=currentln.indexOf(".");
					
					num=Character.toString(currentln.charAt(index-1));
					num=num+currentln.charAt(index);
					num=num+currentln.charAt(index+1);
					numericalvalues(num);
				}
				if(currentln.contains("\t"))
				{
					String[] linewithtab=split(currentln, "\t");
					currentln=linewithtab[1];
					
				}
				splitresult=split(currentln, " ");
				
			    //for keywords
				detective1(splitresult[0]);
				
				//for other stuffs
				detective2(splitresult);
			}
			sc.close();
		}
		
			catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
		
	public void keywords(String k)
	{
		if(kw=="Keywords: ")
		{
			kw=kw+k;
		}
		else
		{
			kw=kw+", "+k;
		}
		
	}
		
	public void others(String o)
	{
		if(oth=="Others: ")
		{
			oth=oth+o;
		}
		else
		{
			oth=oth+" "+o;
		}
	}
	
	public void mathoperators(String m)
	{
		if(mo=="Math Operators: ")
		{
			mo=mo+m;
		}
		else
		{
			mo=mo+" "+m;
		}
	}
	
	public void logicaloperators(String l)
	{
		if(lo=="Logical Operators: ")
		{
			lo=lo+l;
		}
		else
		{
			lo=lo+" "+l;
		}
	}
	
	public void numericalvalues(String n)
	{
		if(nv=="Numerical Values: ")
		{
			nv=nv+n;
		}
		else
		{
			nv=nv+", "+n;
		}
	}
	
	public void identifiers(String i)
	{
		if(id=="")
		{
			id=id+i;
		}
		else
		{
			id=id+", "+i;
		}
	}
	
}
