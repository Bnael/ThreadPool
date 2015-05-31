
public class Result {
	
double res;
	public Result(){
		res = 1;
	}
	
	public void setRes(double d){
		this.res = d;
	}
	
	@Override
	public String toString() {
		return "Result [res=" + res + "]";
	}
}
