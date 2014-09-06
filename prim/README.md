JAFAR - Primitive Collections
=====

This library is just another attempt to make an easy to use primitive collections in Java.
So far, currently implemented collections consist of only dynamic arrays for all primitive types - JArrayList.
Recently I've started to work on Maps.

Artifacts: [Maven Central](https://search.maven.org/#search|ga|1|a%3A%22jafar-prim%22)

##Intro

I always try to compare the performance (memory and speed) of Jafar implementations with the great existing libraries:
* [fastutil](http://fastutil.di.unimi.it/)
* [trove](http://trove.starlight-systems.com/)
* [colt](http://acs.lbl.gov/ACSSoftware/colt/)

During my experiments I try to take maximum from all libraries above.
All comparisons are implemented in [bm](https://github.com/andreytim/jafar/tree/master/bm) module through [jmh](http://openjdk.java.net/projects/code-tools/jmh/) benchmarks and will be visualized soon.

Sweet tidbit to traditional ArrayList's primitive implementations in Jafar is **JArrayList of Booleans**,
which is implemented in bitwise manner and is **eight times more effective in the sense of memory** than the same from the
libraries above.
Implementations of JArrayList for other types are quite competitive with corresponding ones from other libraries.

The main point and cool part of all this adventure is an opportunity to stay within the same interface with Core Java Collections even in the sense of Generics, which is actually the main feature.

Let me explain exactly what I mean here.
We assume below that you already have the Jafar library included to the dependencies of your project.

##Usage & Features

For example you have such code:

```java
// simple Java ArrayList of Integers
List<Integer> list = new ArrayList<Integer>();
```

Just add one letter **J** to the beginning of the constructor call:

```java
// Jafar primitive ArrayList of ints
List<Integer> list = new JArrayList<Integer>();
```

Here you get basically the same code but eating several times less memory because now it's primitive array inside and not an array of Objects. If you consider only the footprint than you may be cool just with this tiny change.

If the performance is also an issue and you're suffering from a really huge array (which is highly probable considering your concern about footprint) than you can do a little bit more here:

```java
JList<Integer> list = new JArrayList<Integer>();
```

We've just extended the interface. It is still java.util.List but with some sweet additional capabilities.

First, if you have somewhere in your code something like this:

```java
for (int i : collection) {
  list.add(i); // no boxing/unboxing here for JList
}
```

then by adding J prefix to the List interface you automatically switch above code to overrided primitive typed method add(int e) of JList interface and gain all profit from getting rid of horrible boxing/unboxing redundancies.

Second, if you have somewhere in your code something like this:

```java
for (int i : list) {
  // do stuff with i
}
```

or

```java
for (int i = 0; i < list.size(); i++) {
  int val = list.get(i);
  // do stuff with val
}
```

then you need a little bit more refactoring:

```java
for (int i = 0; i < list.size(); i++) {
  int val = list.getInt(i); // here is the refactoring
  // do stuff with val
}
```

Unfortunately, I couldn't come up with an idea of simplifying foreach loop so far.
Only something like functional forEach() method which looks even more cumbersome than above option even in Java 8.
It could be different variants of conversion to array and iterating through it.
Actually, i've added such capability to JList:

```java
for (int i : list.ints()) {
  // do something with i
}
```

But if the loop is performed only once there's no positive gain in performance due to making safe copy of the data
internally.
