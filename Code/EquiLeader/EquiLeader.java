package EquiLeader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author M.Kotkov
 */
public class EquiLeader {

    public static int solution(int[] A) {
        /*
         Creating map here and passing it to findLeader method to get occurences for later usage.
         Of course findLeader method has side effect which is bad (not only finds leader, but also number of it's occurences),
         but Java doesn't have tuples like python (at least in standard library), can't create lightweight objects like Json
         and can't return multiple values like GO.
         And although writing class for tuple here would be trivial, I'm too butthurt about not having it in standard library to do that.
         */
        Map<Integer, Integer> map = new HashMap<>();
        Integer leader = findLeader(A, map);
        if (leader == null) {
            return 0;
        }
        int occurencesTotal = map.get(leader);
        int occurences = 0;
        int countEqui = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == leader) {
                occurences++;
            }
            if (isEquiSequence(occurences, i + 1)
                    && isEquiSequence(occurencesTotal - occurences, A.length - (i + 1))) {
                countEqui++;
            }
        }
        return countEqui;
    }

    private static Integer findLeader(int[] A, Map<Integer, Integer> map) {
        int occurencesForDomination = A.length / 2 + 1;
        Integer leader = null;
        for (int i = 0; i < A.length; i++) {
            map.compute(A[i], (k, v) -> v == null ? 1 : v + 1);
            if (leader == null && map.get(A[i]) == occurencesForDomination) {
                leader = A[i];
            }
        }
        return leader;
    }

    private static boolean isEquiSequence(int occurences, int seqSize) {
        return seqSize / 2 < occurences;
    }
}
