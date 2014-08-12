JAFAR - Primitive Collections
=====

Here is just another attempt to make an easy to use library of primitive collections in Java.

There's only an implementation of dynamic array so far - JArrayList.

The main point of all this adventure is to stay within the same interface with the Core Java Collections
even in the sense of Generics, which is actually the main feature.

Let me explain exactly what I mean here.
We assume below that you already have the Jafar library in the dependencies of your project.
For example you've got:

```
// simple Java ArrayList of Integers
List<Integer> list = new ArrayList<Integer>();
```

Just add one letter to the beginning of your class definition:

```
// Jafar primitive ArrayList of ints
List<Integer> list = new JArrayList<Integer>();
```

Here you get basically the same code but eating 4-5 times less memory because it's primitive array inside.
If you consider only the footprint than you may be cool just with this tiny change.

If the performance is also an issue and you're suffering from a really huge array
(which is highly probable considering your concern about footprint)
than you can do a little bit more here:

```
JList<Integer> list = new JArrayList<Integer>();
```

We've just extended the interface. It is still java.util.List but with some sweet additional capabilities.

First, if you have somewhere in your code something like this:

```
for (int i : collection) {
  list.add(i); // no boxing/unboxing here for JList
}
```

then by adding J prefix to the List interface you automatically switch above code
to overrided primitive typed method add(int e) of JList interface
and gain all profit from getting rid of horrible boxing/unboxing redundancies.

Second, if you have somewhere in your code something like this:

```
for (int i : list) {
  // do stuff with i
}
```

or

```
for (int i = 0; i < list.size(); i++) {
  int val = list.get(i);
  // do stuff with val
}
```

then you need a little bit more refactoring:

```
for (int i = 0; i < list.size(); i++) {
  int val = list.getInt(i); // here is the refactoring
  // do stuff with val
}
```

Unfortunately, I can't come up with an idea of simplifying foreach loop so far.
Only something like functional forEach() method which looks even more cumbersome than above option even in Java 8.
It could be different variants of conversion to array and iterating through it.
Actually, i've added such capability to JList:

```
for (int i : list.ints()) {
  // do something with i
}
```

But if the loop is performed only once there's no positive gain in performance due to making safe copy of the data
internally.