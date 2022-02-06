package chap06.item37;

public record Plant(String name, LifeCycle lifeCycle) {

  @Override
  public String toString() {
    return name;
  }

  enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}
}
