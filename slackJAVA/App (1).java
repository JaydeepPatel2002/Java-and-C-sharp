import java.util.*;
import java.util.function.*;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	//List is weight, Return Apple list
	public static <T,E> List<T> map(List<E> list, Function<E,T> f)
	{
		List<T> rs = new ArrayList<>();
		for(E e:list)
		{
			rs.add(f.apply(e));
		}
		return rs;
	}

	//List is apple list to be processed 
	//Params is list of color to be applied 
	//nth apple will be applied to the nth color per line 29
	public static <T,E,I> List<T> map(List<E> list, List<I> params,BiFunction<E,I,T> f)
	{
		List<T> rs = new ArrayList<>();
		for(E e:list)
		{
			I param = params.get(list.indexOf(e));
			rs.add(f.apply(e,param));
		}
		return rs;
	}
	
	//List is apple list to be processed 
		//Params is list of color to be applied 
		//Which color an apple will be applied is decide by indexer
		
	public static <T,E,I> List<T> map(List<E> list, List<I> params,BiFunction<E,I,T> f, Function<E,Integer> indexer)
	{
		List<T> rs = new ArrayList<>();
		for(E e:list)
		{
			I param = params.get(indexer.apply(e));
			rs.add(f.apply(e,param));
		}
		return rs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> weights  = Arrays.asList(1,2,3,4,5,6);
		List<Apple> apples = map(weights,Apple::new);
		List<Grape> grapes = map(weights,Grape::new);
		//Fruit.print(apples);
		//Fruit.print(grapes);
		
		List<String> colors = Arrays.asList("red","green","white","red","green","yellow");
		apples = map(apples,colors,Apple::setColor);
		//Fruit.print(apples);
		grapes = map(grapes,colors,Grape::setColor);
		//Fruit.print(grapes);
		
		apples = map(apples,colors,Apple::setColor,(e)->(int)(Math.random()*5));
		//Fruit.print(apples);
		grapes = map(grapes,colors,Grape::setColor,(e)->5);
		//Fruit.print(grapes);
		
		List<String> countries = Arrays.asList("US","Canada","Mexico");
		apples = map(apples,countries,Apple::setCountry,(e)->(int)(Math.random()*3));
		//Fruit.print(apples);
		grapes = map(grapes,countries,Grape::setCountry,(e)->(int)(Math.random()*3));
		//Fruit.print(grapes);
		
		//We have two list apples and grapes
		
		//1 implement a new comparator class
		apples.sort(new AppleComparator());
		
		//2 Anonymous class
		apples.sort(
				new Comparator<Apple>()
				{
					public int compare(Apple a1,Apple a2)
					{
						return a1.getColor().compareTo(a2.getColor());
					}
				}
				);
		
		//3 Lambda
		//apples.sort((Apple a1,Apple a2)->a1.getCountry().compareTo(a2.getCountry()));
		apples.sort((a1,a2)->a1.getCountry().compareTo(a2.getCountry()));
		apples.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
		apples.sort((a1,a2)->a1.getWeight()-a2.getWeight());
		//3.5
		//Comparator<Apple> c = Comparator.comparing(a -> a.getWeight());
		//apples.sort(c);
		apples.sort(Comparator.comparing(a -> a.getWeight()));
		//4 Method reference
		apples.sort(Comparator.comparing(Apple::getWeight));
		apples.sort(Comparator.comparing(Apple::getColor));
		apples.sort(Comparator.comparing(Apple::getCountry));
		
		apples.sort(Comparator.comparing(Apple::getWeight).reversed());
		
		apples.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor).reversed());
		
		Predicate<Apple> RedApple = a->a.getColor().compareTo("red")==0;
		Predicate<Apple> notRedApple = RedApple.negate();
		//Predicate<Apple> RedAndHeavyApple = a->a.getColor().compareTo("red")==0 && a.getWeight()>150;
		Predicate<Apple> HeavyApple =  a->a.getWeight()>150;
		Predicate<Apple> RedAndHeavyApple = RedApple.and(HeavyApple);
		Predicate<Apple> GreenApple = a->a.getColor().compareTo("green")==0;
		Predicate<Apple> RedNotHeavyAppleorGreenApple = GreenApple.or(RedApple).and(HeavyApple.negate()); 		
		//Fruit.print(apples);
		
		Function<Integer, Integer> f = x->x+1;
		Function<Integer, Integer> g = x->x*2;
		//Function<Integer, Integer> h = f.andThen(g);//4
		Function<Integer, Integer> h = f.compose(g);//3
		//System.out.print(h.apply(1));
		
		var wrapper = new Object(){int counter=0;};
		apples.forEach(a->wrapper.counter++);
		System.out.print(wrapper.counter);
		
		
		
	}

}
