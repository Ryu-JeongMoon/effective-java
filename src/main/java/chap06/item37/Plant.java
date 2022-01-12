package chap06.item37;

public record Plant(String name, chap06.item37.Plant.LifeCycle lifeCycle) {

  enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

  @Override
  public String toString() {
    return name;
  }
}
