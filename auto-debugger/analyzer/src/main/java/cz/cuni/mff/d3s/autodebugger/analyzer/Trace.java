package cz.cuni.mff.d3s.autodebugger.analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trace {
  private Map<Integer, Set<Byte>> byteSlotValues = new HashMap<>();
  private Map<Integer, Set<Character>> charSlotValues = new HashMap<>();
  private Map<Integer, Set<Short>> shortSlotValues = new HashMap<>();
  private Map<Integer, Set<Integer>> intSlotValues = new HashMap<>();
  private Map<Integer, Set<Long>> longSlotValues = new HashMap<>();
  private Map<Integer, Set<Float>> floatSlotValues = new HashMap<>();
  private Map<Integer, Set<Double>> doubleSlotValues = new HashMap<>();
  private Map<Integer, Set<Boolean>> booleanSlotValues = new HashMap<>();

  public void addByteArgValue(int slot, byte value) {
    if (byteSlotValues.containsKey(slot)) {
      byteSlotValues.get(slot).add(value);
    } else {
      byteSlotValues.put(slot, Set.of(value));
    }
  }

  public void addCharArgValue(int slot, char value) {
    if (charSlotValues.containsKey(slot)) {
      charSlotValues.get(slot).add(value);
    } else {
      charSlotValues.put(slot, Set.of(value));
    }
  }

  public void addShortArgValue(int slot, short value) {
    if (shortSlotValues.containsKey(slot)) {
      shortSlotValues.get(slot).add(value);
    } else {
      shortSlotValues.put(slot, Set.of(value));
    }
  }

  public void addIntArgValue(int slot, int value) {
    if (intSlotValues.containsKey(slot)) {
      intSlotValues.get(slot).add(value);
    } else {
      intSlotValues.put(slot, Set.of(value));
    }
  }

  public void addLongArgValue(int slot, long value) {
    if (longSlotValues.containsKey(slot)) {
      longSlotValues.get(slot).add(value);
    } else {
      longSlotValues.put(slot, Set.of(value));
    }
  }

  public void addFloatArgValue(int slot, float value) {
    if (floatSlotValues.containsKey(slot)) {
      floatSlotValues.get(slot).add(value);
    } else {
      floatSlotValues.put(slot, Set.of(value));
    }
  }

  public void addDoubleArgValue(int slot, double value) {
    if (doubleSlotValues.containsKey(slot)) {
      doubleSlotValues.get(slot).add(value);
    } else {
      doubleSlotValues.put(slot, Set.of(value));
    }
  }

  public void addBooleanArgValue(int slot, boolean value) {
    if (booleanSlotValues.containsKey(slot)) {
      booleanSlotValues.get(slot).add(value);
    } else {
      booleanSlotValues.put(slot, Set.of(value));
    }
  }

  public void printSlotValues() {
    printSlotValues(byteSlotValues);
    printSlotValues(charSlotValues);
    printSlotValues(shortSlotValues);
    printSlotValues(intSlotValues);
    printSlotValues(longSlotValues);
    printSlotValues(floatSlotValues);
    printSlotValues(doubleSlotValues);
    printSlotValues(booleanSlotValues);
  }

  private <T> void printSlotValues(final Map<Integer, Set<T>> slotValues) {
    for (Map.Entry<Integer, Set<T>> entry : slotValues.entrySet()) {
      System.out.println("Slot: " + entry.getKey() + " values: " + entry.getValue());
    }
  }


}
