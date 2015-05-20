package com.flowlikeariver.kakuro.cell;

public class AcrossCell implements Cell, Across {

int total;

public AcrossCell(int total) {
  this.total = total;
}

@Override
public String draw() {
  return String.format("   --\\%-2d  ", total);
}

@Override
public int getAcrossTotal() {
  return total;
}

}
