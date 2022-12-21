package MinAvgTwoSlice;

public class MinAvgTwoSlice {

    public static int solution(int[] A) {
        int[] prefixSums = buildPrefixSums(A);

        float minAvg = 20000;
        int beginningOfTheSmallestRange = 0;
        for (int i = 0; i < prefixSums.length - 2; i++) {
            float avg = (prefixSums[i + 2] - prefixSums[i])/2f;

            if (i < prefixSums.length - 3) {
                avg = Math.min(avg, (prefixSums[i + 3] - prefixSums[i])/3f);
            }

            if (avg < minAvg) {
                beginningOfTheSmallestRange = i;
                minAvg = avg;
            }
        }

        return beginningOfTheSmallestRange;
    }

    private static int[] buildPrefixSums(int[] A) {
        int[] prefixSums = new int[A.length + 1];
        prefixSums[0] = 0;
        for (int i = 0; i < A.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + A[i];
        }
        return prefixSums;
    }
}
