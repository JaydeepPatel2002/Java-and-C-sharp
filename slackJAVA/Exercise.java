import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercise
{

	public static void main(String[] args)
	{
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");

		List<Transaction> transactions = Arrays.asList(
		    new Transaction(brian, 2011, 300),
		    new Transaction(raoul, 2012, 1000),
		    new Transaction(raoul, 2011, 400),
		    new Transaction(mario, 2012, 710),
		    new Transaction(mario, 2012, 700),
		    new Transaction(alan, 2012, 950)
		);

		List<Transaction> tr2011 = transactions.stream()
									.filter(t->t.getYear()==2011)
									.sorted(Comparator.comparing(Transaction::getValue))
									.collect(Collectors.toList());
		List<String> cities = transactions.stream()
								.map(t->t.getTrader().getCity())
								.distinct()
								.collect(Collectors.toList());
		List<Trader> traders = transactions.stream()
								.map(Transaction::getTrader)
								.filter(t->t.getCity().equals("Cambridge"))
								.distinct()
								.sorted(Comparator.comparing(Trader::getName))
								.collect(Collectors.toList());
		String traderstr = transactions.stream()
							.map(t->t.getTrader().getName())
							.distinct()
							.sorted()
							.reduce("", (s1,s2)->s1+s2);
		boolean milanBased = transactions.stream().anyMatch(t->t.getTrader().getCity().equals("Milan"));
		
		transactions.stream()
					.filter(t->t.getTrader().getCity().equals("Cambridge"))
					.map(t->t.getValue())
					.forEach(System.out::println);
		
		Optional<Integer> highestValue = transactions.stream()
													.map(t->t.getValue())
													.reduce(Integer::max);
		
		Optional<Transaction> smallest = transactions.stream()
										.reduce((t1,t2)->t1.getValue()<t2.getValue()? t1:t2);
		
		smallest = transactions.stream().min(Comparator.comparing(Transaction::getValue));		
	}

}
