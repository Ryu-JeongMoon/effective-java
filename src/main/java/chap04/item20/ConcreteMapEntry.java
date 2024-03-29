package chap04.item20;

public class ConcreteMapEntry<K, V> extends AbstractMapEntry<K, V> {

  private final K key;
  private V value;

  public ConcreteMapEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public V setValue(V value) {
    V oldValue = this.value;
    this.value = value;
    return oldValue;
  }
}
