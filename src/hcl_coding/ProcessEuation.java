package hcl_coding;

public class ProcessEuation {
	public static void main(String args[]) {
		String equation = "((1+5)+6+(10+8))";
		// we can provide equation here
		int result = 0;
		if (equation != null && equation.length() > 0) {
			String equationsArr[] = equation.split("\\+\\(");
			for (String currentEquation : equationsArr) {
				if (currentEquation != null && currentEquation.contains(")")) {
					String str[] = currentEquation.split("\\)");
					for (int i = 1; i < str.length; i = i + 2) {
						String current[] = str[i - 1].replace("((", "").split("\\+");
						if (current.length == 2) {
							int a = Integer.parseInt(current[0]);
							int b = Integer.parseInt(current[1]);
							int a_cross_b = a * b;
							int a_plus_b = a + b;
							int res = a_cross_b > a_plus_b ? a_cross_b : a_plus_b;
							if (res > result)
								result = res;
						}

					}
				}
			}
		}
		System.out.println("Equation result: " + result);
	}
}
