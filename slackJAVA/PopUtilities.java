import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopUtilities {
	private static  final int BC=0, AB=1,SK=2,MN=3, ON=4, QE=5, NS=6, PE=7, NB=8, NF=9;
	private static String[] fileNames= { "BC.csv", "AB.csv", "SK.csv", "Man.csv", "Ont.csv",
			"QE.csv", "NS.csv", "PEI.csv", "NB.csv", "NF.csv"};
	public  static ArrayList<PopCenter> bcList=new ArrayList<>(), abList= new ArrayList<>(),
			skList=new ArrayList<>(), mnList=new ArrayList<>(), onList=new ArrayList<>(),
			qeList=new ArrayList<>(), nsList=new ArrayList<>(), peList=new ArrayList<>(),
			nbList=new ArrayList<>(), nfList=new ArrayList<>();
	public   static ArrayList<ArrayList<PopCenter>> fullList;
	
	public static void loadAll()
	{
		fullList = new ArrayList<ArrayList<PopCenter>>();
		fullList.add(bcList);
		fullList.add(abList);
		fullList.add(skList);
		fullList.add(mnList);
		fullList.add(onList);
		fullList.add(qeList);
		fullList.add(nsList);
		fullList.add(peList);
		fullList.add(nfList);
		for (int i=0; i<fullList.size(); i++)
		{
			fullList.set(i, PopCenter.loadPopCenters(fileNames[i]));
		}
	}
	
	
	public static void q1()
	{
		abList.stream().sorted(Comparator.comparing(PopCenter::getName)).map(x->x.getName()).forEach(System.out::println);
	}
	
	public static void q2()
	{
		skList.stream().filter(x->x.getLatest()>5000).forEach(System.out::println);
	}
	
	public static int q3()
	{
		return peList.stream().map(x->x.getEarliest()).reduce(0,(x,y)->x+y);
	}
	
	public static int q4()
	{
		return skList.stream().map(x->x.getLatest()-x.getEarliest()).reduce(0,(x,y)->x+y);
	}
	
	public  static PopCenter q5()
	{
		return abList.stream().sorted((x,y)->(y.getLatest()-y.getEarliest())-(x.getLatest()-x.getEarliest()))
				.limit(1).findFirst().get();
	}
	public  static PopCenter[] q6()
	{
		return fullList.stream().flatMap(x->x.stream()).sorted((x,y)->(y.getLatest()-y.getEarliest())-(x.getLatest()-x.getEarliest()))
				.limit(5).toArray(PopCenter[]::new);
	}
	
	public static ArrayList<PopCenter> q7()
	{
		return Arrays.asList(skList, abList).stream().flatMap(x->x.stream())
				.filter(x->x.getLatest()>=10000 && x.getLatest()<=40000)
				//.sorted(Comparator.comparing(PopCenter::getName))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<PopCenter> q8()
	{
		return Arrays.asList(onList, qeList).stream().flatMap(x->x.stream())
				.filter(x->x.getGroup().equals("Small"))
				.sorted(Comparator.comparing(PopCenter::getName))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static void q9()
	{
		onList.stream().map(a->{
			int latest = a.getLatest();
			if(a.getGroup().equals("Small")) latest*=1.1;
			else if(a.getGroup().equals("Medium")) latest*=1.05;
			else latest*=1.02;
			return new PopCenter(a.getName(),a.getGroup(),a.getEarliest(),latest);
		}).forEach(System.out::println);
	}
	//Write a stream ops to Print out the avg population change for each size group across all provinces.
	
public static void q10()
		{
			List<List<PopCenter>>list3 = new ArrayList<>();
			list3.add(PopUtilities.abList);
			list3.add(PopUtilities.bcList);
			list3.add(PopUtilities.mnList);
			list3.add(PopUtilities.nbList);
			list3.add(PopUtilities.nfList);
			list3.add(PopUtilities.nsList);
			list3.add(PopUtilities.onList);
			list3.add(PopUtilities.peList);
			list3.add(PopUtilities.qeList);
			list3.add(PopUtilities.skList);
			
			list3.stream().flatMap(x->x.stream()).collect(Collectors.groupingBy(PopCenter::getsSizeGroup)).entrySet().forEach(g->System.out.println(g.getValue().stream().collect(Collectors.averagingInt(x->x.getnLatestPop()-x.getnEarlierPop()))));
			
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadAll();
		q10();
	}

}
