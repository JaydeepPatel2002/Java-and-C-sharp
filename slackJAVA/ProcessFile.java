import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProcessFile {
	
	public static String processFile(BufferedReaderProcessor p) throws FileNotFoundException, IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader("AB.csv")))
		{
			return p.process(br);
		}
	}
	
	public static ArrayList<String> readAll() throws FileNotFoundException, IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader("AB.csv")))
		{
			return (ArrayList<String>) br.lines().collect(Collectors.toList());
		}
	}
	
	public static <T> void forEach(List<T> list, Consumer<T> c)
	{
		for(T o:list)
		{
			c.accept(o);
		}
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p)
	{
		List<T> rs = new ArrayList<T>();
		forEach(list, e->{if(p.test(e)) rs.add(e);});
		return rs;
	}
	
	public static <T,R> List<R> map(List<T> list, Function<T,R> f)
	{
		List<R> rs = new ArrayList<>();
		list.forEach(t->{rs.add(f.apply(t));});
		return rs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = null;
		try {
			list = readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(filter(list,s->s.indexOf("Small")<0 && s.indexOf("Calgary")>=0));
		//System.out.println(map(list,s->s.length()));
		//System.out.println(map(list,s->s.toUpperCase()));
		//forEach(map(filter(list,s->s.indexOf("Small")>=0),s->s.replaceAll(",", " ")),s->{System.out.printf("%100s\n", s);});
		List<String[]> rs= map(list,s->s.split(","));
		System.out.print(Arrays.deepToString(rs.toArray()));

	}

}
