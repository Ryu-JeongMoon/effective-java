package chap06.item37;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

public enum Phase {

  SOLID, LIQUID, GAS;

  public enum OrdinalTransition {

    MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

    private static final OrdinalTransition[][] TRANSITIONS = {
      {null, MELT, SUBLIME},
      {FREEZE, null, BOIL},
      {DEPOSIT, CONDENSE, null}
    };

    public static OrdinalTransition from(Phase from, Phase to) {
      return TRANSITIONS[from.ordinal()][to.ordinal()];
    }
  }

  @RequiredArgsConstructor
  public enum Transition {

    MELT(SOLID, LIQUID),
    FREEZE(LIQUID, SOLID),
    BOIL(LIQUID, GAS),
    CONDENSE(GAS, LIQUID),
    SUBLIME(SOLID, GAS),
    DEPOSIT(GAS, SOLID);

    private static final Map<Phase, Map<Phase, Transition>> TRANSITION_MAP =
      Arrays.stream(values())
        .collect(groupingBy(
            t -> t.from,
            () -> new EnumMap<>(Phase.class),
            toMap(
              t -> t.to, t -> t,
              (x, y) -> y, () -> new EnumMap<>(Phase.class))
          )
        );

    private final Phase from;
    private final Phase to;

    public static Transition from(Phase from, Phase to) {
      return TRANSITION_MAP.get(from).get(to);
    }
  }
}
