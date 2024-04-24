import java.util.HashMap;
import java.util.Map;

class DLACC {

  private Map<String, Boolean> locks;

  public DLACC() {
    this.locks = new HashMap<>();
  }

  public synchronized boolean acquireLock(String dataItem) {
    if (!locks.containsKey(dataItem) || !locks.get(dataItem)) {
      locks.put(dataItem, true);
      return true;
    }
    return false;
  }

  public synchronized boolean releaseLock(String dataItem) {
    if (locks.containsKey(dataItem) && locks.get(dataItem)) {
      locks.put(dataItem, false);
      return true;
    }
    return false;
  }
}
