//package org.kanomchan.core.common.test;
//import static org.matheclipse.core.expression.F.*;
//
//import org.matheclipse.core.basic.Config;
//import org.matheclipse.core.eval.EvalUtilities;
//import org.matheclipse.core.interfaces.IAST;
//import org.matheclipse.core.interfaces.IExpr;
//import org.matheclipse.parser.client.SyntaxError;
//import org.matheclipse.parser.client.math.MathException;
//
//public class CalculusExample {
//	public static void main(String[] args) {
//		try {
//			EvalUtilities util = new EvalUtilities(false, true);
//			// Show an expression in the Java form:
////			String javaForm = util.toJavaForm("awefsdfwef+b+c+d+e");
////			// prints: D(Times(Sin(x),Cos(x)),x)
////			System.out.println(javaForm.toString());
//
////			// Use the generated Java form to create an expression with F.* static methods:
////			IAST function = D(Times(Sin(x), Cos(x)), x);
//			IExpr result = null;
//			result = util.evaluate("a=5");
//			result = util.evaluate("b=5");
//			result = util.evaluate("c=5");
//			result = util.evaluate("d=5");
//			result = util.evaluate("e=5");
//			result = util.evaluate("a+b+c+d+e");
////			// print: -Sin(x)^2+Cos(x)^2
//			System.out.println(result.toString());
////
////			// evaluate the string directly
////			result = util.evaluate("d(sin(x)*cos(x),x)");
////			// print: -Sin(x)^2+Cos(x)^2
////			System.out.println(result.toString());
////
////			// evaluate the last result ($ans contains "last answer")
////			result = util.evaluate("$ans+cos(x)^2");
////			// print: -Sin(x)^2+2*Cos(x)^2
////			System.out.println(result.toString());
////
////			// evaluate an Integrate[] expression
////			result = util.evaluate("integrate(sin(x)^5,x)");
////			// print: -1/5*Cos(x)^5+2/3*Cos(x)^3-Cos(x)
////			System.out.println(result.toString());
////
////			// set the value of a variable "a" to 10
////			// Note: in server mode the variable name must have a preceding '$' character
////			result = util.evaluate("a=10");
////			// print: 10
////			System.out.println(result.toString());
////
////			// do a calculation with variable "a"
////			result = util.evaluate("a*3+b");
////			// print: b+30
////			System.out.println(result.toString());
////
////			// define a function with a recursive factorial function definition.
////			// Note: fac(0) is the stop condition which must be defined first.
////			result = util.evaluate("fac(0)=1;fac(x_IntegerQ):=x*fac(x-1)");
////			// now calculate factorial of 10:
////			result = util.evaluate("fac(10)");
////			// print: 3628800
////			System.out.println(result.toString());
////
////			// Use [...] for function arguments instead of (...) and upper case names for predefined functions (i.e. Sin[...]
////			// instead of sin[...]).
////			EvalUtilities util2 = new EvalUtilities(false, false);
////
////			if (Config.PARSER_USE_LOWERCASE_SYMBOLS) {
////				// If true the parser doesn't distinguish between lower- or uppercase predefined symbols
////				result = util2.evaluate("integrate[sin[x]^5,x]");
////			} else {
////				result = util2.evaluate("Integrate[Sin[x]^5,x]");
////			}
////			// print: -1/5*Cos(x)^5+2/3*Cos(x)^3-Cos(x)
////			System.out.println(result.toString());
//
//		} catch (SyntaxError e) {
//			// catch Symja parser errors here
//			System.out.println(e.getMessage());
//		} catch (MathException me) {
//			// catch Symja math errors here
//			System.out.println(me.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//}
//}
