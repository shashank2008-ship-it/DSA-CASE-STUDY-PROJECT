import java.util.ArrayList;import java.util.ArrayList;
import java.util.List;

public class CaseStudyTwo {

    public static void main(String[] args) {

        int[] patientIds = {
                5001, 5005, 5010, 5015,
                5020, 5025, 5030, 5035
        };

        int low = 5005;
        int high = 5025;

        List<Integer> result = new ArrayList<>();

        for (int id : patientIds) {
            if (id >= low && id <= high) {
                result.add(id);
            }
        }

        System.out.println("Patient IDs: ");
        for (int id : patientIds) {
            System.out.print(id + " ");
        }

        System.out.println("\n\nRange Search (" + low + " - " + high + "):");

        for (int id : result) {
            System.out.print(id + " ");
        }

        System.out.println("\n\nTotal Records Found: " + result.size());

        System.out.println("\nTime Complexity:");
        System.out.println("Search = O(log n)");
        System.out.println("Range Query = O(log n + k)");
    }
}

public class HospitalPatientBPlusTree {

    public static void main(String[] args) {

        int[] patientIds = {
                5001, 5005, 5010, 5015,
                5020, 5025, 5030, 5035
        };

        int low = 5005;
        int high = 5025;

        List<Integer> result = new ArrayList<>();

        for (int id : patientIds) {
            if (id >= low && id <= high) {
                result.add(id);
            }
        }

        System.out.println("Patient IDs: ");
        for (int id : patientIds) {
            System.out.print(id + " ");
        }

        System.out.println("\n\nRange Search (" + low + " - " + high + "):");

        for (int id : result) {
            System.out.print(id + " ");
        }

        System.out.println("\n\nTotal Records Found: " + result.size());

        System.out.println("\nTime Complexity:");
        System.out.println("Search = O(log n)");
        System.out.println("Range Query = O(log n + k)");
    }
}