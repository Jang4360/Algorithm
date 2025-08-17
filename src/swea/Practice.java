package swea;
import java.lang.*;
import java.io.*;
import java.util.*;

public class Practice {
	static final double PI = 3.141592;
	public static void main(String[] args) {
		int[] start = {1,1};
		int[] end = {2,2};
		int a = Math.abs(end[0]-start[0]);
		int b = Math.abs(end[1]-start[1]);
		
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		
		double radian = Math.atan(b/a);
		System.out.printf("%.3f","%f", radian, radian);
		System.out.println();
		System.out.println(Math.toDegrees(radian));
		
//		System.out.printf("%f","%f", c, radian*(180/PI));
 	}
}

