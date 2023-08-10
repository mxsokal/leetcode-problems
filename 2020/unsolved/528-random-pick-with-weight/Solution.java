import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.Objects.requireNonNull;

public final class Solution {

    private final double[] percents;
    private final Map<Integer,Integer> countByIndex;
    private final List<Integer> indexes;

    public Solution(int[] weights) {
        requireNonNull(weights, "weights is null");

        BigDecimal sum = BigDecimal.valueOf(0);

        long subsum = 0;
        long limit = Long.MAX_VALUE;

        for (int weight : weights) {
            if (weight < limit) {
                subsum += weight;
                limit -= weight;
            } else {
                sum = sum.add(BigDecimal.valueOf(subsum));
                subsum = weight;
                limit = Long.MAX_VALUE - weight;
            }
        }
        sum = sum.add(BigDecimal.valueOf(subsum));
        percents = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            percents[i] = BigDecimal.valueOf(weights[i]).divide(sum, 7, RoundingMode.HALF_UP).doubleValue();
        }
        countByIndex = new HashMap<>();
        indexes = new ArrayList<>();
        reset();
    }

    private void reset() {
        countByIndex.clear();
        indexes.clear();
        for (int i = 0; i < percents.length; i++) {
            countByIndex.put(i, (int) Math.round(percents.length * percents[i]));
            //if (countByIndex.get(i) != 0) {
                indexes.add(i);
            //}
        }
        System.out.println("reset " + countByIndex);
    }

    public int solve() {
        int index;
        int result;
        int count;

        if (indexes.isEmpty()) {
            reset();
        }
        index = ThreadLocalRandom.current().nextInt(0, indexes.size());
        result = indexes.get(index);
        count = countByIndex.merge(result, 0, (o, n) -> o - 1);
        //System.out.println("index " + index + " count " + count);
        if (count == 0) {
            indexes.remove(index);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution(new int[]{3,14,1,7});

        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
        System.out.println(s.solve());
    }


}