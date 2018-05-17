package com.Bean.AnalyXML;

/**
 * 
 * <b>xml导航对象<b>
 * @author 威 
 * <br>2018年3月31日 下午10:28:28 
 *
 */
public class Navig {
	public String name = "";
	public Navig[] next;
	public Navig (String name, Navig[] next){
		this.name = name;
		this.next = next;
	}
	@Override
	public String toString(){
		StringBuilder nextBuil = new StringBuilder().append("[").append(this.name);
		if(next != null){
			for(Navig navi : next)
				nextBuil.append(navi);
		}
		return nextBuil.append("]").toString(); 
	}
	public static void main(String[] args){
		Navig service = new Navig("service", null);
		Navig container = new Navig("container", null);
		Navig container2 = new Navig("container", null);
		container.next = new Navig[]{service, container2};
		Navig context = new Navig("context", null);
		context.next =  new Navig[]{container};
		System.out.println(context);
	}
}
