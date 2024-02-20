import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LineWrap {
  private final int lineWidth;
  private final Map<Pair<PList<String>,Integer>,Result> hash = new HashMap<>();

  LineWrap (int lineWidth) {
      this.lineWidth = lineWidth;
  }

  //-------------------------------------------------------------------------

  String greedy (PList<String> words, int space) {
        if (words.isEmpty()) return "";
        String w = words.get(0);
        PList<String> rest = words.remove(0);
        if (w.length() + 1 > space) return "\n" + w + greedy(rest, lineWidth - w.length());
        else return " " + w + greedy(rest, space - w.length() - 1);
  }

  String runGreedy (String s) {
      PList<String> words = PList.fromArray(s.split("\\s"));
      String w = words.get(0);
      PList<String> rest = words.remove(0);
      return w + greedy(rest, lineWidth - w.length());
    }

  //-------------------------------------------------------------------------
  Result topdown (PList<String> words, int space) {
      if (words.isEmpty()) {
          return new Result("", 0);
      }

      Pair<PList<String>, Integer> key = new Pair<>(words, space);
      if (hash.containsKey(key)) {
          return hash.get(key);
      }

      String w = words.get(0);
      PList<String> rest = words.remove(0);
      Result r;

      if (w.length() + 1 > space) {
          r = new Result("\n" + w + topdown(rest, space).para(), space * space * space);
      } else {
          Result r1 = topdown(rest, space - w.length() - 1).updatePara(w);
          Result r2 = topdown(rest, space).updateCost(w, space);

          // If we are at the beginning of a new line, reset the cost to 0
          if (r1.para().startsWith("\n")) {
              r1 = r1.updateCost("", 0);
          }

          if (r2.para().startsWith("\n")) {
              r2 = r2.updateCost("", 0);
          }

          r = r1.cost() < r2.cost() ? r1 : r2;
      }

      hash.put(key, r);
      return r;
  }

  String runTopdown (String s) {
        hash.clear();
        PList<String> words = PList.fromArray(s.split("\\s"));
        String w = words.get(0);
        PList<String> rest = words.remove(0);
        return w + topdown(rest,lineWidth - w.length()).para();
    }

    //-------------------------------------------------------------------------

}
