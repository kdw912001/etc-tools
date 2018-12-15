package test.byteio;
import java.io.*;
public class TestDataIO {
	public void fileSave() {
		//DataOutputStream 클래스는 값을 종류별로 기록할 수 있는
		//메소드를 제공하는 보조스트림 클래스임.
		/*try (FileOutputStream fout = new FileOutputStream("member.dat");
				DataOutputStream dout = new DataOutputStream(fout);){
			
		}레퍼런스를 각각 쓸 일이 없다면 하나의 레퍼런스로 선언할 것.*/
		//한번에 선언하면 굳이 위에 처럼 2개의 레퍼런스를 선언할 필요가 없음.
		try(DataOutputStream dout = new DataOutputStream(
				new FileOutputStream("member.dat"))) {
			//dout.writeUTF("홍길동"); //Data 스트림은 기록한 순서대로 값을 읽어야 함.
			String name = "홍길동";
			int age = 27;
			char gender = '남';
			double height = 178.5;
			dout.writeUTF(name);//읽을 때 꼭 UTF로 읽어야 함
			dout.writeInt(age);
			dout.writeChar(gender);
			dout.writeDouble(height);
			dout.flush();
			
			System.out.println("파일에 기록 완료.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fileRead() {
		//레퍼런스를 각각 쓸 일이 없다면 하나의 레퍼런스로 선언할 것.
		/*try(FileInputStream fin = new FileInputStream("member.dat");
				DataInputStream din = new DataInputStream(fin);)*/
		try (DataInputStream din = new DataInputStream(
				new FileInputStream("member.dat"));){
			//꼭 기록한 순서대로 읽어와야 함.
			String name = din.readUTF();
			int age = din.readInt();
			char gender = din.readChar();
			double height = din.readDouble();
			
			System.out.println(name + ", "+age +", "+gender+", "+height);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		//data 입출력 보조 스트림 테스트
		TestDataIO test = new TestDataIO();
		//test.fileSave();
		//member.dat를 열어보면 직접 byte값이 나오기 때문에 값이 이상하게 나옴
		test.fileRead();
	}
}

package test.byteio;
import java.io.*;
public class TestDataIO2 {
	public void fileSave() {
		//try 옆에 선언과 생성해서 close 생략하지 말고 직접 다 쳐보기
		//exception 종류도 다 써보기
		FileOutputStream fout = null;
		DataOutputStream dout = null;
		try {
			fout=new FileOutputStream("member.dat");
			dout=new DataOutputStream(fout);
			//dout.writeUTF("홍길동"); //Data 스트림은 기록한 순서대로 값을 읽어야 함.
			String name = "홍길동";
			int age = 27;
			char gender = '남';
			double height = 178.5;
			dout.writeUTF(name);//읽을 때 꼭 UTF로 읽어야 함
			dout.writeInt(age);
			dout.writeChar(gender);
			dout.writeDouble(height);
			dout.flush();
			
			System.out.println("파일에 기록 완료.");
		}catch(FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		}catch(IOException e) {	e.printStackTrace();
		}catch (Exception e) {	e.printStackTrace();
		}finally {
			try {
				dout.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void fileRead() {
		FileInputStream fin = null;
		DataInputStream din = null;
		try {
			fin = new FileInputStream("test.dat");
			din = new DataInputStream(fin);
			//꼭 기록한 순서대로 읽어와야 함.
			String name = din.readUTF();
			int age = din.readInt();
			char gender = din.readChar();
			double height = din.readDouble();
			
			System.out.println(name + ", "+age +", "+gender+", "+height);
		}catch(FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				din.close();
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[]args) {
		TestDataIO test = new TestDataIO();
		//test.fileSave();
		test.fileRead();
	}

}

package test.byteio;
import java.io.*;
public class TestObjectIO {
	public void fileSave() {
		//파일 기록에 사용할 객체배열 준비
		Student[] sar = {
				new Student(12, "홍길동",4.43,"경영학과"),
				new Student(25, "이순신",4.5,"체육학과"),
				new Student(37, "장영실", 3.87,"정보통신학과")
		};
		
		try(ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream("student.dat"));){
			for(int i=0; i<sar.length;i++) {
				objOut.writeObject(sar[i]);
			}
			System.out.println("파일 기록 완료.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fileRead() {
		int count = 0;
		//배열공간은 모자르면 안되기 때문에 넉넉히 선언
		Student[] sar = new Student[10];
		try(ObjectInputStream objIn = new ObjectInputStream(
				new FileInputStream("student.dat"))){
			while(true) {
				/*Student std = (Student)objIn.readObject();
				//Object가 상위 클래스이기 때문에 다운캐스팅*/
				//위와같이 할 필요 없이 아래처럼 한 문장으로 선언
				sar[count] = (Student)objIn.readObject();
				count++;
			}
		}catch(EOFException e) {
			System.out.println("파일 읽기 완료");
			for(int i=0; i<count;i++) {
				System.out.println(sar[i]); //toString오버라이딩
			}
		}catch(Exception e) {
			//System.out.println("파일 읽기 완료.");
			//->EOFException발생 고로 catch문 작성
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// 객체입출력 테스트
		TestObjectIO test = new TestObjectIO();
		test.fileSave();
		test.fileRead();
	}
}

package io.silsub2;
import java.util.*;
import java.io.*;
public class SungjukProcess {
	private Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		SungjukProcess test = new SungjukProcess();
		//test.sungjukSave();
		test.scoreRead();
	}
	public void sungjukSave() {
		
		try(DataOutputStream dout = new DataOutputStream(
				new FileOutputStream("score.dat"));) {
			do{
				System.out.print("국어 점수 입력 : ");
				int kor = sc.nextInt();
				dout.writeInt(kor);
				System.out.print("영어 점수 입력 : ");
				int eng = sc.nextInt();
				dout.writeInt(eng);
				System.out.print("수학 점수 입력 : ");
				int ma = sc.nextInt();
				dout.writeInt(ma);
				
				int sum = kor+eng+ma;
				dout.writeInt(sum);
				double avg = (kor+eng+ma)/3;
				dout.writeDouble(avg);
				System.out.print("계속 저장하시겠습니까?(y/n) : ");
			}while(sc.next().toUpperCase().charAt(0) == 'Y');
			System.out.println("score.dat에 저장 완료.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scoreRead() {
		/*FileInputStream에 readDouble() the next eight bytes of this input stream, interpreted as a double.
		 * EOFException - if this input stream reaches the end before reading eight bytes.
		 * 마지막으로 입력받은 double값에 EOFException(값이 없을때)이 발생하면 반복문 종료
		 */
		int count = 0;
		int totalSum=0;
		double totalAvg=0;
		try(DataInputStream din = new DataInputStream(
				new FileInputStream("score.dat"));){
			
			do{
				int kor = din.readInt();
				int eng = din.readInt();
				int ma = din.readInt();
				int sum = din.readInt();
				double avg = din.readDouble();
						
				//System.out.println(kor+" "+eng+" "+ma+" "+sum+" "+avg);
				System.out.printf("%5d%5d%5d%5d%7.2f\n", kor,eng,ma,sum,avg);
				//printf 소수점 계산시 자동으로 반올림 됨.
				totalSum +=sum;
				totalAvg +=avg;
				count++;
			}while(true);
			//System.out.println("전체총점 : "+totalSum+"전체 평균 : "+totalAvg+"읽은 횟수 : "+count);
			
		}catch(EOFException e) {//마지막으로 저장한 값 readDouble()이 없을 때 실행되는 예외 
			System.out.printf("횟수 : %2d 합평균 : %2d 평균의 합 : %2.2f\n", count,totalSum/count,totalAvg/count);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package io.silsub3;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
public class Book implements Serializable{
	private static final long serialVersionUID = 2126172264279034891L;
	private String title;
	private String author;
	private int price;
	private Calendar dates;
	private double discountRate;
	
	public Book() {}
	public Book(String title, String author, int price, Calendar dates, double discountRate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.dates = dates;
		this.discountRate = discountRate;
	}

	public String getTitle() {	return title;}
	public void setTitle(String title) {this.title = title;	}
	public String getAuthor() {	return author;}
	public void setAuthor(String author) {	this.author = author;}
	public int getPrice() {return price;}
	public void setPrice(int price) {	this.price = price;}
	public Calendar getDates() {return dates;}
	public void setDates(Calendar dates) {this.dates = dates;	}
	public double getDiscountRate() {return discountRate;}
	public void setDiscountRate(double discountRate) {this.discountRate = discountRate;}
	public static long getSerialversionuid() {	return serialVersionUID;}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'년'MM'월'dd'일'출간");
		
		return title+" "+author+" "+price+" "+sdf.format(dates.getTime())+" "+discountRate;
	}
}

package io.silsub3;
import java.util.*;
import java.io.*;
public class BookManager {
	private Scanner sc = new Scanner(System.in);
	
	public BookManager() {}
	
	public void fileSave() {
		Book[] b = { //new GregorianCalendar();
				new Book("자바1", "김1", 1000, new GregorianCalendar(1990,11,11), 1.1),
				new Book("자바2","김2",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("자바3","김3",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("자바4","김4",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("자바5","김5",1000, new GregorianCalendar(1990,11,12),1.1),
		};
		try(ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream("books.dat", true));){
			//대상 파일이 없으면 자동으로 파일을 만듦
			//대상 파일이 있으면, 파일 안의 내용을 지우고 
			//새로쓰기 상태로 파일을 연다.
			//파일출력스트림 생성시에 추가쓰기(append) 모드를 true로 하면
			//대상파일이 있을 때 기존 내용은 그대로 두고 내용 뒤에 추가쓰기가 됨.
			//생략되면 기본이 false// true면 파일 안의 내용을 놔두고 계속 씀
			//객체 출력 스트림에서는 오류 남
			for(int i=0; i<b.length;i++) {
				objOut.writeObject(b[i]);
			}
			System.out.println("books.dat에 저장완료!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void fileRead() {
		Book[] bk = new Book[10];
		int count=0;
		try (ObjectInputStream objIn = new ObjectInputStream(
				new FileInputStream("books.dat"))){
			while(true) {
				bk[count]=(Book)objIn.readObject();
				count++;
			}
			
		}catch(EOFException e) {
			for(int i=0; i<count;i++) {
				System.out.println(bk[i]);
			}
			System.out.println("books.dat 읽기 완료!");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
package io.silsub3;

import replay.BookManager;
public class TestBookManager {
	public static void main(String[]args) {
		BookManager bm = new BookManager();
		//bm.fileSave();
		bm.fileRead();
	}
}

package test.chario;
import java.util.*;
import java.io.*;
public class TestCFileIO1 {
	private Scanner sc = new Scanner(System.in);
	
	public TestCFileIO1() {}
	
	//문자스트림으로 파일에 저장 처리
	public void fileSave() {
		FileWriter fw = null;
		
		System.out.print("저장할 파일명 : ");
		String fileName = sc.next();
		
		try {
			fw = new FileWriter(fileName,true);//대상파일과 스트림(통로) 생성
			//대상 파일이 없으면 파일을 새로 만듦
			//대상 파일이 있으면, 새로쓰기 상태로 열림
			
			fw.write('A');
			fw.write("java program \n");
			char[] charr = {'a','p','p','l','e'};
			fw.write(charr);
			fw.flush(); //스트림에서 완전히 밀어버리는 작업
						//가끔 스트림에 남는 데이터가 있는데 그걸 다 밀어버림
			
			System.out.println("파일 기록 저장 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//문자스트림으로 파일로부터 읽어와서 콘솔에 출력
	public void fileRead() {
		//FileReader fr = null;
		
		System.out.print("읽을 파일명 : ");
		String fileName = sc.next();
		
		try(FileReader fr = new FileReader(fileName);) {
			//선언과 생성이 같이 들어가야 함. 여러 개를 쓸수 있기때문에;도 가능
			//try 옆에 이런식으로 작성하게 되면 finally 에 close()생략 가능
			
			//fr = new FileReader(fileName);
			//파일이 없으면 에러임
			
			int readData;
			while((readData = fr.read()) != -1) { //read()는 문자 하나씩 읽음
				System.out.print((char)readData); //int형이기 때문에 형변환
			}
			System.out.println("\n파일읽기 완료");
		} catch (Exception e) { 
			//각각 에러 처리할 내용이 있으면 각각 Exception
			//하나의 에러메시지만 출력할거면 Exception 하나만 작성
			e.printStackTrace();
		}/*finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public static void main(String[] args) {
		// 문자기반 파일입출력 테스트
		TestCFileIO1 test = new TestCFileIO1();
		test.fileSave();
		test.fileRead();
	}
}

