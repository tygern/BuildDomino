Installation
============

1.  Clone the BuildDomino repository:

        git clone git@github.com/tygern/BuildDomino.git

2. *(Optional)* Install [buildr](http://buildr.apache.org/)

3. *(Optional)* Build the jar package.

        cd BuildDomino/
        buildr package

4. Run the BuildDomino jar in the target directory.

        cd BuildDomino/target/
        java -jar build-domino-x.y.z.jar
        
**Note:** Requires java JDK 7

Introduction
============

Coxeter Groups
--------------

This program was developed to preform elementary operation on elements
of type D [Coxeter
groups](http://en.wikipedia.org/wiki/Coxeter_group).  It is also able
to construct domino tableaux from elements of type D Coxeter
groups. Devra Garfinkle developed an algorithm similar to the
[Robinson--Schensted
correspondence](http://en.wikipedia.org/wiki/Robinson%E2%80%93Schensted_correspondence)
to associate a signed permutations to a pair of domino tableaux. This
pair of tableaux are difficult to compute by hand but are very
useful. Like the Robinson--Schensted correspondence, this pair can be
used to calculate the Kazhdan--Lusztig cells of the Coxeter group.
The computation of these cells, as described in Garfinkle's papers,
*On the classification of primitive ideals for complex classical Lie
algebras* [I](http://www.numdam.org/item?id=CM_1990__75_2_135_0),
[II](http://www.numdam.org/item?id=CM_1992__81_3_307_0), and
[III](http://www.numdam.org/item?id=CM_1993__88_2_187_0), involves
moving tableaux through sets called *cycles*.  I hope to add these
calculations to a future version of BuildDomino.

Signed Permutations
-------------------

A
[signed permutation](http://en.wikipedia.org/wiki/Hyperoctahedral_group)
is a map from the set {-n, -(n-1), ... , -1, 1, ... , n-1, n} to
itself such that x maps to y if and only if -x maps to -y. The set of
signed permutations can be represented as permutations of n coins
where flipping is allowed.  As with regular permutations, each signed
permutation can be written in *one line notation*.  A Coxeter group of
type B is isomorphic to the group of signed permutations, and a
Coxeter group of type D is isomorphic to an index 2 subgroup.

Use
===

Input
-----

You may input an element either as a signed permutation or as a
expression in terms of generators.  In either case, you must enter
your element as a list of integers separated by commas.  For example,
I would input the signed permutation that fixes 2 and 4, sends 1 to -3
and 3 to -1 as \[\-3, 2, \-1, 4\]. I would enter the element
\(s1\)\(s2\)\(s3\)\(s4\)\(s3\) as \[1,2,3,4,3\].  I use (s_3) as my
branch node for the type D
[Coxeter diagram](http://en.wikipedia.org/wiki/Coxeter%E2%80%93Dynkin_diagram).

Output
------

### Elementary Calculations

BuildDomino is able to compute the length, descent sets, and inverse
of an element.  It can also tell whether a particular element is a
*bad* element.  We define a bad element to be an element that has no
reduced expressions beginning or ending in two noncommuting generators
that is not a product of commuting generators.  Each of these
computations is conducted using the signed permutation corresponding
to the element.

### Domino Tableaux

Once the user enters a Coxeter group element, BuildDomino can output
the right and left domino tableau.  The user has two choices for the
form of the output.  The tableau can be drawn in a graphical window or
can be written in [TikZ](http://sourceforge.net/projects/pgf/) code
for inclusion in [LaTeX](http://www.latex-project.org/) documents.
You also have the option of viewing your original element or its
inverse as a signed permutation.  This is especially useful if you
enter an element in terms of generators.
