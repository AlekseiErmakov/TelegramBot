package parse;

import java.util.ArrayList;
import java.util.List;

public class Example {
    List<? extends Animal> aniOne = getAnimals();
    List<? extends Animal> aniTwo = getCats();
    List<? extends Animal> aniThree = getDogs();
    //ошибка
    List<? extends Animal> aniFour = getLions();
    List<? super Animal> aniFive = getAnimals();
    List<? super Animal> aniSix = getLions();
    //ошибка
    List<? super Animal> aniSeven = getCats();
    //ошибка
    List<? super Animal> aniEight = getDogs();
    public void method(){
        aniOne.add(new Cat());
        aniTwo.add(new Cat());
        aniThree.add(new Cat());
        aniOne.add(new Animal());
        aniTwo.add(new Animal());
        aniThree.add(new Animal());
        aniOne.add(new Dog());
        aniTwo.add(new Dog());
        aniThree.add(new Dog());

        aniOne.get(0).eat();
        aniSix.get(0).
    }
    public void method(){
        aniFive.add(new Cat());
        aniSix.add(new Dog());
    }
    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        return animals;
    }
    public List<Cat> getCats() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        return cats;
    }
    public List<Dog> getDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        return dogs;
    }
    public List<Lion> getLions() {
        List<Lion> lions = new ArrayList<>();
        lions.add(new Lion());
        return lions;
    }

}
class Lion{
    public void eat(){};
}
class Animal extends Lion{
    public void shout(){
    }
}
class Cat extends Animal{
    int tail;
    public void shout(){
    }
    public int getTail(){
        return tail;
    }
}
class Dog extends Animal{
    double height;
    public void shout(){
    }
    public double getHeight(){
        return height;
    }
}