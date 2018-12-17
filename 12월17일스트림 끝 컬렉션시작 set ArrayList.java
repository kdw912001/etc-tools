package io.silsub1;
import java.util.*;
import java.io.*;
public class MyNote {
	private Scanner sc = new Scanner(System.in);
	public MyNote(){}
	public void fileSave(){
		StringBuilder sb = new StringBuilder();
		System.out.println("파일에 저장할 내용을 입력하시오");
		while(true){			
			String s = sc.nextLine();			
			if(s.equals("exit"))	break;			
			sb.append(s + "\n");
		}
		System.out.println("저장하시겠습니까? (y/n) : ");			
		if(sc.next().toUpperCase().charAt(0) == 'Y'){			
			System.out.println("저장할 파일명 : ");
			String fileName = sc.next();
			try(BufferedWriter bw = 
				new BufferedWriter(
					new FileWriter(fileName))){
				bw.write(sb.toString());
				bw.flush();
				System.out.println(fileName + "파일에 성공적으로 저장하였습니다.");
				System.out.println();
			}catch(IOException e){e.printStackTrace();}
		}	
	}
	public void fileOpen(){
		System.out.println("열기할 파일명 : ");
		String fileName = sc.next();
		try(BufferedReader br = 
			new BufferedReader(
				new FileReader(fileName))) {
			while(true){
				String line = br.readLine();
				if(line == null)	break;
				System.out.println(line);
			}
			System.out.println("\n" + fileName + " 읽기 완료...\n");
		} catch (IOException e) {e.printStackTrace();}
	}
	public void fileEdit(){
		StringBuilder sb = new StringBuilder();
		System.out.println("수정할 파일명 : ");
		String fileName = sc.next();
		try(BufferedReader br = 
				new BufferedReader(
					new FileReader(fileName));
			BufferedWriter bw = 
				new BufferedWriter(
				new FileWriter(fileName, true));) {
			//파일의 내용 읽기
			String line;
			while((line = br.readLine()) != null)
				sb.append(line + "\n");
			
			//추가할 내용 입력받기
			System.out.println("파일에 추가할 내용을 입력하시오." );
			while(!(line = sc.nextLine()).equals("exit")){
				sb.append(line + "\n");
			}
			System.out.println("변경된 내용을 파일에 추가하시겠습니까? (y/n) : ");
			if(sc.next().toUpperCase().charAt(0) == 'Y'){
				bw.write(sb.toString());
				System.out.println(fileName + " 파일의 내용이 변경되었습니다.");
			}
		} catch (IOException e) {e.printStackTrace();}
	}
}
package io.test;
import java.util.Scanner;
import io.silsub1.MyNote;
public class TestMyNote {
	public static void main(String[] args) {
		// 입출력 실습문제1
		MyNote note = new MyNote();
		Scanner sc = new Scanner(System.in);
		int no;
		do{
		System.out.println("******   MyNote  *******");
		System.out.println();
		System.out.println("1. 노트 새로 만들기");		//MyNote의 fileSave()
		System.out.println("2. 노트 열기");		//MyNote의 fileOpen()
		System.out.println("3. 노트 열어서 수정하기");	//MyNote의 fileEdit()
		System.out.println("4. 끝내기");		//main() 으로 리턴
		System.out.println();
		System.out.print("메뉴 선택 : ");
		no = sc.nextInt();		
		switch(no){
		case 1:	note.fileSave();   break;
		case 2:	note.fileOpen();   break;
		case 3:  note.fileEdit();    break;
		case 4:  System.out.println("MyNote 프로그램을 종료합니다.");
		        return;
		default:	System.out.println("번호 선택이 잘못되었습니다.");
		        System.out.println("다시 선택하십시오.\n");
		}		
		}while(no != 4);
	}
}
package test.chario;
import java.io.*;
public class TestByteToCharStream {
	public static void main(String[] args) {
		//바이트스트림을 문자스트림으로 바꾸기
		// 읽기용스트림에만 적용할 수 있음
		//기본스트림은 inputStream 임  보조스트림은 Reader 임
		
		//키보드 장치 : System.in   API 필드를 보면 변수형태가 static InputStream
		//public static final InputStream.in
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));//보조 스트림 2개를 연결
		//바이트스트림을 문자스트림으로 바꾸는 작업을 할 수 있음
		//얘는 예외 처리가 없음
				
		System.out.println("입력할 내용 : ");
		StringBuilder sb = new StringBuilder();
		String str = null;
		try {
			while((str = br.readLine()).equals("exit") == false) {
				sb.append(str + "\n");
			}
			System.out.println("--------------------");
			System.out.println(sb.toString());
			System.out.println("--------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
}	


package test.set;
import java.util.*;
/*API HashSet 클래스
 * public class HashSet<E>extends AbstractSet<E>implements Set<E>, Cloneable, Serializable  
		     							복사 가능,   직렬화-객체스트림 가능
 * Constructor and Description
HashSet()
Constructs a new, empty set; the backing HashMap instance has default initial capacity (16) and load factor (0.75).
기본 capacity가 16, 용량이 초과되면 0.75배 만큼 용량 늘릴 수 있음(load factor)
HashSet(Collection<? extends E> c)
Constructs a new set containing the elements in the specified collection.
HashSet(int initialCapacity)//저장용량 값을 정하면서 생성자 생성, 기본 load factor는 0.75
Constructs a new, empty set; the backing HashMap instance has the specified initial capacity and default load factor (0.75).
HashSet(int initialCapacity, float loadFactor)//저장용량과 loadfactor를 직접 정함
Constructs a new, empty set; the backing HashMap instance has the specified initial capacity and the specified load factor.*/

public class TestHashSet {
	public static void main(String[] args) {
		// HashSet test
		HashSet hset = new HashSet();
		
		System.out.println(hset.isEmpty());//현재 비어있으니 true
		System.out.println(hset.size()); //없으니 0
		
		//객체만 저장할 수 있음
		hset.add(new String("apple"));
		hset.add("banana"); 
		//문자열 값 자체가 문자열 저장소를 가르키는 주소 발생시킴 
		//new String이 원칙이나 이렇게도 가능
		hset.add(new Integer(123));
		//data(값)--> instance(객체) : Boxing
		//Wrapper 클래스 사용(Boxing사용할 때)
		hset.add(456); //Auto Boxing 처리됨
		//Collection 은 객체만 넣는게 맞지만 Auto Boxing처리 되서 new Integer로 바꿈
		hset.add(new Double(54.7));//Boxing 처리
		hset.add(3.15); //AutoBoxing
		//기본자료형 값에 대해서는
		//컬렉션에 저장시 자동 boxing처리됨
		
		//부모클래스에 toString() 오버라이딩되어 있음
		System.out.println(hset/*.toString()*/);
		//저장순서가 유지 안 됨(set의 특징)
		//중복 허용 안 함
		hset.add("apple"); //같은 값을 넣어도 알아서 중복검사를 함
		hset.add("banana");
		System.out.println(hset);//[banana, apple, 54.7, 456, 3.15, 123]
		System.out.println(hset.size());//6
		
		
		if(hset.contains("apple")) {
			hset.remove("apple");
		}
		//"apple" 객체를 포함하고 있으면 삭제, 삭제 후 출력 및 사이즈 확인
		System.out.println(hset);//[banana, 54.7, 456, 3.15, 123]
		System.out.println(hset.size());//5
		
		/*hset.clear(); //전부 다 지워버림
		System.out.println(hset.isEmpty());//안이 비었으니 true*/
	
		//저장된 객체 정보를 하나씩 꺼내서 사용하는 방법
		//set은 하나를 꺼낼 수 있는 메서드가 없음
		//첫번째 : 저장된 객체의 목록 만들기
		//목록 : iterator  라고 함
		System.out.println("1 ------------------");
		Iterator iter = hset.iterator(); 
		//set에 저장된 객체들을 목록화 하여 iter가 가지고 있음
		//Iterator 인터페이스 내에 hasNext()와 next() 이용
		while(iter.hasNext()) {
			Object obj = iter.next();//리턴 값이 Object
			System.out.println(obj);
		}
		
		//두번째 : Object[] 로 바꾸어 꺼내기
		System.out.println("2 --------------------");
		Object[] objArr = hset.toArray();//객체배열로 바꿈
		for(int i=0; i<objArr.length;i++) {
			System.out.println(objArr[i]);
		}
		
		//세번째 : toArray(T[] a) 사용하는 방법
		System.out.println("3 --------------------");
		//Object[] objArr2 = hset.toArray(objArr);
		Object[] objArr2 = new Object[hset.size()];
		hset.toArray(objArr2);
		for(Object o: objArr2) {//objArr2를 꺼내면 Object
			System.out.println(o);
		}
	}
}




package test.set;
import java.util.*;
public class TestLinkedHashSet {
	public static void main(String[] args) {
		// LinkedHashSet test
		//저장 순서가 유지되는 Set
		//중복 허용 안 함
		
		LinkedHashSet lset = new LinkedHashSet();
		
		lset.add("apple");
		lset.add("banana");
		lset.add(123);
		lset.add(54.7);
		lset.add("apple");
		
		System.out.println(lset);
		System.out.println("1-----------------");
		//첫번째 : 저장된 객체 목록 만들어 하나씩 꺼내기
		Iterator objIter = lset.iterator();
		while(objIter.hasNext()) {
			System.out.println(objIter.next());
		}
		
		//두번째 : Object[] 배열로 바꾸어 하나씩 꺼내기
		System.out.println("2-------------------");
		Object[] objArr = lset.toArray();
		for(int i=0; i<objArr.length;i++)
			System.out.println(objArr[i]);
		
		//세번째 : toArray(T[] a) 사용
		System.out.println("3----------------");
		Object[] objArr2 = new Object[lset.size()];
		lset.toArray(objArr2);
		for(Object o:objArr2)
			System.out.println(o);
	}}
package test.set;import java.util.*;
public class TestTreeSet {
	public static void main(String[] args) {
		// TreeSet test
		//중복 허용 안 함
		//객체를 자동 오름차순 정렬하면서 저장함
		TreeSet tset = new TreeSet();
		//Set tset = new TreeSet(); 가능
		
		tset.add("orange");
		tset.add("banana");
		tset.add("apple");
		tset.add("grape");
		
		System.out.println(tset);
		
		//1. Iterator()
		System.out.println("1-----------------");
		Iterator it = tset.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		//2. toArray()
		System.out.println("2-----------------");
		Object[] objArr = tset.toArray();
		for(Object o: objArr)
			System.out.println(o);
		
		//3. 내림차순 정렬된 목록 만들기
		//descendingIterator()
		System.out.println("3---------------------");
		Iterator descIter = tset.descendingIterator();
		while(descIter.hasNext()) 
			System.out.println(descIter.next());
		
		//4. descendingSet()
		//내림차순 정렬된 Set 만들기
		System.out.println("4 ---------------");
		Set descSet = tset.descendingSet();
		System.out.println(descSet);}}
package practice1;
import java.util.*;
public class Lotto {
	public static void lottoDisplay() {
		TreeSet tset = new TreeSet();//treeset은 자동정렬
		for(int i=0; i<6;i++) {
			tset.add((int)(Math.random()*45)+1);
		}
		System.out.println(tset);
		Iterator it = tset.iterator();
		int []arr = new int[tset.size()];
		int count=0;
		while(it.hasNext()) {
			arr[count]=(int) it.next();
			System.out.print(arr[count]+ " ");
			count++;
		}
		System.out.println();
		
		//선생님 방식
		TreeSet lottoNumbers = new TreeSet();
		Random r = new Random();
		while(true) {
			lottoNumbers.add(r.nextInt(45)+1);
			if(lottoNumbers.size()==6) break;
		}
		System.out.println(lottoNumbers);
		Object[] numbers = lottoNumbers.toArray();
		int[] ar = new int[lottoNumbers.size()];
		
		for(int i=0; i<ar.length;i++) {
			ar[i] = (Integer)numbers[i]; //Object를 Integer로 형변환
			//Auto unboxing
			System.out.print(ar[i]+"\t");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		lottoDisplay();}}

package test.list;
import java.util.*;
/*list는 저장되는 순서가 유지
 * set과 달리 list를 get,sort 있음
 * 	set에서는 iterator를 이용했는데 get(int index)로 값을 얻음
 *  public void sort(Comparator<? super E> c)
 * 
 * set에서 없는 ListIterator가 있음. 
 * Interface ListIterator<E>
 * 메소드 중에 previous()가 있음 앞 뒤 왔다갔다 가능
 */
public class TestArrayList {
	public static void main(String[] args) {
		// ArrayList test
		ArrayList alist = new ArrayList();
		
		//저장 순서가 index 로 매겨짐
		//객체 중복 저장 가능함(set은 중복 허용 안함)
		alist.add("java");
		alist.add("oracle");
		alist.add("jdbc");
		alist.add("java");
		
		System.out.println(alist);//[java, oracle, jdbc, java]
		System.out.println("저장 객체 수 : "+alist.size());
		for(int i=0; i<alist.size();i++) {
			System.out.println(i + " : " + alist.get(i));
		}
		
		//저장순번이 있는 배열이나 리스트는 for each문 사용 가능함
		for(Object obj : alist) {//리스트의 반환형은 Object
			System.out.println(obj);
		}
		
		//add(순번, 객체)
		alist.add(1, "html5"); //1번 인덱스에 값 저장
		System.out.println(alist);//원래 값은 뒤로 밀림
		//[java, html5, oracle, jdbc, java]
	
		//set(index, 변경할 객체)
		alist.set(3, "css3");
		System.out.println(alist);//[java, html5, oracle, css3, java]
		
		//remove(삭제할 객체) : Set 계열 메소드와 동일함
		alist.remove("java");
		System.out.println(alist);//[html5, oracle, css3, java]
	
		//remove(index) set에는 없는 기능 list만의 기능
		alist.remove(2);
		System.out.println(alist);//[html5, oracle, java]		
	}
}

package test.list;
//vo(Value Object) == dto(Data Transfer Object)
//==bean==do(Domain Object) //entity
//위와 같이 값을 저장하는 클래스는 직렬화 처리 원칙
public class Person implements java.io.Serializable{
	public static final long serialVersionUID = 333L;
	private String name;
	private int age;
	private Double point;
	public Person() {}
	public Person(String name, int age, Double point) {
		super();
		this.name = name;
		this.age = age;
		this.point = point;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;	}
	public void setAge(int age) {this.age = age;}
	public Double getPoint() {return point;}
	public void setPoint(Double point) {this.point = point;}
	public static long getSerialversionuid() {	return serialVersionUID;}
	@Override
	public String toString() {return this.name+", "+this.age+", "+this.point;}
}
package test.list;
import java.util.*;
import test.list.Person;
public class TestPersonArrayList {

	public static void main(String[] args) {
		// Person 저장용 ArrayList 사용
		ArrayList list = new ArrayList();
		//list list = new ArrayList()도 가능 (다형성 이용)
		list.add(new Person("홍길동", 25, 1537.5));
		list.add(new Person("이순신", 49, 15789.9));
		list.add(new Person("신사임당", 55, 34567.4));
		
		System.out.println(list);//toString 메소드 오버라이딩
		
		for(Object obj : list) {//꺼내면 기본 타입이 obj 실제론 Person
			System.out.println(obj);
		}
		
		//각 객체가 가진 포인트 값의 합계를 구함
		double totalPoint = 0;
		for(int i=0; i<list.size();i++) {
			Person p = (Person)(list.get(i));
			totalPoint +=p.getPoint();
			//get이 반납하는 타입이 Object인데 사용해야할 메소드는 
			//Person 클래스 안에 있는 getPoint()이므로 형변환 해야 함.
		}
		System.out.println("포인트 총합 : "+totalPoint);
	}
}








package test.list;
import java.util.*;
public class TestPersonArrayList2 {

	public static void main(String[] args) {
		// Generic 사용 테스트
		//Generic은 선언한 클래스 외의 다른 클래스 타입은 저장할 수 없음->에러
		//오직 제한한 클래스만 사용 가능
		ArrayList<Person> list; //Person만 저장할 수 있는 list
		/*list = new ArrayList<Person>(); */
		//한번 제네릭 붙으면 레퍼런스 변수 선언, 생성 모두 제네릭 타입 명시
		
		list = addMethod(/*list*/);
		printList(list);
	}
	
	public static ArrayList<Person> addMethod(/*ArrayList<Person> list*/) {
		//받아주는 매개변수도 제네릭 타입 명시
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person("홍길동", 25, 1537.5));
		list.add(new Person("이순신", 49, 15789.9));
		list.add(new Person("신사임당", 55, 34567.4));
		//list.add(new String("신사임당"); Person 타입이 아니므로 에러
		
		return list;//메소드의 반환형에도 제네릭 명시 ArrayList<Person>
	}
	
	public static void printList(ArrayList<Person> list) {
		double totalPoint = 0.;
		for(Person p: list) {
			//제네릭이 선언되면 다운캐스팅이 필요 없고 
			//Object가 아닌 Person을 바로 꺼낼 수 있음.
			System.out.println(p);
			totalPoint += p.getPoint();
		}
		System.out.println("포인트총합 : "+ totalPoint);
	}
}
