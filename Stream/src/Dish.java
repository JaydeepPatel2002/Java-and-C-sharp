import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Dish implements Comparable<Dish>{
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
		}
	
	public String getName() {
		return name;
	}
	
	public boolean isVegetarian() {
		return vegetarian;
	}
	
	public int getCalories() {
		return calories;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return name;
	}
	public enum Type { MEAT, FISH, OTHER }
	
	public static void main(String[] args) {
		
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		List<Dish> vegMenu = menu.stream()
								.filter(Dish::isVegetarian)
								.filter(d->d.getCalories()<140)
								.collect(Collectors.toList());
		
		//System.out.println(Arrays.deepToString(vegMenu.toArray()));
		
		List<Integer> nums = Arrays.asList(1,2,1,3,1,4,1,5,5);
		nums.stream()
				.filter(i->i%2==1)
				.distinct()
				.forEach(System.out::println);
		
		vegMenu = menu.stream()
				.filter(d->d.getCalories()>140)
				.skip(3)
				.limit(3)
				.collect(Collectors.toList());

		//System.out.println(Arrays.deepToString(vegMenu.toArray()));

		vegMenu = menu.stream()
				.filter(d->d.getType()==Dish.Type.MEAT)
				.limit(2)
				.collect(Collectors.toList());


		//System.out.println(Arrays.deepToString(vegMenu.toArray()));
		
		
		List<Integer> disNames = menu.stream()
									.map(Dish::getName)
									.map(String::length)
									.collect(Collectors.toList());
		//System.out.println(Arrays.deepToString(disNames.toArray()));

		disNames = menu.stream()
				.map(Dish::getCalories)
				.collect(Collectors.toList());
		//System.out.println(Arrays.deepToString(disNames.toArray()));
		
		List<HashMap<String,Integer>> dishs = menu.stream()
							.map(d->new HashMap<String,Integer>(Collections.singletonMap(d.getName(), d.getCalories())))
							.collect(Collectors.toList());
		//System.out.println(Arrays.deepToString(dishs.toArray()));
		
		
		List<String> words = Arrays.asList("Hello", "Word");
		List<String> chars = words.stream()
				.map(w->w.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(Arrays.deepToString(chars.toArray()));
		
		
		nums = Arrays.asList(1,2,3,4,5);
		nums.stream()
			.map(i->i*i)
			.forEach(System.out::println);
		
		List<Integer>num1 = Arrays.asList(1,2,3);
		List<Integer>num2 = Arrays.asList(4,5);
		//Change steam ops only keep i+j % 3==0 in the result
		num1.stream()
				.flatMap(i->num2.stream()
						.filter(j->(i+j)%3==0)
						.map(j->new int[] {i,j}))
				.map(a->String.format("[%d,%d]", a[0],a[1]))
				.forEach(System.out::println);
		
		
		if(menu.stream().anyMatch(Dish::isVegetarian))
		{
			System.out.println("Find something in the menua which is veg");
		}
		
		if(menu.stream().allMatch(Dish::isVegetarian))
		{
			System.out.println("Find something in the menua which is veg");
		}
		else
		{
			System.out.println("Not all dish are veg");
		}
		
		if(menu.stream().noneMatch(d->d.getCalories()>500))
		{
			System.out.println("It is a health menu");
		}
		else
		{
			System.out.println("It is not a health menu");
		}
		
		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		dish.ifPresent(d->System.out.println(d.getName()));
		if(dish.isPresent())
		{
			Dish d = dish.get();
			System.out.println(d.getName());
		}
		Dish d = menu.stream().filter(Dish::isVegetarian).findAny().orElse(null);
		
		d= menu.stream().filter(Dish::isVegetarian).findFirst().orElse(null);
		
		System.out.println(nums.stream().reduce(1,(a,b)->a*b));
		System.out.println(nums.stream().reduce(0,Integer::sum));
		System.out.println(nums.stream().reduce(Integer::sum).orElse(0));
		System.out.println(nums.stream().reduce(Integer::max).orElse(0));
		System.out.println(nums.stream().reduce(Integer::min).orElse(0));
		
		System.out.println(nums.stream().count());
		System.out.println(nums.stream().map(i->1).reduce(0,Integer::sum));
		
		menu.stream().sorted().forEach(System.out::println);
		menu.stream().sorted((a,b)->a.calories-b.calories).forEach(System.out::println);
		menu.stream().sorted(Comparator.comparing(Dish::getType)).forEach(System.out::println);
	}

	@Override
	public int compareTo(Dish o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
}
