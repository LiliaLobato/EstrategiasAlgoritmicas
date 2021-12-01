import java.util.*;

public class Greedy {

  static class Item implements Comparable<Item> {
    double value;
    double weight;
    double valuePerWeight;

    public Item(double v, double w) {
      this.value = v;
      this.weight = w;
      this.valuePerWeight = v/w;
    }

    @Override
    public int compareTo(Item item) {
      if(item.valuePerWeight == this.valuePerWeight){
        return 0;
      }else if(item.valuePerWeight > this.valuePerWeight){
        return 1;
      }else{
        return -1;
      }
    }
  }  

  static double knapsack(List<Item> items, double W) {
    // hacer el seleccion
    double totalValue=0;
    double w = W;
    PriorityQueue<Item> selection= new PriorityQueue<>();
    for(Item item: items) {
      selection.offer(item);
    }
    // mientras haya peso y haya items seguir metiendo a la mochila
    while(!selection.isEmpty() && w > 0) {
      Item s = selection.poll();
      if(s.weight <= w) {
        w -= s.weight;
        totalValue += s.value;
      }
      //si no cabe el item entero fraccionarlo
      else {
          //double f = w / s.weight;
          totalValue += w * s.valuePerWeight;
          w = 0;
      }
    }
    //regresar cuanto es el valor que metimos
    return totalValue;
  }

  public static void main(String[] args) {
    List<Item> items = new ArrayList<>();
		Collections.addAll(items, new Item(20, 10), new Item(30, 20), new Item(66, 30), new Item(40, 40), new Item(60, 50));
    double W = 100;
    System.out.println(knapsack(items, W));

  }

}
