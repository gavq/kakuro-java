package com.flowlikeariver.kakuro;

import com.flowlikeariver.kakuro.cell.ValueCell;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sum {

private final int total;
private final List<ValueCell> cells = new ArrayList<>();
List<Set<Integer>> possibles;

public Sum(int total, Collection<ValueCell> valueCells) {
  this.total = total;
  cells.addAll(valueCells);
}

// All different is part of the definition of a kakuro puzzle
private boolean allDifferent(int value, List<Integer> candidates) {
  List<Integer> trial = new ArrayList<>(candidates);
  trial.add(value);
  return (new HashSet<>(trial).size() == trial.size());
}

// All different is part of the definition of a kakuro puzzle
private boolean allDifferent(List<Integer> candidates) {
  return (new HashSet<>(candidates).size() == candidates.size());
}

// Exhaustive search for possible solutions
private Stream<List<Integer>> permute(int pos, int target, List<Integer> candidates) {
  if (target >= 1) {
    if (pos == (cells.size() - 1)) {
      List<Integer> p = new ArrayList<>(candidates);
      p.add(target);
      return Stream.of(p);
    }
    else {
      return cells.get(pos).getValues().stream()
              .flatMap(v -> {
                List<Integer> trial = new ArrayList<>(candidates);
                trial.add(v);
                return permute(pos + 1, target - v, trial);
              });
    }
  }
  else {
    return Stream.empty();
  }
}

private int update(int pos) {
  ValueCell cell = cells.get(pos);
  int previousSize = cell.size();
  cell.setValues(possibles.get(pos));
  return previousSize - cell.size();
}

public int solve() {
  possibles = cells.stream().map(cell -> new TreeSet<Integer>()).collect(toList());
  int last = cells.size() - 1;
  permute(0, total, new ArrayList<>())
          .filter(p -> cells.get(last).isPossible(p.get(last)))
          .filter(this::allDifferent)
          .forEach(p -> {
            IntStream.rangeClosed(0, last).forEach(i -> possibles.get(i).add(p.get(i)));
          });
  return IntStream.range(0, cells.size())
          .map(this::update)
          .sum();
}

}
