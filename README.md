Build Domino
============

Introduction
------------

This program was developed to construct domino tableaux from elements
of type B and D
[Coxeter groups](http://en.wikipedia.org/wiki/Coxeter_group). Devra
Garfinkle developed an algorithm similar to the
[Robinson--Schensted correspondence](http://en.wikipedia.org/wiki/Robinson%E2%80%93Schensted_correspondence)
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
Coxeter group of type D is isomorphic to an index 2 subgroup.  In
future versions of BuildDomino I hope to write a method to convert an
expression of a Coxeter group in terms of generators to one line
notation.  In this version however, you must enter your elements in
one line notation.

Output
------

Once you enter your Coxeter Group element in one line notation,
BuildDomino outputs the domino tableau in two forms.  It shows the
tableau in a graphical window, and outputs the tableau in
[TikZ](http://sourceforge.net/projects/pgf/) code for inclusion in
[LaTeX](http://www.latex-project.org/) documents.
